package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepository {

    private final String databaseUrl;

    public PlayerRepositoryImpl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    @Override
    public void addNewPlayer(String playerName) {
        try (Connection connection = createConnection()) {
            PreparedStatement addStatement = connection.prepareStatement("INSERT INTO players (name, score) values (?, 1)");
            addStatement.setString(1, playerName);
            addStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePlayerScore(String playerName) {
        try (Connection connection = createConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM players WHERE name=?");
            selectStatement.setString(1, playerName);
            ResultSet resultSet = selectStatement.executeQuery();
            int score = resultSet.getInt("score");
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE players SET score=? WHERE name=?");
            updateStatement.setInt(1, score + 1);
            updateStatement.setString(2, playerName);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PlayerResult> selectAll() {
        List<PlayerResult> playerResults = new ArrayList<>();

        try (Connection connection = createConnection()) {
            ResultSet resultSet = connection.prepareStatement("SELECT * FROM players").executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int score = resultSet.getInt("score");
                PlayerResult playerResult = new PlayerResult(name, score);
                playerResults.add(playerResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerResults;
    }

    private Connection createConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(databaseUrl);
        return connection;
    }
}
