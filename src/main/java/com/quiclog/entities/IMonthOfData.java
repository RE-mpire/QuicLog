package com.quiclog.entities;

import java.util.List;

public interface IMonthOfData {
    int getMonth();
    void setMonth(int month);
    
    List<IWeekOfData> getWeekOfData();
    void setWeekOfData(List<IWeekOfData> weekOfData);
}

