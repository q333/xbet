package by.academy.it.entity;

import java.io.Serializable;

/**
 * Representation of database table 'bets'
 */
public class Bet implements Serializable {

    private static final long serialVersionUID = -8902292860220708529L;

    private Integer id;
    private Integer user_id;
    private Integer match_id;
    private String betResult;
    private Double bet;
    private Integer money;
    private String status;
    private Match match;

    public String getBetResult() {
        return betResult;
    }

    public void setBetResult(String betResult) {
        this.betResult = betResult;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Integer match_id) {
        this.match_id = match_id;
    }

    public Double getBet() {
        return bet;
    }

    public void setBet(Double bet) {
        this.bet = bet;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bet bet1 = (Bet) o;

        if (id != null ? !id.equals(bet1.id) : bet1.id != null) return false;
        if (user_id != null ? !user_id.equals(bet1.user_id) : bet1.user_id != null) return false;
        if (match_id != null ? !match_id.equals(bet1.match_id) : bet1.match_id != null) return false;
        if (betResult != null ? !betResult.equals(bet1.betResult) : bet1.betResult != null) return false;
        if (bet != null ? !bet.equals(bet1.bet) : bet1.bet != null) return false;
        if (money != null ? !money.equals(bet1.money) : bet1.money != null) return false;
        if (status != null ? !status.equals(bet1.status) : bet1.status != null) return false;
        return match != null ? match.equals(bet1.match) : bet1.match == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (match_id != null ? match_id.hashCode() : 0);
        result = 31 * result + (betResult != null ? betResult.hashCode() : 0);
        result = 31 * result + (bet != null ? bet.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (match != null ? match.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", match_id=" + match_id +
                ", betResult='" + betResult + '\'' +
                ", bet=" + bet +
                ", money=" + money +
                ", status='" + status + '\'' +
                '}';
    }
}
