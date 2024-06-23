package com.quiclog.factories;

import java.util.List;

import com.quiclog.entities.IMonthOfData;
import com.quiclog.entities.IWeekOfData;
import com.quiclog.entities.MonthOfData;

public class MonthOfDataFactory {
    public static IMonthOfData createMonthOfData(int month, List<IWeekOfData> weekOfData) {
        IMonthOfData monthOfData = new MonthOfData();
        monthOfData.setMonth(month);
        monthOfData.setWeekOfData(weekOfData);
        return monthOfData;
    }
}

