package edu.aitu.oop3.model;

import java.time.LocalDateTime;

public class Appointment {

    private int id;
    private int patientId;
    private int doctorId;
    private LocalDateTime dateTime;
    private String status;
    public Appointment(int id, int patientId, int doctorId, LocalDateTime dateTime) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.dateTime = dateTime;
        this.status = "BOOKED";
    }
    private Appointment(Builder builder) {
        this.id = builder.id;
        this.patientId = builder.patientId;
        this.doctorId = builder.doctorId;
        this.dateTime = builder.dateTime;
        this.status = builder.status;
    }
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private int id;
        private int patientId;
        private int doctorId;
        private LocalDateTime dateTime;
        private String status = "BOOKED";

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder patientId(int patientId) {
            this.patientId = patientId;
            return this;
        }

        public Builder doctorId(int doctorId) {
            this.doctorId = doctorId;
            return this;
        }

        public Builder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }
    public int getId() { return id; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getStatus() { return status; }

    public void cancel() {
        this.status = "CANCELLED";
    }
}