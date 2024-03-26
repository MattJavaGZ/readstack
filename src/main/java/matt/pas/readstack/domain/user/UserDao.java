package matt.pas.readstack.domain.user;

import matt.pas.readstack.domain.common.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao extends BaseDao {

    public void save(User user) {
        saveUser(user);
        saveRole(user);
    }

    private void saveUser(User user) {
        String sql = """
                INSERT INTO
                    user
                (username, email, password, registration_date)
                    VALUES
                (?,?,?,?)
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setObject(4, user.getRegistrationDate());
            statement.executeUpdate();
            final ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveRole(User user) {
        String sql = """
                INSERT INTO
                    user_role
                (username)
                    VALUES
                (?)
                """;
        update(user.getUsername(), sql);
    }

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT id, username, email, password, registration_date FROM user WHERE username=?";
        try (final Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, username);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return Optional.of(mapRow(resultSet));
            else
                return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findById(int userId) {
        String sql = """
                SELECT
                    id, username, email, password, registration_date
                FROM
                    user
                WHERE
                    id=?
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, userId);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return Optional.of(mapRow(resultSet));
            else return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = """
                SELECT
                    id, username, email, password, registration_date
                FROM
                    user
                """;
        try (Connection connection = getConnection();
             final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                final User user = mapRow(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String roleByUserName(String userName) {
        String sql = """
                SELECT
                    role_name
                FROM
                    user_role
                WHERE
                    username=?
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, userName);
            final ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("role_name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void blockUser(String userName) {
        String sql = """
                UPDATE
                    user_role
                SET
                    role_name='BLOCK'
                WHERE
                    username=?
                """;
        update(userName, sql);
    }
    public void unlockUser(String userName) {
        String sql = """
                UPDATE
                    user_role
                SET
                    role_name='USER'
                WHERE
                    username=?
                """;
        update(userName, sql);
    }
    private void update(String userName, String sql) {
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, userName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteUser (String userName, int userId) {
        deleteUserFromRole(userName);
        deleteUserFromUsers(userId);
    }
    private void deleteUserFromRole (String userName) {
        String sql = """
                DELETE FROM
                    user_role
                WHERE
                    username=?
                """;
        update(userName, sql);
    }
    private void deleteUserFromUsers(int userId) {
        String sql = """
                DELETE FROM
                    user
                WHERE
                    id=?
                """;
        try (Connection connection = getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private User mapRow(ResultSet resultSet) throws SQLException {
        final int id = resultSet.getInt("id");
        final String username1 = resultSet.getString("username");
        final String email = resultSet.getString("email");
        final String password = resultSet.getString("password");
        LocalDateTime registrationDate = resultSet.getObject("registration_date", LocalDateTime.class);
        return new User(id, username1, email, password, registrationDate);
    }
}
