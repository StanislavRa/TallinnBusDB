package models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Passenger {
    Long id;
    String fullName;
    String email;
    String phoneNumber;
    int age;
    Location startLocation;
    Location stopLocation;
    Bus bus;
    Timestamp createdOn;
}
