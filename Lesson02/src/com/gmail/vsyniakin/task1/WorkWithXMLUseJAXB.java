package com.gmail.vsyniakin.task1;

import javax.xml.bind.*;
import java.io.File;

public class WorkWithXMLUseJAXB {

    public static void marshallerTrainsToXML(Trains trains, File file) {

        try {
            JAXBContext jaxbC = JAXBContext.newInstance(Trains.class);
            Marshaller marsh = jaxbC.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marsh.marshal(trains, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public static Trains unmarshallerXMLToTrains(File file) {
        try {
            JAXBContext jaxbC = JAXBContext.newInstance(Trains.class);
            Unmarshaller unmarsh = jaxbC.createUnmarshaller();
            Trains trains = (Trains) unmarsh.unmarshal(file);
            return trains;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
