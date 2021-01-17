package com.thoughtworks.exam;

import com.thoughtworks.exam.doctor.Doctor;
import com.thoughtworks.exam.doctor.Speciality;
import com.thoughtworks.exam.time.Day;
import com.thoughtworks.exam.time.TimeSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hospital {

    private static Hospital hospitalObj = null;
    private Map<Speciality, List<Doctor>> specialityToDoctorMap;

    private Hospital() {};

    public static Hospital getHospital() {
        if (hospitalObj == null) {
            hospitalObj = new Hospital();
            hospitalObj.specialityToDoctorMap = new HashMap<>();
        }
        return hospitalObj;
    }

    public void addDoctor(String name, Speciality speciality, Map<Day, List<TimeSlot>> availability) {
        Doctor doctor = Doctor.with(name, speciality, availability);
        if (!specialityToDoctorMap.containsKey(speciality)) {
            specialityToDoctorMap.put(speciality, new ArrayList<>());
        }
        specialityToDoctorMap.get(speciality).add(doctor);
    }

    public boolean isDoctorAvailable(Speciality speciality, Day day, TimeSlot timeSlot) {
        List<Doctor> specializedDoctors = specialityToDoctorMap.get(speciality);
        if (specializedDoctors == null) return false;
        for (Doctor doctor : specializedDoctors) {
            if (doctor.isAvailable(day, timeSlot)) return true;
        }
        return false;
    }

    public boolean bookAppointment(Speciality speciality, Day day, TimeSlot timeSlot) {
        if (!isDoctorAvailable(speciality, day, timeSlot)) return false;
        List<Doctor> specializedDoctors = specialityToDoctorMap.get(speciality);
        for (Doctor doctor : specializedDoctors) {
            if (doctor.isAvailable(day, timeSlot)) {
                doctor.bookAppointment(day, timeSlot);
                return true;
            }
        }
        return false;
    }
}
