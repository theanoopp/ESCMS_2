package in.equipshare.es_cms.model;

import java.io.Serializable;

public class NeedEquipment implements Serializable {


    private String equipmentName;
    private String equipmentQuantity;
    private String durationMonth;
    private String durationYear;
    private String brief;
    private long startDate;

    public NeedEquipment() {
    }

    public NeedEquipment(String equipmentName, String equipmentQuantity, String durationMonth, String durationYear, String brief, long startDate) {
        this.equipmentName = equipmentName;
        this.equipmentQuantity = equipmentQuantity;
        this.durationMonth = durationMonth;
        this.durationYear = durationYear;
        this.brief = brief;
        this.startDate = startDate;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentQuantity() {
        return equipmentQuantity;
    }

    public void setEquipmentQuantity(String equipmentQuantity) {
        this.equipmentQuantity = equipmentQuantity;
    }

    public String getDurationMonth() {
        return durationMonth;
    }

    public void setDurationMonth(String durationMonth) {
        this.durationMonth = durationMonth;
    }

    public String getDurationYear() {
        return durationYear;
    }

    public void setDurationYear(String durationYear) {
        this.durationYear = durationYear;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
}
