package edu.aitu.oop3.service;

import edu.aitu.oop3.model.Appointment;
import edu.aitu.oop3.model.Doctor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private final List<Appointment> appointments = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
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

        Appointment appointment =
                new Appointment(nextId++, patientId, doctorId, dateTime);

        appointments.add(appointment);
        return appointment;
    }
    public Appointment bookBySpecialization(String specialization,
                                            int patientId,
                                            LocalDateTime dateTime) {

        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot book appointment in the past");
        }
        Doctor doctor = doctors.stream()
                .filter(d -> d.getSpecialization()
                        .equalsIgnoreCase(specialization))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("No doctor with this specialization"));
        Appointment appointment = Appointment.builder()
                .id(nextId++)
                .patientId(patientId)
                .doctorId(doctor.getId())
                .dateTime(dateTime)
                .build();

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