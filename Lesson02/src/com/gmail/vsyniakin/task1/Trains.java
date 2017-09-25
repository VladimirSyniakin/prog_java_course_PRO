package com.gmail.vsyniakin.task1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@XmlRootElement
public class Trains {

    @XmlElement (name = "train")
    private ArrayList <Train> arrayTrains = new ArrayList<>();

    public Trains() {
    }

    public void addTrain (Train train){
        this.arrayTrains.add(train);
    }


    public ArrayList<Train> getArrayListTrains() {
        return arrayTrains;
    }

    public void setArrayTrains(ArrayList<Train> arrayTrains) {
        this.arrayTrains = arrayTrains;
    }

    public ArrayList<Train> timeDepartureTrains (String timeFromStr, String timeToStr){
        LocalDate today = LocalDate.now();
        LocalTime timeFrom = LocalTime.parse(timeFromStr,Train.timeFormate());
        LocalTime timeTo = LocalTime.parse(timeToStr,Train.timeFormate());
        ArrayList<Train> trainsInThisTime = new ArrayList<>();

        for (Train train: arrayTrains) {
            if ((train.getDate().equals(today))&&(train.getDeparture().isAfter(timeFrom))&&(train.getDeparture().isBefore(timeTo))){
                trainsInThisTime.add(train);
            }
        }
        return trainsInThisTime;
    }

    @Override
    public String toString() {
        return "Trains{" +
                "arrayTrains=" + arrayTrains +
                '}';
    }
}
