package edu.aitu.oop3.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointments {

    public static void main(String[] args) {
        System.out.println("Demo: create appointments table, insert, select");
        try (Connection connection = DatabaseConnection.getConnection()) {
            createTableIfNeeded(connection);
            insertAppointment(connection, 1, 1, LocalDate.of(2026, 1, 22), LocalTime.of(14, 30), "Scheduled");
            insertAppointment(connection, 2, 1, LocalDate.of(2026, 1, 23), LocalTime.of(10, 0), "Scheduled");
            printAllAppointments(connection);
        } catch (SQLException e) {
            System.out.println("Database error:");
            e.printStackTrace();
        }
    }
    private static void createTableIfNeeded(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS appointments (
                id SERIAL PRIMARY KEY,
                patient_id INT REFERENCES patients(id),
                doctor_id INT REFERENCES doctors(id),
                appointment_date DATE NOT NULL,
                appointment_time TIME NOT NULL,
                status VARCHAR(20) NOT NULL
            )
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Table appointments is ready.");
        }
    }
    private static void insertAppointment(Connection connection, int patientId, int doctorId,
                                          LocalDate date, LocalTime time, String status) throws SQLException {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.setInt(2, doctorId);
            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.setTime(4, java.sql.Time.valueOf(time));
            stmt.setString(5, status);
            int rows = stmt.executeUpdate();
            System.out.println("Inserted rows: " + rows);
        }
    }
    private static void printAllAppointments(Connection connection) throws SQLException {
        String sql = "SELECT * FROM appointments";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Current appointments:");
            while (rs.next()) {
                int id = rs.getInt("id");
                int patientId = rs.getInt("patient_id");
                int doctorId = rs.getInt("doctor_id");
                LocalDate date = rs.getDate("appointment_date").toLocalDate();
                LocalTime time = rs.getTime("appointment_time").toLocalTime();
                String status = rs.getString("status");

                System.out.printf("ID: %d | Patient ID: %d | Doctor ID: %d | Date: %s | Time: %s | Status: %s%n",
                        id, patientId, doctorId, date, time, status);
            }
        }
    }
}