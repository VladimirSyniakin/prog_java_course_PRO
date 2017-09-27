package com.gmail.vsyniakin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.HashMap;
import java.util.HashSet;

@XmlAccessorType(XmlAccessType.FIELD)
public class Question extends HashSet<Question> {

    @XmlElement(name = "question")
    private String question;
    @XmlElement(name = "answers")
    private HashMap<Integer, Answer> answers;

    public Question(String question, HashMap<Integer, Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public HashMap<Integer, Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<Integer, Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
