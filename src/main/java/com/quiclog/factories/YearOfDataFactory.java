package com.quiclog.factories;

import java.time.Month;
import java.util.List;

import com.quiclog.entities.IMonthOfData;
import com.quiclog.entities.IYearOfData;
import com.quiclog.entities.YearOfData;

public class YearOfDataFactory {
    public static IYearOfData createYearOfData(Month[] startAndEndMonths, int year, List<IMonthOfData> monthOfData) {
        IYearOfData yearOfData = new YearOfData();
        yearOfData.setStartAndEndMonths(startAndEndMonths);
        yearOfData.setYear(year);
        yearOfData.setMonthOfData(monthOfData);
        return yearOfData;
    }
}
