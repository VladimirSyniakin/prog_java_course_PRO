package com.gmail.vsyniakin;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapStatisticAdapter extends XmlAdapter <MapAnswerElement [],HashMap<Answer, AtomicInteger>>{
    @Override
    public HashMap<Answer, AtomicInteger> unmarshal(MapAnswerElement[] v) throws Exception {
        HashMap<Answer, AtomicInteger> mapAnswer = new HashMap<>();

        for (int i = 0; i<v.length; i++){
            mapAnswer.put(v[i].getKey(),new AtomicInteger(v[i].getValue()));
        }
        return mapAnswer;
    }

    @Override
    public MapAnswerElement[] marshal(HashMap<Answer, AtomicInteger> v) throws Exception {
        MapAnswerElement [] arrayAnswer = new MapAnswerElement[v.size()];
        int i = 0;
        for (Answer answer:v.keySet()) {
            arrayAnswer[i] = new MapAnswerElement(answer,v.get(answer).intValue());
            i++;
        }
        return arrayAnswer;
    }
}
