package com.ufrn.hibersafe.api.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    private Long id;

    @Column(name="isAccepted")
    private boolean isAccepted;

    @Column
    private int score;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Question question;

    @Column(name="creationDate")
    private LocalDateTime creationDate;

    @Column(name="lastActivityDate")
    private LocalDateTime lastActivityDate;

    public Answer() {
    }

    public Answer(Long id, boolean isAccepted, int score, String body, Question question, LocalDateTime creationDate,
            LocalDateTime lastActivityDate) {
        this.id = id;
        this.isAccepted = isAccepted;
        this.score = score;
        this.body = body;
        this.question = question;
        this.creationDate = creationDate;
        this.lastActivityDate = lastActivityDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(LocalDateTime lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", isAccepted='" + isAccepted() + "'" + ", score='" + getScore() + "'"
                + ", body='" + getBody() + "'" + ", question='" + getQuestion() + "'" + ", creationDate='"
                + getCreationDate() + "'" + ", lastActivityDate='" + getLastActivityDate() + "'" + "}";
    }

}
