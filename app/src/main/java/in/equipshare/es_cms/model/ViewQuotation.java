package in.equipshare.es_cms.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ViewQuotation extends RFQ {

    @SerializedName("companyName")
    private String companyName;

    @SerializedName("rfqId")
    private String rfqId;


    public ViewQuotation(String location, String projectType, String durationMonth, String durationYear, String startDate, String devAuthority, String projectDescription, ArrayList<NeedEquipment> equipmentList, String companyName, String rfqId) {
        super(location, projectType, durationMonth, durationYear, startDate, devAuthority, projectDescription, equipmentList);
        this.companyName = companyName;
        this.rfqId = rfqId;
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
}
