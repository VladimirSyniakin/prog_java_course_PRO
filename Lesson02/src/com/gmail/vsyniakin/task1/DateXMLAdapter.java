package com.gmail.vsyniakin.task1;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class DateXMLAdapter extends XmlAdapter <String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        LocalDate date = LocalDate.parse(v,Train.dateFormate());
        return date;
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.format(Train.dateFormate());
    }
}
