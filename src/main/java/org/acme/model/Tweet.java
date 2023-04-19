package org.acme.model;

import org.bson.Document;

import java.util.Date;

public class Tweet {

    private String id;
    private User user;
    private String texto;
    private Date date;

    public Tweet(User user, String texto, Date date) {
        this.user = user;
        this.texto = texto;
        this.date = date;
    }

    public Tweet(Document document) {
        setId(document.getString("_id"));
        setTexto(document.getString("texto"));
        setDate((document.getDate("date")));
        User userTweet = new User();
        Document documentUser = (Document) document.get("user");
        userTweet.setId(documentUser.getString("_id"));
        userTweet.setName(documentUser.getString("name"));
        userTweet.setEmail(documentUser.getString("email"));
        userTweet.setPassword(documentUser.getString("password"));
        setUser(user);
    }

    public Tweet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", texto='" + texto + '\'' +
                ", date=" + date +
                '}';
    }
}
