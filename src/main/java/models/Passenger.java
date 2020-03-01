package models;

import java.sql.Timestamp;

public class Passenger {
    int id;
    String fullName;
    String email;
    String phoneNumber;
    int age;
    int startLocationId;
    int stopLocationId;
    int busId;
    Timestamp createdOn;

    public Passenger(String fullName, String email, String phoneNumber, int age, int startLocationId, int stopLocationId, int busId) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.startLocationId = startLocationId;
        this.stopLocationId = stopLocationId;
        this.busId = busId;
    }

    public Passenger() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStartLocationId() {
        return startLocationId;
    }

    public void setStartLocationId(int startLocationId) {
        this.startLocationId = startLocationId;
    }

    public int getStopLocationId() {
        return stopLocationId;
    }

    public void setStopLocationId(int stopLocationId) {
        this.stopLocationId = stopLocationId;
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
        return "Passenger{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", startLocationId=" + startLocationId +
                ", stopLocationId=" + stopLocationId +
                ", busId=" + busId +
                ", createdOn=" + createdOn +
                '}';
    }
}
