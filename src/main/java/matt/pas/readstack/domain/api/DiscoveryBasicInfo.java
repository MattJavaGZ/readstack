package matt.pas.readstack.domain.api;

import java.time.LocalDateTime;
import java.util.Objects;

public class DiscoveryBasicInfo {

    private Integer id;
    private String title;
    private String url;
    private String description;
    private LocalDateTime dateAdded;
    private int voteCount;
    private String author;
    private int isFavorite; //1-nie , 2- ulubione
    private int visitCounter;


    public DiscoveryBasicInfo(Integer id, String title, String url, String description, LocalDateTime dateAdded, int voteCount, String author, int visitCounter) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.dateAdded = dateAdded;
        this.voteCount = voteCount;
        this.author = author;
        this.isFavorite = 1;
        this.visitCounter = visitCounter;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getAuthor() {
        return author;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getVisitCounter() {
        return visitCounter;
    }

    public void setVisitCounter(int visitCounter) {
        this.visitCounter = visitCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscoveryBasicInfo that = (DiscoveryBasicInfo) o;
        return voteCount == that.voteCount && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(url, that.url) && Objects.equals(description, that.description) && Objects.equals(dateAdded, that.dateAdded) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, url, description, dateAdded, voteCount, author);
    }
}
