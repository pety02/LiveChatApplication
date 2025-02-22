package com.example.livechat.entites;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "MESSAGE", schema = "public", catalog = "LiveChatDB")
public class MessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "from_user")
    private Integer fromUser;
    @Basic
    @Column(name = "to_user")
    private Integer toUser;
    @Basic
    @Column(name = "sent_date")
    private Timestamp sentDate;
    @Basic
    @Column(name = "content")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUser() {
        return fromUser;
    }

    public void setFromUser(Integer fromUser) {
        this.fromUser = fromUser;
    }

    public Integer getToUser() {
        return toUser;
    }

    public void setToUser(Integer toUser) {
        this.toUser = toUser;
    }

    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity that = (MessageEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fromUser, that.fromUser) && Objects.equals(toUser, that.toUser) && Objects.equals(sentDate, that.sentDate) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromUser, toUser, sentDate, content);
    }
}
