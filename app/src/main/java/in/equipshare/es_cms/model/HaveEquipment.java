package in.equipshare.es_cms.model;

import java.io.Serializable;

public class HaveEquipment implements Serializable {

    private String equipmentName;
    private String startDate;
    private String endDate;
    private String rate;
    private String brief;

    public HaveEquipment() {
    }

    public HaveEquipment(String equipmentName, String startDate, String endDate, String rate, String brief) {
        this.equipmentName = equipmentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
        this.brief = brief;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
