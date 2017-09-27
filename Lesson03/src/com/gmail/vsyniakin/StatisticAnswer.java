package com.gmail.vsyniakin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


@XmlAccessorType(XmlAccessType.FIELD)
public class StatisticAnswer {

    @XmlElement(name = "answers")
    @XmlJavaTypeAdapter(MapStatisticAdapter.class)
    private HashMap<Answer, AtomicInteger> statisticUserAnswer;
    @XmlElement(name = "questions")
    private HashMap<Integer, Question> someQuestions;

    public void loadQuestions() {
        statisticUserAnswer = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                statisticUserAnswer.put(someQuestions.get(i).getAnswers().get(j), new AtomicInteger());
            }
        }
    }

    public void addAnswers(String questionOne, String questionTwo, String questionThree) {
        if (statisticUserAnswer == null) {
            loadQuestions();
        }
        Answer answer;
        if (questionOne != null) {
            answer = someQuestions.get(0).getAnswers().get(Integer.parseInt(questionOne));
            statisticUserAnswer.get(answer).incrementAndGet();
        }
        if (questionTwo != null) {
            answer = someQuestions.get(1).getAnswers().get(Integer.parseInt(questionTwo));
            statisticUserAnswer.get(answer).incrementAndGet();
        }
        if (questionThree != null) {
            answer = someQuestions.get(2).getAnswers().get(Integer.parseInt(questionThree));
            statisticUserAnswer.get(answer).incrementAndGet();
        }
    }

    public HashMap<Answer, AtomicInteger> getStatisticUserAnswer() {
        return statisticUserAnswer;
    }

    public void setStatisticUserAnswer(HashMap<Answer, AtomicInteger> statisticUserAnswer) {
        this.statisticUserAnswer = statisticUserAnswer;
    }

    public HashMap<Integer, Question> getSomeQuestions() {
        return someQuestions;
    }

    public void setSomeQuestions(HashMap<Integer, Question> someQuestions) {
        this.someQuestions = someQuestions;
    }

    @Override
    public String toString() {
        return "StatisticAnswer{" +
                "statisticUserAnswer=" + statisticUserAnswer +
                "}";
    }
}
