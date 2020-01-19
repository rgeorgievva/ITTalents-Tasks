package harbour;

import java.time.LocalDateTime;

public class PortShipment {

    private int packageId;
    private int dockId;
    private int craneId;
    private String shipName;
    private LocalDateTime unloadingTime;

    public PortShipment() {

    }

    public PortShipment(int packageId, int dockId, int craneId, String shipName) {
        this.packageId = packageId;
        this.dockId = dockId;
        this.craneId = craneId;
        this.shipName = shipName;
        this.unloadingTime = LocalDateTime.now();
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getDockId() {
        return dockId;
    }

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    public int getCraneId() {
        return craneId;
    }

    public void setCraneId(int craneId) {
        this.craneId = craneId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public LocalDateTime getUnloadingTime() {
        return unloadingTime;
    }

    public void setUnloadingTime(LocalDateTime unloadingTime) {
        this.unloadingTime = unloadingTime;
    }
}

