package com.gmail.vsyniakin.task1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class WorkWithXML {

    public static Trains loadFromFile (File file) {
        Trains trains = new Trains();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            Element root = doc.getDocumentElement();
            NodeList trainsList = root.getChildNodes();
            for (int i=0; i < trainsList.getLength(); i++) {
                Node node = trainsList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    Train train = elementToTrain(element);
                    if (train != null){
                        trains.addTrain(train);
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        }
        return trains;
    }

    private static Train elementToTrain(Element element) {
        Train train = new Train();

        train.setId(Integer.parseInt(element.getAttribute("id")));
        Train.setTempId(train.getId());

        train.setFrom(element.getElementsByTagName("from").item(0).getTextContent());
        train.setTo(element.getElementsByTagName("to").item(0).getTextContent());

        String dateStr = element.getElementsByTagName("date").item(0).getTextContent();
        String timeStr = element.getElementsByTagName("departure").item(0).getTextContent();

        try {
            train.setDate(LocalDate.parse(dateStr,Train.dateFormate()));
            train.setDeparture(LocalTime.parse(timeStr,Train.timeFormate()));
        } catch (Exception e) {
                System.out.println("Date or time format is not correct!");
                return null;
        }
        return train;
    }

      public static void saveAllTrainsToFile (Trains trains, String fileName){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            Element element = document.createElement("trains");
            document.appendChild(element);
            for (Train train:trains.getArrayListTrains()) {
                Element trainElem = trainToElement(train,document);
                element.appendChild(trainElem);
            }
            TransformerFactory transformerF = TransformerFactory.newInstance();
            Transformer transformer = transformerF.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult strRes = new StreamResult(fileName);

            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(source, strRes);

        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e){
            e.printStackTrace();
        }

    }

    private static Element trainToElement (Train train, Document document){
        Element element = document.createElement("train");

        element.setAttribute("id", String.valueOf(train.getId()));

        Element fromElem = document.createElement("from");
        fromElem.setTextContent(train.getFrom());

        Element toElem = document.createElement("to");
        toElem.setTextContent(train.getTo());

        Element dateElem = document.createElement("date");
        dateElem.setTextContent(train.getDate().format(Train.dateFormate()));
        Element timeElem = document.createElement("departure");
        timeElem.setTextContent(train.getDeparture().format(Train.timeFormate()));

        element.appendChild(fromElem);
        element.appendChild(toElem);
        element.appendChild(dateElem);
        element.appendChild(timeElem);

        return element;
    }

    public static void saveTrainToFile (Train train, File file) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.parse(file);

            Element trainsElem = document.getDocumentElement();
            trainsElem.appendChild(trainToElement(train,document));

            TransformerFactory transformerF = TransformerFactory.newInstance();
            Transformer transformer = transformerF.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult strRes = new StreamResult(file);

            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(source, strRes);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }




    



}
