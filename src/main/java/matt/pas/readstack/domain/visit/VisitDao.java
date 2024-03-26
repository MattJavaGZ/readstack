package matt.pas.readstack.domain.visit;

import matt.pas.readstack.domain.common.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VisitDao extends BaseDao {

    public void saveVisit(Visit visit) {
        String sql = """
                INSERT INTO
                    visit (discovery_id, visit_counter)
                VALUES
                    (?,?)
                ON DUPLICATE KEY UPDATE
                    visit_counter=?
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, visit.getDiscoveryId());
            statement.setInt(2, visit.getVisitCounter());
            int visitCounter = visitCounterByDiscoveryId(visit.getDiscoveryId()).orElse(0);
            visitCounter++;
            statement.setInt(3, visitCounter);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<Integer> visitCounterByDiscoveryId(int discoveryId){
        String sql = """
                SELECT
                    visit_counter
                FROM
                    visit
                WHERE
                    discovery_id=?
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, discoveryId);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return Optional.of(resultSet.getInt(1));
            else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
