package com.example.gen_medicine.DATABASE;

public class DocList {
    String dname;
    String hospital;

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    String phone;

    public String getHospital() {
        return hospital;
    }

    public String getPhone() {
        return phone;
    }

    public String getDname() {
        return dname;
    }
}
