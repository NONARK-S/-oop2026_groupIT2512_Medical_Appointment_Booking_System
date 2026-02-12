package edu.aitu.oop3.patterns.singleton;;

public class ConfigManager {

    private static ConfigManager instance;

    private String clinicName = "Medical Appointment Booking System";
    private boolean notificationsEnabled = true;

    private ConfigManager() {
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getClinicName() {
        return clinicName;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }
}
