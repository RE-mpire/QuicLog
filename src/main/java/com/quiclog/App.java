package com.quiclog;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import com.quiclog.entities.Action;
import com.quiclog.entities.IAction;
import com.quiclog.entities.IDataPoint;
import com.quiclog.entities.IMetric;
import com.quiclog.entities.IMonthOfData;
import com.quiclog.entities.IQuestion;
import com.quiclog.entities.IQuestionnaire;
import com.quiclog.entities.IWeekOfData;
import com.quiclog.entities.IYearOfData;
import com.quiclog.factories.DataPointFactory;
import com.quiclog.factories.MetricFactory;
import com.quiclog.factories.MonthOfDataFactory;
import com.quiclog.factories.QuestionFactory;
import com.quiclog.factories.QuestionnaireFactory;
import com.quiclog.factories.WeekOfDataFactory;
import com.quiclog.factories.YearOfDataFactory;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, IMetric> metrics = new HashMap<>();
    private static final Map<String, IQuestionnaire> questionnaires = new HashMap<>();
    private static final Stack<IAction> actionLog = new Stack<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter command (create metric, create questionnaire, log [questionnaire], show [all, questionnaire, metric], delete [...], undo, exit):");
            System.out.print("> ");
            String command = scanner.nextLine();
            String[] parts = command.split(" ");

            switch (parts[0].toLowerCase()) {
                case "create":
                    if (parts.length < 2) {
                        System.out.println("Create target required (metric or questionnaire).");
                        break;
                    }
                    if (parts[1].equalsIgnoreCase("metric")) {
                        createMetric();
                    } else if (parts[1].equalsIgnoreCase("questionnaire")) {
                        createQuestionnaire();
                    } else {
                        System.out.println("Invalid create command.");
                    }
                    break;
                case "log":
                    if (parts.length > 1) {
                        logQuestionnaire(parts[1]);
                    } else {
                        System.out.println("Questionnaire name required.");
                    }
                    break;
                case "show":
                    if (parts.length > 1) {
                        show(parts[1]);
                    } else {
                        System.out.println("Show type required (all, questionnaire, metric).");
                    }
                    break;
                case "delete":
                    if (parts.length > 1) {
                        delete(parts[1]);
                    } else {
                        System.out.println("Delete target required (questionnaire or metric).");
                    }
                    break;
                case "undo":
                    undo();
                    break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("Unknown command.");
                    break;
            }
        }
    }

    private static void createMetric() {
        System.out.println("Enter metric title:");
        String title = scanner.nextLine();
        System.out.println("Enter metric description:");
        String description = scanner.nextLine();
        System.out.println("Enter minimum value:");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter maximum value:");
        int max = Integer.parseInt(scanner.nextLine());

        IMetric metric = MetricFactory.createMetric(title, description, min, max, new ArrayList<>());
        metrics.put(title, metric);

        IAction action = new Action(IAction.ActionType.ADD, metric, null, metric);
        actionLog.push(action);

        System.out.println("Metric created.");
    }

    private static void createQuestionnaire() {
        if (metrics.isEmpty()) {
            System.out.println("No metrics available. Create a metric first.");
            return;
        }

        System.out.println("Enter questionnaire name:");
        String name = scanner.nextLine();
        System.out.println("Enter number of questions:");
        int numQuestions = Integer.parseInt(scanner.nextLine());

        Map<IQuestion, IMetric> questionMetricPairs = new HashMap<>();

        for (int i = 0; i < numQuestions; i++) {
            System.out.println("Enter question prompt:");
            String prompt = scanner.nextLine();
            System.out.println("Enter question type (scale, textField, numField):");
            IQuestion.QuestionType type = IQuestion.QuestionType.valueOf(scanner.nextLine().toUpperCase());
            System.out.println("Enter corresponding metric title:");
            String metricTitle = scanner.nextLine();
            IMetric metric = metrics.get(metricTitle);

            if (metric == null) {
                System.out.println("Metric not found.");
                i--; // Retry this question
                continue;
            }

            IQuestion question = QuestionFactory.createQuestion(prompt, type);
            questionMetricPairs.put(question, metric);
        }

        IQuestionnaire questionnaire = QuestionnaireFactory.createQuestionnaire(name, questionMetricPairs);
        questionnaires.put(name, questionnaire);

        IAction action = new Action(IAction.ActionType.ADD, questionnaire, null, questionnaire);
        actionLog.push(action);

        System.out.println("Questionnaire created.");
    }

    private static void logQuestionnaire(String questionnaireName) {
        IQuestionnaire questionnaire = questionnaires.get(questionnaireName);
        if (questionnaire == null) {
            System.out.println("Questionnaire not found.");
            return;
        }

        for (Map.Entry<IQuestion, IMetric> entry : questionnaire.getQuestionMetricPairs().entrySet()) {
            IQuestion question = entry.getKey();
            IMetric metric = entry.getValue();
            System.out.println(question.getPrompt());
            String answer = scanner.nextLine();

            IDataPoint dataPoint = DataPointFactory.createDataPoint(answer, LocalDateTime.now());
            // Assuming a simple yearly structure for demo purposes
            if (metric.getYearOfData().isEmpty()) {
                IYearOfData yearOfData = YearOfDataFactory.createYearOfData(new Month[]{Month.JANUARY, Month.DECEMBER}, LocalDateTime.now().getYear(), new ArrayList<>());
                metric.getYearOfData().add(yearOfData);
            }
            IYearOfData yearOfData = metric.getYearOfData().get(0); // Simplified assumption for demo
            if (yearOfData.getMonthOfData().isEmpty()) {
                IMonthOfData monthOfData = MonthOfDataFactory.createMonthOfData(LocalDateTime.now().getMonthValue(), new ArrayList<>());
                yearOfData.getMonthOfData().add(monthOfData);
            }
            IMonthOfData monthOfData = yearOfData.getMonthOfData().get(0); // Simplified assumption for demo
            if (monthOfData.getWeekOfData().isEmpty()) {
                IWeekOfData weekOfData = WeekOfDataFactory.createWeekOfData(new ArrayList<>());
                monthOfData.getWeekOfData().add(weekOfData);
            }
            IWeekOfData weekOfData = monthOfData.getWeekOfData().get(0); // Simplified assumption for demo
            weekOfData.getDataPoints().add(dataPoint);
        }

        System.out.println("Data logged.");
    }

    private static void show(String type) {
        switch (type.toLowerCase()) {
            case "all" -> {
                System.out.println("Questionnaires:");
                for (String name : questionnaires.keySet()) {
                    System.out.println(" - " + name);
                }
                System.out.println("Metrics:");
                for (String name : metrics.keySet()) {
                    System.out.println(" - " + name);
                }
            }
            case "questionnaire" -> {
                System.out.println("Enter questionnaire name:");
                String questionnaireName = scanner.nextLine();
                IQuestionnaire questionnaire = questionnaires.get(questionnaireName);
                if (questionnaire == null) {
                    System.out.println("Questionnaire not found.");
                    return;
                }
                for (IQuestion question : questionnaire.getQuestionMetricPairs().keySet()) {
                    System.out.println(question.getPrompt() + " (" + question.getType() + ")");
                }
            }
            case "metric" -> {
                System.out.println("Enter metric name:");
                String metricName = scanner.nextLine();
                IMetric metric = metrics.get(metricName);
                if (metric == null) {
                    System.out.println("Metric not found.");
                    return;
                }
                System.out.println("Title: " + metric.getTitle());
                System.out.println("Description: " + metric.getDescription());
                System.out.println("Min: " + metric.getMin());
                System.out.println("Max: " + metric.getMax());
                System.out.println("Data: ");
                for (IYearOfData yearOfData : metric.getYearOfData()) {
                    System.out.println("Year: " + yearOfData.getYear());
                    for (IMonthOfData monthOfData : yearOfData.getMonthOfData()) {
                        System.out.println(" Month: " + monthOfData.getMonth());
                        for (IWeekOfData weekOfData : monthOfData.getWeekOfData()) {
                            for (IDataPoint dataPoint : weekOfData.getDataPoints()) {
                                System.out.println("  - " + dataPoint.getData() + " (logged on " + dataPoint.getDateTime() + ")");
                            }
                        }
                    }
                }
            }
            default -> System.out.println("Unknown show type.");
        }
    }

    private static void delete(String target) {
        switch (target.toLowerCase()) {
            case "questionnaire" -> {
                System.out.println("Enter questionnaire name:");
                String questionnaireName = scanner.nextLine();
                IQuestionnaire questionnaire = questionnaires.remove(questionnaireName);
                if (questionnaire == null) {
                    System.out.println("Questionnaire not found.");
                    return;
                }
                IAction action = new Action(IAction.ActionType.DELETE, questionnaire, questionnaire, null);
                actionLog.push(action);
                System.out.println("Questionnaire deleted.");
            }
            case "metric" -> {
                System.out.println("Enter metric name:");
                String metricName = scanner.nextLine();
                IMetric metric = metrics.remove(metricName);
                if (metric == null) {
                    System.out.println("Metric not found.");
                    return;
                }
                IAction actionMetric = new Action(IAction.ActionType.DELETE, metric, metric, null);
                actionLog.push(actionMetric);
                System.out.println("Metric deleted.");
            }
            default -> System.out.println("Unknown delete target.");
        }
    }

    private static void undo() {
        if (actionLog.isEmpty()) {
            System.out.println("No actions to undo.");
            return;
        }

        IAction lastAction = actionLog.pop();
        switch (lastAction.getActionType()) {
            case ADD -> {
            switch (lastAction.getTargetEntity()) {
                case IQuestionnaire addedQuestionnaire -> {
                    questionnaires.remove(addedQuestionnaire.getName());
                    System.out.println("Undo add: Questionnaire removed.");
                }
                case IMetric addedMetric -> {
                    metrics.remove(addedMetric.getTitle());
                    System.out.println("Undo add: Metric removed.");
                }
                default -> {
                }
            }
            }
            case DELETE -> {
            switch (lastAction.getPreviousState()) {
                case IQuestionnaire deletedQuestionnaire -> {
                    questionnaires.put(deletedQuestionnaire.getName(), deletedQuestionnaire);
                    System.out.println("Undo delete: Questionnaire restored.");
                }
                case IMetric deletedMetric -> {
                    metrics.put(deletedMetric.getTitle(), deletedMetric);
                    System.out.println("Undo delete: Metric restored.");
                }
                default -> {
                }
            }
            }
            default -> System.out.println("Unknown action type.");
        }
    }
}