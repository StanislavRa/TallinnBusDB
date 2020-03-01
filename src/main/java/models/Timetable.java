package models;

import java.sql.Time;
import java.sql.Timestamp;

public class Timetable {
    int id;
    String weekday;
    Time arrivalTime;
    int locationId;
    int busId;
    Timestamp createdOn;

    public Timetable(String weekday, Time arrivalTime, int locationId, int busId) {
        this.weekday = weekday;
        this.arrivalTime = arrivalTime;
        this.locationId = locationId;
        this.busId = busId;
    }

    public Timetable() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "id=" + id +
                ", weekday='" + weekday + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", locationId=" + locationId +
                ", busId=" + busId +
                ", createdOn=" + createdOn +
                '}';
    }
}
