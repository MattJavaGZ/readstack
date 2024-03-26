package matt.pas.readstack.domain.api;

public class CommentSaveRequest {

    private Integer discoveryId;
    private String userName;
    private String commentContent;

    public CommentSaveRequest(Integer discoveryId, String userName, String commentContent) {
        this.discoveryId = discoveryId;
        this.userName = userName;
        this.commentContent = commentContent;
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
}
