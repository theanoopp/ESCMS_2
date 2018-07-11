package in.equipshare.es_cms.model;

public class RFQSummary {

    private String companyName;
    private String rfqId;
    private String location;

    public RFQSummary() {
    }

    public RFQSummary(String companyName, String rfqId, String location) {
        this.companyName = companyName;
        this.rfqId = rfqId;
        this.location = location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRfqId() {
        return rfqId;
    }

    public void setRfqId(String rfqId) {
        this.rfqId = rfqId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
