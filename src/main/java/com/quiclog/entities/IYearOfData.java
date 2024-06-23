package com.quiclog.entities;

import java.time.Month;
import java.util.List;

public interface IYearOfData {
    Month[] getStartAndEndMonths();
    void setStartAndEndMonths(Month[] months);
    
    int getYear();
    void setYear(int year);
    
    List<IMonthOfData> getMonthOfData();
    void setMonthOfData(List<IMonthOfData> monthOfData);
}
