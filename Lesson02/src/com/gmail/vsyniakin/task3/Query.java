package com.gmail.vsyniakin.task3;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Query {


    private int count;
    private String created;
    private String lang;

    @XmlElement (name = "results")
    private Results results;

    @XmlAttribute(name = "count")
    public int getCount() {
        return count;
    }

    @XmlAttribute(namespace = "http://www.yahooapis.com/v1/base.rng", name = "created")
    public String getCreated() {
        return created;
    }

    @XmlAttribute(namespace = "http://www.yahooapis.com/v1/base.rng", name = "lang")
    public String getLang() {
        return lang;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "Query{" +
                "count=" + count +
                ", created='" + created + '\'' +
                ", lang='" + lang + '\'' +
                ", results=" + results +
                '}';
    }
}
