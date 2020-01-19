package harbour;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class PortShipmentDAO {

    private static PortShipmentDAO instance = new PortShipmentDAO();

    private PortShipmentDAO() {

    }

    public static PortShipmentDAO getInstance() {
        return instance;
    }

    public void saveParcelInfo(PortShipment portShipment) throws SQLException {
        Connection connection = DBManager.INSTANCE.getConnection();
        String sql = "INSERT INTO port_shipments (boat_name, dock_id, crane_id, unloading_time, package_id) " +
                "VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, portShipment.getShipName());
            statement.setInt(2, portShipment.getDockId());
            statement.setInt(3, portShipment.getCraneId());
            statement.setTimestamp(4, Timestamp.valueOf(portShipment.getUnloadingTime()));
            statement.setInt(5, portShipment.getPackageId());

            statement.executeUpdate();
        }
    }

    public Map<Integer, TreeSet<PortShipment>> unloadedParcelsByDocks() throws SQLException {
        Map<Integer, TreeSet<PortShipment>> unloadedParcels = new TreeMap<>();

        Connection connection = DBManager.INSTANCE.getConnection();
        String sql = "SELECT dock_id, package_id, crane_id, boat_name, unloading_time FROM port_shipments;";



        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                PortShipment portShipment = new PortShipment();
                portShipment.setDockId(result.getInt("dock_id"));
                portShipment.setCraneId(result.getInt("crane_id"));
                portShipment.setPackageId(result.getInt("package_id"));
                portShipment.setShipName(result.getString("boat_name"));
                Timestamp time = result.getTimestamp("unloading_time");
                portShipment.setUnloadingTime(time.toLocalDateTime());
                int dock = portShipment.getDockId();

                if (!unloadedParcels.containsKey(dock)) {
                    unloadedParcels.put(dock, new TreeSet<>((s1, s2) -> {
                        return s1.getUnloadingTime().compareTo(s2.getUnloadingTime());
                    }));
                }
                unloadedParcels.get(dock).add(portShipment);
            }
        }
        return unloadedParcels;
    }

}
