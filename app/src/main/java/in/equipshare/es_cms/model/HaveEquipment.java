package in.equipshare.es_cms.model;

public class HaveEquipment {

    private String equipmentName;
    private String startDate;
    private String endDate;
    private String rate;

    public HaveEquipment() {
    }

    public HaveEquipment(String equipmentName, String startDate, String endDate, String rate) {
        this.equipmentName = equipmentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
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
}
