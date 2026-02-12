package edu.aitu.oop3.service;

import edu.aitu.oop3.model.Appointment;
import edu.aitu.oop3.model.Doctor;
import edu.aitu.oop3.patterns.factory.Notification;
import edu.aitu.oop3.patterns.factory.NotificationFactory;
import edu.aitu.oop3.patterns.singleton.ConfigManager;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartBookingService {

    public Optional<Appointment> smartBook(
            List<Doctor> doctors,
            String specialization,
            String patientId,
            String dateTime,
            String reason,
            String notifyType,
            String notifyTo
    ) {

        Optional<Doctor> bestDoctor = doctors.stream()
                .filter(d -> d.getSpecialization().equalsIgnoreCase(specialization))
                .sorted(Comparator.comparingDouble(Doctor::getRating).reversed())
                .findFirst();

        if (bestDoctor.isEmpty()) return Optional.empty();

        Doctor doctor = bestDoctor.get();

        Appointment appointment = Appointment.builder()
                .id("APT-" + System.currentTimeMillis())
                .patientId(patientId)
                .doctorId(doctor.getId())
                .dateTime(dateTime)
                .reason(reason)
                .status("CREATED")
                .build();

        ConfigManager cfg = ConfigManager.getInstance();

        if (cfg.isNotificationsEnabled()) {
            Notification n = NotificationFactory.create(notifyType);
            n.send(notifyTo,
                    "Booked: " + cfg.getClinicName() +
                            ", Doctor=" + doctor.getName() +
                            ", Time=" + dateTime);
        }

        return Optional.of(appointment);
    }
}
