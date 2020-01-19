package harbour;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeSet;

public class Statistician extends Thread {

    private static int reportNumber = 1;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(6000);//24 hours
                writeStatisticsToFile();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
    }

    private void writeStatisticsToFile() throws SQLException {
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");
            String fileName = "report-" + (reportNumber++) + "-" + time.format(formatter);
            File file = new File(fileName);

            try (
                    FileOutputStream fos = new FileOutputStream(file, true);
                    PrintStream ps = new PrintStream(fos);
                    ){
                Map<Integer, TreeSet<PortShipment>> parcels = PortShipmentDAO.getInstance().unloadedParcelsByDocks();
                for (Map.Entry<Integer, TreeSet<PortShipment>> e : parcels.entrySet()) {
                    ps.println("Dock " + e.getKey() + ":");
                    for (PortShipment shipment : e.getValue()) {
                        ps.println("\tshipment " + shipment.getPackageId() + ", ship " + shipment.getShipName() +
                                ", crane " + shipment.getCraneId() + ", " + shipment.getUnloadingTime());
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            } catch (IOException e) {
                System.out.println("Error while writing to file: " + e.getMessage());
            }

    }
}
