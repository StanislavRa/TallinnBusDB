package models;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Timestamp;
import java.util.Date;

public class Bus {
    private int id;
    private String busNumber;
    private Driver driver;
    private float fuel;
    private Timestamp createdOn;
    private Date purchasedOn;

    public Bus(int id, String busNumber, Driver driver, float fuel, Timestamp createdOn, Date purchasedOn) {
        this.id = id;
        this.busNumber = busNumber;
        this.driver = driver;
        this.fuel = fuel;
        this.createdOn = createdOn;
        this.purchasedOn = purchasedOn;
    }

    public Bus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Date getPurchasedOn() {
        return purchasedOn;
    }

    public void setPurchasedOn(Date purchasedOn) {
        this.purchasedOn = purchasedOn;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", busNumber='" + busNumber + '\'' +
                ", driver=" + driver.getFullName() +
                ", fuel=" + fuel +
                ", createdOn=" + createdOn +
                ", purchasedOn=" + purchasedOn +
                '}';
    }
}
