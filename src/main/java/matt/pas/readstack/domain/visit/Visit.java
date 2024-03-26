package matt.pas.readstack.domain.visit;

public class Visit {

    private Integer discoveryId;
    private int visitCounter;

    public Visit(Integer discoveryId) {
        this.discoveryId = discoveryId;
        this.visitCounter = 1;
    }


    public Integer getDiscoveryId() {
        return discoveryId;
    }

    public int getVisitCounter() {
        return visitCounter;
    }

    public void setVisitCounter(int visitCounter) {
        this.visitCounter = visitCounter;
    }
}
