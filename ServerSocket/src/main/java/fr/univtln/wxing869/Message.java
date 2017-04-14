package fr.univtln.wxing869;

import java.util.Date;

/**
 * Created by wenlixing on 23/02/2017.
 */
public class Message {
    private String content;
    private String sender;
    private Date received;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }
}
