package models;

import lombok.Data;
import lombok.ToString;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class Timetable {
    Long id;
    Weekday weekday;
    Time arrivalTime;
    Location location;
    Bus bus;
    Timestamp createdOn;

    @ToString
    public enum Weekday {
        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday"),
        SATURDAY("Saturday"),
        SUNDAY("Sunday");

        String name;

        Weekday(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
