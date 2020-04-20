package models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Location {
    Long id;
    String stopName;
    Timestamp createdOn;
}
