package org.acme.model;

import java.util.List;

public class Stream {

    private String id;
    private List<Tweet> tweetList;

    public Stream(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    public Stream() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Tweet> getTweetList() {
        return tweetList;
    }

    public void setTweetList(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    @Override
    public String toString() {
        return "Stream{" +
                "id='" + id + '\'' +
                ", tweetList=" + tweetList +
                '}';
    }
}
