package matt.pas.readstack.domain.api;

import java.time.LocalDateTime;

public class CommentFullInfo {
    private Integer id;
    private Integer discoveryId;
    private String userName;
    private String commentContent;
    private LocalDateTime dateAdded;

    public CommentFullInfo(Integer id, Integer discoveryId, String userName, String commentContent, LocalDateTime dateAdded) {
        this.id = id;
        this.discoveryId = discoveryId;
        this.userName = userName;
        this.commentContent = commentContent;
        this.dateAdded = dateAdded;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDiscoveryId() {
        return discoveryId;
    }

    public String getUserName() {
        return userName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }
}
