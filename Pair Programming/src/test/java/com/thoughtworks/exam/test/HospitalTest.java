package com.thoughtworks.exam.test;

import com.thoughtworks.exam.Hospital;
import com.thoughtworks.exam.doctor.Speciality;
import com.thoughtworks.exam.time.Day;
import com.thoughtworks.exam.time.Time;
import com.thoughtworks.exam.time.TimeSlot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalTest {

    private TimeSlot getTwoToThreeTimeSlot() {
        return new TimeSlot(Time.at(2), Time.at(3));
    }

    @Test
    public void testBookingAvailableSlot() {
        Hospital hospital = Hospital.getHospital();
        addDoctorMonday2To3("Karthik", Speciality.ENT);
        assert (hospital.bookAppointment(Speciality.ENT, Day.MONDAY, getTwoToThreeTimeSlot()));
    }

    @Test
    public void testBookingSameSlotMultipleTimes() {
        Hospital hospital = Hospital.getHospital();
        addDoctorMonday2To3("Karthik", Speciality.ENT);
        assert (hospital.bookAppointment(Speciality.ENT, Day.MONDAY, getTwoToThreeTimeSlot()));
        assert (!hospital.bookAppointment(Speciality.ENT, Day.MONDAY, getTwoToThreeTimeSlot()));
    }

    @Test
    public void testAnotherBooking() {
        Hospital hospital = Hospital.getHospital();
        addDoctorMonday2To3("Karthik", Speciality.ENT);
        addDoctorMonday2To3("Sumanth", Speciality.ENT);
        assert (hospital.bookAppointment(Speciality.ENT, Day.MONDAY, getTwoToThreeTimeSlot()));
        assert (hospital.bookAppointment(Speciality.ENT, Day.MONDAY, getTwoToThreeTimeSlot()));
    }

    @Test
    public void testBookingUnavailableSlot() {
        Hospital hospital = Hospital.getHospital();
        assert (!hospital.bookAppointment(Speciality.ENT, Day.MONDAY, getTwoToThreeTimeSlot()));
    }

    private void addDoctorMonday2To3(String name, Speciality speciality) {
        Hospital hospital = Hospital.getHospital();
        Map<Day, List<TimeSlot>> availability = new HashMap<>();
        availability.put(Day.MONDAY, new ArrayList<TimeSlot>());
        TimeSlot twoToThreeTimeSlot = getTwoToThreeTimeSlot();
        twoToThreeTimeSlot.setAvailable(true);
        availability.get(Day.MONDAY).add(twoToThreeTimeSlot);
        hospital.addDoctor(name, speciality, availability);
    }
}
