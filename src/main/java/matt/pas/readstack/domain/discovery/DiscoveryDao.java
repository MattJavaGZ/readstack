package matt.pas.readstack.domain.discovery;

import matt.pas.readstack.config.DataSourceProvider;
import matt.pas.readstack.domain.common.BaseDao;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiscoveryDao extends BaseDao {


    public List<Discovery> findByCategory(int categoryId) {
        List<Discovery> discoveries = new ArrayList<>();
        String sql = """
                SELECT
                    id, title, url, description, date_added, category_id, user_id
                FROM
                    discovery
                WHERE
                    category_id=?
                """;
        try (final Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, categoryId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final Discovery discovery = mapRow(resultSet);
                discoveries.add(discovery);
            }
        return discoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Discovery> findAll () {
        List<Discovery> allDiscoveries = new ArrayList<>();
        String sql = "SELECT id, title, url, description, date_added, category_id, user_id FROM discovery";
        try (final Connection connection = getConnection();
             final Statement statement = connection.createStatement();
        ){
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                final Discovery discovery = mapRow(resultSet);
                allDiscoveries.add(discovery);
            }
            return allDiscoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Discovery>  findById(int id) {
        String sql = "SELECT id, title, url, description, date_added, category_id, user_id FROM discovery WHERE id=?";
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return Optional.of(mapRow(resultSet));
            else
                return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Discovery discovery) {
        String sql = """
                INSERT INTO
                    discovery
                (title, url, description, date_added,category_id, user_id)
                    VALUES
                (?,?,?,?,?,?)
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, discovery.getTitle());
            statement.setString(2, discovery.getUrl());
            statement.setString(3, discovery.getDescription());
            statement.setObject(4, discovery.getDateAdded());
            statement.setInt(5, discovery.getCategoryId());
            statement.setInt(6, discovery.getUserId());
            statement.executeUpdate();
            final ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()){
                discovery.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Discovery mapRow(ResultSet resultSet) throws SQLException {
        final int id = resultSet.getInt("id");
        final String title = resultSet.getString("title");
        final String url = resultSet.getString("url");
        final String description = resultSet.getString("description");
        final LocalDateTime dateAdded = resultSet.getTimestamp("date_added").toLocalDateTime();
        final int categoryId = resultSet.getInt("category_id");
        final int userId = resultSet.getInt("user_id");
        return new Discovery(id, title, url, description, dateAdded, categoryId, userId);
    }
}
