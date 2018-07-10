package in.equipshare.es_cms.model;

public class HaveEquipment {

    private String equipmentName;
    private String date;
    private String rate;

    public HaveEquipment() {
    }

    public HaveEquipment(String equipmentName, String date, String rate) {
        this.equipmentName = equipmentName;
        this.date = date;
        this.rate = rate;
    }


    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
