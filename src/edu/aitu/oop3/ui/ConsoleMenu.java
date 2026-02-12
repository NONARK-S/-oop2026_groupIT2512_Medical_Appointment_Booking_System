package edu.aitu.oop3.ui;

import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner sc = new Scanner(System.in);

    public void start() {
        while (true) {
            printMenu();
            int choice = readInt("Choose option: ");

            switch (choice) {
                case 1 -> handleBookAppointment();
                case 2 -> handleCancelAppointment();
                case 3 -> handleShowAppointments();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }

            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("=== Medical Appointment Booking System ===");
        System.out.println("1) Book appointment");
        System.out.println("2) Cancel appointment");
        System.out.println("3) Show appointments");
        System.out.println("0) Exit");
    }

    private void handleBookAppointment() {
        System.out.println("--- Book appointment ---");

        String patientId = readLine("Patient ID: ");
        String doctorId = readLine("Doctor ID: ");
        String date = readLine("Date (YYYY-MM-DD): ");
        String time = readLine("Time (HH:MM): ");

        System.out.println("Appointment booked successfully.");
    }

    private void handleCancelAppointment() {
        System.out.println("--- Cancel appointment ---");

        String appointmentId = readLine("Appointment ID: ");

        System.out.println("Appointment canceled.");
    }

    private void handleShowAppointments() {
        System.out.println("--- Show appointments ---");
        System.out.println("(Appointments list)");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
