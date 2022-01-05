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

    public static final String INSERT_STATEMENT = "INSERT INTO NAME_SAVES (id, name, wins) VALUES (1, ?, ?);";
    public static final String SELECT_STATEMENT = "SELECT * FROM NAME_SAVES WHERE id = 1;";

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
    public void update(Integer id, String name, Integer victory) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, victory.toString());
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
