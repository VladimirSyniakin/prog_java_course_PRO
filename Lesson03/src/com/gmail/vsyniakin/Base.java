package com.gmail.vsyniakin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.HashSet;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Base {
    @XmlElement(name = "question")
    private HashMap<Integer, Question> someQuestions;
    @XmlElement(name = "user")
    private HashSet<User> users;
    @XmlElement(name = "statistic")
    private StatisticAnswer statisticAllUser;

    public void createNewBase() {
        if (someQuestions == null) {
            standardBaseQuestion();
        }
        if (users == null) {
            users = new HashSet<>();
        }
        if (statisticAllUser == null) {
            statisticAllUser = new StatisticAnswer();
            statisticAllUser.setSomeQuestions(someQuestions);
        }
    }

    public void standardBaseQuestion() {

        someQuestions = new HashMap<>();

        String questionStr = "Как будет на зулуском языке слово: привет?";
        HashMap<Integer, Answer> answers = new HashMap<>();
        answers.put(0, new Answer("Szia"));
        answers.put(1, new Answer("Pozdravljeni"));
        answers.put(2, new Answer("Sawubona"));
        Question question = new Question(questionStr, answers);
        someQuestions.put(0, question);

        questionStr = "Сколько нужно часов, что бы сварить страусиное яйцо в крутую?";
        answers = new HashMap<>();
        answers.put(0, new Answer("3 часа"));
        answers.put(1, new Answer("4 часа"));
        answers.put(2, new Answer("менее 1 часа"));
        question = new Question(questionStr, answers);
        someQuestions.put(1, question);

        questionStr = "Какой международный телефонный код Антарктиды?";
        answers = new HashMap<>();
        answers.put(0, new Answer("672"));
        answers.put(1, new Answer("747"));
        answers.put(2, new Answer("458"));
        question = new Question(questionStr, answers);
        someQuestions.put(2, question);
    }

    public User searchUser(String login) {
        try {
            for (User user : users) {
                if (user.getLogin().equals(login)) {
                    return user;
                }
            }
        } catch (NullPointerException e) {

        }
        return null;
    }

    public User addUser(String login, String password) {

        if (searchUser(login) == null) {
            User user = new User(login, password);
            user.setStatisticAnswer(new StatisticAnswer());
            user.getStatisticAnswer().setSomeQuestions(someQuestions);
            user.getStatisticAnswer().loadQuestions();
            users.add(user);
            return user;
        }
        return null;
    }

    public boolean checkPassword(String login, String password) {
        User user = searchUser(login);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public HashMap<Integer, Question> getSomeQuestions() {
        return someQuestions;
    }

    public void setSomeQuestions(HashMap<Integer, Question> someQuestions) {
        this.someQuestions = someQuestions;
    }

    public HashSet<User> getUsers() {
        return users;
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }

    public StatisticAnswer getStatisticAllUser() {
        return statisticAllUser;
    }

    public void setStatisticAllUser(StatisticAnswer statisticAllUser) {
        this.statisticAllUser = statisticAllUser;
    }
}
