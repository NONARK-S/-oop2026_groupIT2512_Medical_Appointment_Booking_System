import edu.aitu.oop3.service.AppointmentService;
import java.time.LocalDateTime;

public class Main {
  public static void main(String[] args) {
    System.out.println("Medical Appointment Booking System started");

    AppointmentService service = new AppointmentService();

    service.book(1, 1, LocalDateTime.now().plusDays(1));
    service.book(2, 1, LocalDateTime.now().plusDays(2));

    System.out.println("Appointments count: " + service.getAll().size());
  }
}
