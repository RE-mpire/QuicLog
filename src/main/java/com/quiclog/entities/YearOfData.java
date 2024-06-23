package com.quiclog.entities;

import java.time.Month;
import java.util.List;

public class YearOfData implements IYearOfData {
    private Month[] startAndEndMonths;
    private int year;
    private List<IMonthOfData> monthOfData;

    @Override
    public Month[] getStartAndEndMonths() {
        return startAndEndMonths;
    }

    @Override
    public void setStartAndEndMonths(Month[] startAndEndMonths) {
        this.startAndEndMonths = startAndEndMonths;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public List<IMonthOfData> getMonthOfData() {
        return monthOfData;
    }

    @Override
    public void setMonthOfData(List<IMonthOfData> monthOfData) {
        this.monthOfData = monthOfData;
    }
}

