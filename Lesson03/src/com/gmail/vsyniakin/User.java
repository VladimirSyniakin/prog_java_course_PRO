package com.gmail.vsyniakin;

import javax.xml.bind.annotation.*;

@XmlAccessorType (XmlAccessType.FIELD)
public class User {
    @XmlElement
    private String login;
    @XmlElement
    private String password;
    @XmlElement (name="userStatistic")
    private StatisticAnswer statisticAnswer;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public StatisticAnswer getStatisticAnswer() {
        return statisticAnswer;
    }

    public void setStatisticAnswer(StatisticAnswer statisticAnswer) {
        this.statisticAnswer = statisticAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return login != null ? login.equals(user.login) : user.login == null;
    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
