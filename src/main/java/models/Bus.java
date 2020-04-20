package models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Bus {
    private Long id;
    private String busNumber;
    private Driver driver;
    private Float fuel;
    private Timestamp createdOn;
    private Date purchasedOn;
}
