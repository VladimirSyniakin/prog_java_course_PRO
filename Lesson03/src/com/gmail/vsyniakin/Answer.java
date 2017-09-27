package com.gmail.vsyniakin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)

public class Answer {

    @XmlElement(name = "answer")
    private String answer;

    public Answer() {
    }

    public Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;

        Answer answer1 = (Answer) o;

        return answer != null ? answer.equals(answer1.answer) : answer1.answer == null;
    }

    @Override
    public int hashCode() {
        return answer != null ? answer.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "com.gmail.vsyniakin.Answer{" +
                "answer='" + answer + '\'' +
                '}';
    }
}
