package com.example.demKare;

import java.io.Serializable;
import java.util.Date;

public class Reminder implements Serializable {
    private String name;
    private String file;
    private String category;
    private Date date;
    private String time;

    public Reminder(String name, String file, String category, Date date, String time) {
        this.category=category;
        this.date=date;
        this.file=file;
        this.name=name;
        this.time=time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
