package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Bus {
    private int id;
    private String busNumber;
    private int driverId;
    private float fuel;
    private Timestamp createdOn;
    private Date purchasedOn;

    public Bus(String busNumber, int driverId, float fuel, Date purchasedOn) {
        this.busNumber = busNumber;
        this.driverId = driverId;
        this.fuel = fuel;
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

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
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
                ", driverId=" + driverId +
                ", fuel=" + fuel +
                ", createdOn=" + createdOn +
                ", purchasedOn=" + new SimpleDateFormat("dd-MM-yyyy").format(purchasedOn) +
                '}';
    }
}
