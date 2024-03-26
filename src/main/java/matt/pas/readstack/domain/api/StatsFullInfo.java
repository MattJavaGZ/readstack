package matt.pas.readstack.domain.api;

public class StatsFullInfo {
    private int usersCount;
    private int discoveryCount;
    private int commentsCount;
    private int voteCount;

    public StatsFullInfo(int usersCount, int discoveryCount, int commentsCount, int voteCount) {
        this.usersCount = usersCount;
        this.discoveryCount = discoveryCount;
        this.commentsCount = commentsCount;
        this.voteCount = voteCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public int getDiscoveryCount() {
        return discoveryCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
