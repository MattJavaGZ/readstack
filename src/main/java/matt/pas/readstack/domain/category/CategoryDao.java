package matt.pas.readstack.domain.category;

import matt.pas.readstack.config.DataSourceProvider;
import matt.pas.readstack.domain.common.BaseDao;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDao extends BaseDao {

    public Optional<Category> findById(int categoryId) {
        String sql = "SELECT id, name, description FROM category WHERE id=?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, categoryId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final Category category = mapRow(resultSet);
                return Optional.of(category);
            } else
                return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Category> findAll() {
        List<Category> allCategories = new ArrayList<>();
        String sql = "SELECT id, name, description FROM category";
        try (final Connection connection = getConnection();
             final Statement statement = connection.createStatement())
        {
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                final Category category = mapRow(resultSet);
                allCategories.add(category);
            }

            return allCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Category mapRow(ResultSet resultSet) throws SQLException {
        final int id = resultSet.getInt("id");
        final String name = resultSet.getString("name");
        final String description = resultSet.getString("description");
        return new Category(id, name, description);
    }
}
