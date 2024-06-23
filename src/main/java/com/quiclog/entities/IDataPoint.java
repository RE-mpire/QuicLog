package com.quiclog.entities;

import java.time.LocalDateTime;

public interface IDataPoint {
    Object getData();
    void setData(Object data);
    
    LocalDateTime getDateTime();
    void setDateTime(LocalDateTime dateTime);
}
