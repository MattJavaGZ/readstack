package matt.pas.readstack.domain.vote;

import java.time.LocalDateTime;

public class Vote {

    Integer userId;
    Integer discoveryId;
    Type type;
    LocalDateTime dateAdded;

    public Vote(Integer userId, Integer discoveryId, Type type, LocalDateTime dateAdded) {
        this.userId = userId;
        this.discoveryId = discoveryId;
        this.type = type;
        this.dateAdded = dateAdded;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getDiscoveryId() {
        return discoveryId;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public enum Type{
        UP, DOWN;
    }
}
