package matt.pas.readstack.domain.api;

import matt.pas.readstack.domain.vote.Vote;

public class DiscoveryVote {

    private String userName;
    private Integer discoveryId;
    private String type;

    public DiscoveryVote(String userName, Integer discovery_id, String type) {
        this.userName = userName;
        this.discoveryId = discovery_id;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getDiscoveryId() {
        return discoveryId;
    }

    public String getType() {
        return type;
    }
}
