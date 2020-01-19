package coloring.db;

import coloring.Egg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class EggDAO {

    private static EggDAO instance = new EggDAO();

    private EggDAO() {

    }

    public static EggDAO getInstance() {
        return instance;
    }

    public void saveEggInfo(Egg egg) throws SQLException {
        Connection connection = DBManager.INSTANCE.getConnection();
        String sql = "INSERT INTO eggs (type, colour, is_colourful, child, date)" +
                " VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, egg.getType().name());
            statement.setString(2, egg.getColor().name());
            statement.setBoolean(3, egg.isColourful());
            statement.setString(4, egg.getChild().getName());
            Timestamp time = Timestamp.valueOf(egg.getColouringTime());
            statement.setTimestamp(5, time);
            statement.executeUpdate();
        }
    }
}
