package in.equipshare.es_cms.model;

public class QuotationRequests {

    String companyName;
    String equipmentName;
    String location;

    public QuotationRequests() {
    }

    public QuotationRequests(String companyName, String equipmentName, String location) {
        this.companyName = companyName;
        this.equipmentName = equipmentName;
        this.location = location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
