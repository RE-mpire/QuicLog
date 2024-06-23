package com.quiclog.entities;

import java.util.List;

public interface IMetric {
    String getTitle();
    void setTitle(String title);
    
    String getDescription();
    void setDescription(String description);
    
    int getMin();
    void setMin(int min);
    
    int getMax();
    void setMax(int max);
    
    List<IYearOfData> getYearOfData();
    void setYearOfData(List<IYearOfData> yearOfData);
}

