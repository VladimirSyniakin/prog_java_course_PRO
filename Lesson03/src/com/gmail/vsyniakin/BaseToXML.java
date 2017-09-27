package com.gmail.vsyniakin;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class BaseToXML {
    public static void marshalBase(Base base, File file) {
        try {
            JAXBContext jaxbC = JAXBContext.newInstance(Base.class);
            Marshaller marsh = jaxbC.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marsh.marshal(base, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Base unmarshalBase(File file) {
        try {
            JAXBContext jaxbC = JAXBContext.newInstance(Base.class);
            Unmarshaller unmarsh = jaxbC.createUnmarshaller();
            Base base = (Base) unmarsh.unmarshal(file);
            return base;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
