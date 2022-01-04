package hu.nye.progtech.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.service.player.HumanPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ebben az osztályban a játékosok pontjait mentem adatbázisba.
 */
public class ScoreSavesRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreSavesRepository.class);

    public static final String INSERT_STATEMENT = "INSERT INTO players (name, wins) VALUES (?, ?);";
    public static final String SELECT_STATEMENT = "SELECT FROM players;";

    private final Connection connection;

    public ScoreSavesRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Itt történik az adatfeltöltés.
     *
     * @param name a játékos neve
     * @param victory a játékos győzelmánek száma
     * @throws SQLException az adatbázis miatt
     */
    public void update(String name, Integer victory) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, victory.toString());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Itt pedig a lekérdezés.
     *
     * @return a játékos adatai
     */
    public List<HumanPlayer> players() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_STATEMENT);) {
            List<HumanPlayer> playerList = new ArrayList<HumanPlayer>();
            while (resultSet.next()) {
                //playerList.add(new HumanPlayer(resultSet.getString("name"), resultSet.getInt("victory")));
            }
            return playerList;
        } catch (SQLException throwables) {
            throw new RuntimeException("The program failed!");
        }
    }

    public void close() throws Exception {
        connection.close();
    }

}
