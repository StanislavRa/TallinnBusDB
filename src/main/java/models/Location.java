package models;

import java.sql.Timestamp;

public class Location {
    int id;
    String stopName;
    Timestamp createdOn;

    public Location(String stopName) {
        this.stopName = stopName;
    }

    public Location() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "id=" + id +
                ", stopName='" + stopName + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
