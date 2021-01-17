package com.thoughtworks.exam.time;

// Represents time in hours and minutes
public class Time implements Comparable {
    private Integer hours;

    private Time(int hours) {
        this.hours = hours;
    }

    public static Time at(int hours) {
        return new Time(hours);
    }

    @Override
    public int compareTo(Object o) {
        Time other = (Time) o;
        return hours.compareTo(other.hours);
    }

    public int getHours() {
        return hours;
    }
}