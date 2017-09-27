package com.gmail.vsyniakin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MapAnswerElement {

    @XmlElement (name="answer")
    private Answer key;
    @XmlElement
    private int value;

    public MapAnswerElement(Answer key, int value) {
        this.key = key;
        this.value = value;
    }

    public MapAnswerElement() {
    }

    public Answer getKey() {
        return key;
    }

    public void setKey(Answer key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
