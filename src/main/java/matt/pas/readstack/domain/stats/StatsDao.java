package matt.pas.readstack.domain.stats;

import matt.pas.readstack.domain.common.BaseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatsDao extends BaseDao {

    public int userCount(){
        String sql = """
                SELECT
                    (SELECT COUNT(id) FROM user)
                AS
                    count;
                """;
        return counting(sql);
    }
    public int discoveryCount(){
        String sql = """
                SELECT
                    (SELECT COUNT(id) FROM discovery)
                AS
                    count;
                """;
        return counting(sql);
    }
    public int commentCount(){
        String sql = """
                SELECT
                    (SELECT COUNT(id) FROM comment)
                AS
                    count;
                """;
        return counting(sql);
    }
    public int voteCount(){
        String sql = """
                SELECT
                    (SELECT COUNT(user_id) FROM vote)
                AS
                    count;
                """;
        return counting(sql);
    }

    private int counting(String sql) {
        try (Connection connection = getConnection();
             final Statement statement = connection.createStatement())
        {
            final ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getInt("count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
