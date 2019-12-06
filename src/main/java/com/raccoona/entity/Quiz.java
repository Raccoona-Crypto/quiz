package com.raccoona.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Quizes",
        indexes = {@Index(name = "phrase_idx", columnList = "phrase")})
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phrase;
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "redeem_id", referencedColumnName = "id")
    private Redeem redeem;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reward_id", referencedColumnName = "id")
    private Reward reward;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Redeem getRedeem() {
        return redeem;
    }

    public void setRedeem(Redeem redeem) {
        this.redeem = redeem;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(getId(), quiz.getId()) &&
                Objects.equals(getPhrase(), quiz.getPhrase()) &&
                Objects.equals(getStatus(), quiz.getStatus()) &&
                Objects.equals(getRedeem(), quiz.getRedeem()) &&
                Objects.equals(getReward(), quiz.getReward());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPhrase(), getStatus(), getRedeem(), getReward());
    }
}
