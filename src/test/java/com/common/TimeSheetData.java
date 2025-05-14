package com.common;

public class TimeSheetData {
    private String employee;
    private String date;
    private String hours;
    private String remark;

    public TimeSheetData(String employee, String date, String hours, String remark) {
        this.employee = employee;
        this.date = date;
        this.hours = hours;
        this.remark = remark;
    }

    public String getEmployee() {
        return employee;
    }

    public String getDate() {
        return date;
    }

    public String getHours() {
        return hours;
    }

    public String getRemark() {
        return remark;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TimeSheetData other) {
            return employee.equals(other.employee) && date.equals(other.date) && hours.equals(other.hours) && remark.equals(other.remark);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return employee.hashCode() + date.hashCode() + hours.hashCode() + remark.hashCode();
    }
}
