package models;

import java.sql.Timestamp;

public class Driver {
    private int id;
    private String fullName;
    private String address;
    private String phone;
    private int age;
    private float height;
    private Timestamp createdOn;

    public Driver(String fullName, String address, String phone, int age, float height) {
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.height = height;
    }

    public Driver() {}

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", createdOn=" + createdOn +
                '}';
    }
}
