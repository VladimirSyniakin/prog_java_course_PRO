package com.gmail.vsyniakin.task1;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@XmlRootElement
public class Train {
    private int id;
    private static int tempId = 0;
    private String from;
    private String to;
    private LocalDate date;
    private LocalTime departure;

    public Train() {
    }

    public Train(String from, String to, LocalDate date, LocalTime departure) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
        ++tempId;
        this.id = tempId;
    }

    public static DateTimeFormatter dateFormate (){
        return DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    public static DateTimeFormatter timeFormate (){
        return DateTimeFormatter.ofPattern("HH:mm");
    }

    @XmlElement
    public String getFrom() {
        return from;
    }

    @XmlElement
    public String getTo() {
        return to;
    }

    @XmlElement
    @XmlJavaTypeAdapter(DateXMLAdapter.class)
    public LocalDate getDate() {
        return date;
    }

    @XmlElement
    @XmlJavaTypeAdapter(TimeXMLAdapter.class)
    public LocalTime getDeparture() {
        return departure;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public static int getTempId() {
        return tempId;
    }

    public static void setTempId(int tempId) {
        Train.tempId = tempId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                ", departure=" + departure +
                '}';
    }
}
