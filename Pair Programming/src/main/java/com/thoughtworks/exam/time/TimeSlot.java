package com.thoughtworks.exam.time;

public class TimeSlot {
    private Time startTime;
    private Time endTime;
    private boolean available;

    public TimeSlot(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean contains(TimeSlot slot) {
        return startTime.compareTo(slot.startTime) <= 0
                && endTime.compareTo(slot.endTime) >= 0;
    }

    public int durationInHours() {
        return endTime.getHours() - startTime.getHours();
    }

    public int startTime() {
        return startTime.getHours();
    }

    public int endTime() {
        return endTime.getHours();
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
