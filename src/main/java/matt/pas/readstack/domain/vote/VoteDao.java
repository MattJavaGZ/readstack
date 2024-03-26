package matt.pas.readstack.domain.vote;

import matt.pas.readstack.domain.common.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoteDao extends BaseDao {

    public void save(Vote vote) {
        String sql = """
                INSERT INTO 
                    vote (user_id, discovery_id, type, date_added)
                VALUES
                    (?,?,?,?)
                ON DUPLICATE KEY UPDATE
                    type=?
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1,vote.getUserId());
            statement.setInt(2, vote.getDiscoveryId());
            statement.setString(3, vote.getType().toString());
            statement.setObject(4, vote.getDateAdded());
            statement.setString(5, vote.getType().toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int countByDiscoveryId(int discoveryId){
        String sql = """
                  SELECT
                     (SELECT COUNT(discovery_id) FROM vote WHERE discovery_id = ? AND type = 'UP')
                     -
                     (SELECT COUNT(discovery_id) FROM vote WHERE discovery_id = ? AND type = 'DOWN')
                     AS
                     vote_count;
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, discoveryId);
            statement.setInt(2, discoveryId);
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("vote_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Integer> discoveriesIdByUserVote(int userId) {
        List<Integer> userIdList = new ArrayList<>();
        String sql = """
                SELECT 
                    discovery_id
                FROM
                    vote
                WHERE
                    user_id=?
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, userId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                userIdList.add(resultSet.getInt(1));
            }
            return userIdList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
