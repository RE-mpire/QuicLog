package com.quiclog.entities;

import java.util.List;

public class MonthOfData implements IMonthOfData {
    private int month;
    private List<IWeekOfData> weekOfData;

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public List<IWeekOfData> getWeekOfData() {
        return weekOfData;
    }

    @Override
    public void setWeekOfData(List<IWeekOfData> weekOfData) {
        this.weekOfData = weekOfData;
    }
}
