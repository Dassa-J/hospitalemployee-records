package server;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="hospital")

@XmlType(propOrder={"employeeId", "employeeAddress", "employeeGend", "employeeQua", "employeeStatus", "dateCommencement", "dateExit", "employeeName"})

public class HospitalEmployee {
   private String employeeId, employeeAddress, employeeGend, employeeQua, employeeStatus, 
                      dateCommencement, dateExit, employeeName;

    public HospitalEmployee() {
    }

    public HospitalEmployee(String employeeId, String employeeAddress, String employeeGend, String employeeQua, String employeeStatus, String dateCommencement, String dateExit, String employeeName) {
        this.employeeId = employeeId;
        this.employeeAddress = employeeAddress;
        this.employeeGend = employeeGend;
        this.employeeQua = employeeQua;
        this.employeeStatus = employeeStatus;
        this.dateCommencement = dateCommencement;
        this.dateExit = dateExit;
        this.employeeName = employeeName;
    }
    
    @XmlElement
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    @XmlElement
    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    @XmlElement
    public String getEmployeeGend() {
        return employeeGend;
    }

    public void setEmployeeGend(String employeeGend) {
        this.employeeGend = employeeGend;
    }

    @XmlElement
    public String getEmployeeQua() {
        return employeeQua;
    }

    public void setEmployeeQua(String employeeQua) {
        this.employeeQua = employeeQua;
    }

    @XmlElement
    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    @XmlElement
    public String getDateCommencement() {
        return dateCommencement;
    }

    public void setDateCommencement(String dateCommencement) {
        this.dateCommencement = dateCommencement;
    }

    @XmlElement
    public String getDateExit() {
        return dateExit;
    }

    public void setDateExit(String dateExit) {
        this.dateExit = dateExit;
    }

    @XmlElement
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "HospitalEmployee{" + "employeeId=" + employeeId + ", employeeAddress=" + employeeAddress + ", employeeGend=" + employeeGend + ", employeeQua=" + employeeQua + ", employeeStatus=" + employeeStatus + ", dateCommencement=" + dateCommencement + ", dateExit=" + dateExit + ", employeeName=" + employeeName + '}';
    }
   
   
}
