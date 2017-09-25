package com.gmail.vsyniakin.task3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement (name = "results")
public class Results {

    @XmlElement (name = "rate")
    private Rate [] rate;

    public void setRate(Rate[] rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Results{" +
                "rate=" + Arrays.toString(rate) +
                '}';
    }
}
