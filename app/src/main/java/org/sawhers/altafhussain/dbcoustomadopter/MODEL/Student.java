package org.sawhers.altafhussain.dbcoustomadopter.MODEL;

import java.io.Serializable;

/**
 * Created by ALTAFHUSSAIN on 1/4/2017.
 */

public class Student implements Serializable {
    int id;
    String name;
    String course;
    int totalfee;
    int feepaid;

    public Student() {
    }

    public Student(String name, String course, int totalfee, int feepaid) {
        this.name = name;
        this.course = course;
        this.totalfee = totalfee;
        this.feepaid = feepaid;
    }

    public Student(int id, String name, String course, int totalfee, int feepaid) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.totalfee = totalfee;
        this.feepaid = feepaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(int totalfee) {
        this.totalfee = totalfee;
    }

    public int getFeepaid() {
        return feepaid;
    }

    public void setFeepaid(int feepaid) {
        this.feepaid = feepaid;
    }

    @Override
    public String toString() {
        return name;
    }
}
