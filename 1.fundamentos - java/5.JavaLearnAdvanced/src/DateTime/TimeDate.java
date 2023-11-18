package DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeDate {
    public static void main(String[] args) {
        LocalDate date = null;
        LocalTime time = null;
        LocalDateTime datetime = LocalDateTime.now();
        System.out.println(datetime.getDayOfMonth());
        System.out.println(datetime.getSecond());
        System.out.println(LocalDateTime.of(2002,1, 25, 10, 50));
    }
}
