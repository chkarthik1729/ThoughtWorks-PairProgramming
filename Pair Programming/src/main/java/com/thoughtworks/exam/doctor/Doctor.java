package com.thoughtworks.exam.doctor;

import com.thoughtworks.exam.time.Day;
import com.thoughtworks.exam.time.TimeSlot;

import java.util.List;
import java.util.Map;

public class Doctor {

    private String name;
    private Speciality speciality;
    private Map<Day, List<TimeSlot>> dayTimeSlotMap;

    private Doctor(String name, Speciality speciality, Map<Day, List<TimeSlot>> dayTimeSlotMap) {
        this.name = name;
        this.speciality = speciality;
        this.dayTimeSlotMap = dayTimeSlotMap;
    }

    public static Doctor with(String name, Speciality speciality, Map<Day, List<TimeSlot>> availabilityDayToTimeSlot) {
        Doctor doctor = new Doctor(name, speciality, availabilityDayToTimeSlot);
        return doctor;
    }

    public boolean isAvailable(Day day,TimeSlot slot) {
        List<TimeSlot> slots = dayTimeSlotMap.get(day);
        for (TimeSlot doctorSlot : slots) {
            if (doctorSlot.contains(slot) && doctorSlot.isAvailable()) return true;
        }
        return false;
    }

    public boolean bookAppointment(Day day, TimeSlot slot) {
        if (!isAvailable(day, slot)) return false;
        List<TimeSlot> slots = dayTimeSlotMap.get(day);
        for (TimeSlot doctorSlot : slots) {
            if (doctorSlot.contains(slot) && doctorSlot.isAvailable()) {
                doctorSlot.setAvailable(false);
                return true;
            }
        }
        return false;
    }
}
