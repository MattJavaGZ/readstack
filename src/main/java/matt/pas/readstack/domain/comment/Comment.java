package matt.pas.readstack.domain.comment;

import java.time.LocalDateTime;

public class Comment {

    private  Integer id;
    private Integer userId;
    private Integer discoveryId;
    private String commentContent;
    private LocalDateTime dateAdded;

    public Comment(Integer userId, Integer discoveryId, String commentContent, LocalDateTime dateAdded) {
        this.userId = userId;
        this.discoveryId = discoveryId;
        this.commentContent = commentContent;
        this.dateAdded = dateAdded;
    }

    public Comment(Integer id, Integer userId, Integer discoveryId, String commentContent, LocalDateTime dateAdded) {
        this(userId, discoveryId, commentContent, dateAdded);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getDiscoveryId() {
        return discoveryId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
