package models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Driver {
    private Long id;
    private String fullName;
    private String address;
    private String phone;
    private int age;
    private Float height;
    private Timestamp createdOn;
}
