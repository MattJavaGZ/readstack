package matt.pas.readstack.domain.comment;

import matt.pas.readstack.domain.common.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends BaseDao {

    public void addComment(Comment comment) {
        String sql = """
                INSERT INTO
                    comment
                    (user_id, discovery_id, comment_content, date_added)
                VALUES
                    (?,?,?,?)
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, comment.getUserId());
            statement.setInt(2, comment.getDiscoveryId());
            statement.setString(3, comment.getCommentContent());
            statement.setObject(4, comment.getDateAdded());
            statement.executeUpdate();
            final ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                comment.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Comment> findByDiscoveryId(int discoveryId){
        List<Comment> commentsList = new ArrayList<>();
        String sql = """
                SELECT
                    id, user_id, discovery_id, comment_content, date_added
                FROM
                    comment
                WHERE
                    discovery_id=?
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, discoveryId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commentsList.add(map(resultSet));
            }
            return commentsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Comment findCommentById(int commentId){
        String sql = """
                SELECT
                    id, user_id, discovery_id, comment_content, date_added
                FROM
                    comment
                WHERE
                    id=?
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, commentId);
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editComment(int commentId, String commentContent) {
        String sql = """
                UPDATE
                    comment
                SET
                    comment_content=?
                WHERE
                    id=?
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, commentContent);
            statement.setInt(2, commentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteComment(int commentId) {
        String sql = """
                DELETE FROM
                    comment
                WHERE
                    id=?
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, commentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Integer> discoveriesIdByUserComment(int userId){
        List<Integer> discoveriesList = new ArrayList<>();
        String sql = """
                SELECT
                    discovery_id
                FROM
                    comment
                WHERE
                    user_id=?
                """;

        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, userId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final int discoveryId = resultSet.getInt("discovery_id");
                if (!discoveriesList.contains(discoveryId)) {
                    discoveriesList.add(discoveryId);
                }
            }
            return discoveriesList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Comment map(ResultSet resultSet) throws SQLException {
        final int id = resultSet.getInt("id");
        final int userId = resultSet.getInt("user_id");
        final int discoveryId = resultSet.getInt("discovery_id");
        final String commentContent = resultSet.getString("comment_content");
        final LocalDateTime dateAdded = resultSet.getTimestamp("date_added").toLocalDateTime();
        return new Comment(id, userId, discoveryId, commentContent, dateAdded);

    }
}
