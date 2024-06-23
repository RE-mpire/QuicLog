package com.quiclog.entities;

import java.time.LocalDateTime;

public class DataPoint implements IDataPoint {
    private Object data;
    private LocalDateTime dateTime;

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

