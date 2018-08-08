package by.academy.it.entity;

import java.io.Serializable;

/**
 * Representation of database table 'results'
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 8270753393039084570L;

    private Integer id;
    private Integer matchId;
    private String result;
    private Integer winnerId;
    private Integer loserId;
    private Integer winnerGoals;
    private Integer loserGoals;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Integer winnerId) {
        this.winnerId = winnerId;
    }

    public Integer getLoserId() {
        return loserId;
    }

    public void setLoserId(Integer loserId) {
        this.loserId = loserId;
    }

    public Integer getWinnerGoals() {
        return winnerGoals;
    }

    public void setWinnerGoals(Integer winnerGoals) {
        this.winnerGoals = winnerGoals;
    }

    public Integer getLoserGoals() {
        return loserGoals;
    }

    public void setLoserGoals(Integer loserGoals) {
        this.loserGoals = loserGoals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result1 = (Result) o;

        if (id != result1.id) return false;
        if (matchId != result1.matchId) return false;
        if (winnerId != result1.winnerId) return false;
        if (loserId != result1.loserId) return false;
        if (winnerGoals != result1.winnerGoals) return false;
        if (loserGoals != result1.loserGoals) return false;
        return result != null ? result.equals(result1.result) : result1.result == null;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + matchId;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + winnerId;
        result1 = 31 * result1 + loserId;
        result1 = 31 * result1 + winnerGoals;
        result1 = 31 * result1 + loserGoals;
        return result1;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", result='" + result + '\'' +
                ", winnerId=" + winnerId +
                ", loserId=" + loserId +
                ", winnerGoals=" + winnerGoals +
                ", loserGoals=" + loserGoals +
                '}';
    }
}
