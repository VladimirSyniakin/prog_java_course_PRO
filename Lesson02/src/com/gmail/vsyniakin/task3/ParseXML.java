package com.gmail.vsyniakin.task3;

import com.google.gson.Gson;

import javax.xml.bind.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ParseXML {

    public static String reader(String urlString) {
        StringBuilder strBuild = new StringBuilder();
        try {
            URLConnection urlC = new URL(urlString).openConnection();
            try (BufferedReader bfr = new BufferedReader(new InputStreamReader(urlC.getInputStream()))) {
                String str;
                while ((str = bfr.readLine())!= null){
                    strBuild.append(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }  catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return strBuild.toString();
    }

    public static OutputStream marshallerXML(Query query, OutputStream os) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Query.class);
            Marshaller marsh = jc.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marsh.marshal(query, os);

            return os;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Query unmarshallerXML (String urlString){

        try {

            JAXBContext jc = JAXBContext.newInstance(Query.class);
            Unmarshaller unmarsh = jc.createUnmarshaller();
            URL url = new URL (urlString);
            Query query = (Query) unmarsh.unmarshal(url);
            return query;

        } catch (MalformedURLException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }


}
