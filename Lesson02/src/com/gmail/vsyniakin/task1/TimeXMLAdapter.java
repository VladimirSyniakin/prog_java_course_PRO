package com.gmail.vsyniakin.task1;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;

public class TimeXMLAdapter extends XmlAdapter<String,LocalTime>{

    @Override
    public LocalTime unmarshal(String v) throws Exception {
        LocalTime time = LocalTime.parse(v,Train.timeFormate());
        return time;
    }

    @Override
    public String marshal(LocalTime v) throws Exception {
        return v.format(Train.timeFormate());
    }
}
