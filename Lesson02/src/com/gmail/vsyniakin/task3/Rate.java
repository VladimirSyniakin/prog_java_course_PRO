package com.gmail.vsyniakin.task3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "rate")
public class Rate {

    private String id;
    private String name;
    private double rate;
    private String date;
    private String time;
    private String ask;
    private String bid;

    @XmlAttribute
    public String getId() {
        return id;
    }

    @XmlElement (name = "Name")
    public String getName() {
        return name;
    }

    @XmlElement (name = "Rate")
    public double getRate() {
        return rate;
    }

    @XmlElement (name = "Date")
    public String getDate() {
        return date;
    }

    @XmlElement (name = "Time")
    public String getTime() {
        return time;
    }

    @XmlElement (name = "Ask")
    public String getAsk() {
        return ask;
    }

    @XmlElement (name = "Bid")
    public String getBid() {
        return bid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", ask='" + ask + '\'' +
                ", bid='" + bid + '\'' +
                '}';
    }
}
