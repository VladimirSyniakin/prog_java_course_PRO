package com.gmail.vsyniakin;

import com.gmail.vsyniakin.task1.Train;
import com.gmail.vsyniakin.task1.Trains;
import com.gmail.vsyniakin.task1.WorkWithXML;
import com.gmail.vsyniakin.task1.WorkWithXMLUseJAXB;
import com.gmail.vsyniakin.task2.Human;
import com.gmail.vsyniakin.task2.ParseJSONFile;
import com.gmail.vsyniakin.task3.ParseXML;
import com.gmail.vsyniakin.task3.Query;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        // task1:

        File fileOne = new File("trains.xml");
        Trains trains = WorkWithXML.loadFromFile(fileOne);
        Train trainOne = new Train("Odessa","Kiev", LocalDate.parse("21.09.2017",Train.dateFormate()), LocalTime.parse("18:10",Train.timeFormate()));
        WorkWithXML.saveTrainToFile (trainOne, new File ("trains.xml"));
        System.out.println(trains.timeDepartureTrains("15:00","19:00"));

            // use JAXB, marshal & unmarshal:
        Trains trainsJAXB = WorkWithXMLUseJAXB.unmarshallerXMLToTrains(fileOne);
        System.out.println(trainsJAXB);
        File fileTwo = new File ("trainsJAXB.xml");
        WorkWithXMLUseJAXB.marshallerTrainsToXML(trainsJAXB, fileTwo);


        // task2:

        Human human = ParseJSONFile.loadFromJSON(new File("parse to JSON.json"));
        System.out.println(human);
        ParseJSONFile.saveToJSONFile(human, new File ("parse to JSON temp.json"));


        // task3:

        Query query = ParseXML.unmarshallerXML("http://query.yahooapis.com/v1/public/yql?format=xml&q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22USDEUR%22,%20%22USDUAH%22)&env=store://datatables.org/alltableswithkeys");

        System.out.println(query);

        ParseXML.marshallerXML(query, System.out);


    }
}
