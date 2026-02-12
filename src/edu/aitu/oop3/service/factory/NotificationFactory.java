package edu.aitu.oop3.patterns.factory;

public class NotificationFactory {

    public static Notification create(String type) {

        if (type.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        }

        if (type.equalsIgnoreCase("SMS")) {
            return new SmsNotification();
        }

        throw new IllegalArgumentException("Unknown notification type");
    }
}
