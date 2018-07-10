package in.equipshare.es_cms.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RFQ {


    @SerializedName("location")
    private String location;

    @SerializedName("projectType")
    private String projectType;

    @SerializedName("durationMonth")
    private String durationMonth;

    @SerializedName("durationYear")
    private String durationYear;

    @SerializedName("startDate")
    private String startDate;

    @SerializedName("devAuthority")
    private String devAuthority;

    @SerializedName("projectDescription")
    private String projectDescription;

    @SerializedName("equipmentList")
    private ArrayList<EquipmentSelect> equipmentList;

    public RFQ() {
    }


    public RFQ(String location, String projectType, String durationMonth, String durationYear, String startDate, String devAuthority, String projectDescription, ArrayList<EquipmentSelect> equipmentList) {
        this.location = location;
        this.projectType = projectType;
        this.durationMonth = durationMonth;
        this.durationYear = durationYear;
        this.startDate = startDate;
        this.devAuthority = devAuthority;
        this.projectDescription = projectDescription;
        this.equipmentList = equipmentList;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDevAuthority() {
        return devAuthority;
    }

    public void setDevAuthority(String devAuthority) {
        this.devAuthority = devAuthority;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public ArrayList<EquipmentSelect> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(ArrayList<EquipmentSelect> equipmentList) {
        this.equipmentList = equipmentList;
    }
}
