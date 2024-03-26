package matt.pas.readstack.domain.api;

import matt.pas.readstack.domain.comment.Comment;
import matt.pas.readstack.domain.comment.CommentDao;
import matt.pas.readstack.domain.user.UserDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentService {
    CommentMapper commentMapper = new CommentMapper();
    CommentDao commentDao = new CommentDao();

    public void addComment(CommentSaveRequest commentSaveRequest) {
        final Comment commentToSave = commentMapper.map(commentSaveRequest);
        commentDao.addComment(commentToSave);
    }

    public List<CommentFullInfo> findCommentsByDiscoveryId(int discoveryId) {
        return commentDao.findByDiscoveryId(discoveryId).stream()
                .map(commentMapper::map)
                .collect(Collectors.toList());
    }
    public CommentFullInfo findCommentById(int commentId) {
        final Comment comment = commentDao.findCommentById(commentId);
        return commentMapper.map(comment);
    }
    public void editComment(int commentId, String commentContent) {
        commentDao.editComment(commentId, commentContent);
    }
    public void deleteComment(int commentId) {
        commentDao.deleteComment(commentId);
    }

    private class CommentMapper{
        UserDao userDao = new UserDao();

        Comment map(CommentSaveRequest commentSaveRequest) {
            return new Comment(
                   userDao.findByUsername(commentSaveRequest.getUserName()).orElseThrow().getId(),
                    commentSaveRequest.getDiscoveryId(),
                    commentSaveRequest.getCommentContent(),
                    LocalDateTime.now()
            );
        }
        CommentFullInfo map(Comment comment) {
            return new CommentFullInfo(
                    comment.getId(),
                    comment.getDiscoveryId(),
                    userDao.findById(comment.getUserId()).orElseThrow().getUsername(),
                    comment.getCommentContent(),
                    comment.getDateAdded()
            );
        }
    }
}
