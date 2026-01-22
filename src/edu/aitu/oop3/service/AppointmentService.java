package edu.aitu.oop3.service;

import edu.aitu.oop3.model.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private final List<Appointment> appointments = new ArrayList<>();
    private int nextId = 1;

    public Appointment book(int patientId, int doctorId, LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot book appointment in the past");
        }

        for (Appointment a : appointments) {
            if (a.getDoctorId() == doctorId &&
                    a.getDateTime().equals(dateTime) &&
                    a.getStatus().equals("BOOKED")) {
                throw new IllegalStateException("Doctor is busy at this time");
            }
        }

        Appointment appointment = new Appointment(nextId++, patientId, doctorId, dateTime);
        appointments.add(appointment);
        return appointment;
    }

    public List<Appointment> getAll() {
        return appointments;
    }

    public boolean cancel(int appointmentId) {
        for (Appointment a : appointments) {
            if (a.getId() == appointmentId) {
                a.cancel();
                return true;
            }
        }
        return false;
    }
}
