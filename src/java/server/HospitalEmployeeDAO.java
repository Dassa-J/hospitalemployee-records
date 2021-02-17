package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HospitalEmployeeDAO {
    private Connection con = null;
    
    public  static void main(String[] args)  {
        HospitalEmployeeDAO dao = new HospitalEmployeeDAO ();
        
        HospitalEmployee he = dao.getEmployeeDetails("123");
        System.out.println(he);
    }
    
    public HospitalEmployeeDAO () {
        try {
            System.out.println("Loading db driver....");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
             con = DriverManager.getConnection (
             "jdbc:derby://localhost:1527/Employee_DB",
             "Esther",
             "esther");       
        }
        catch (Exception ex){
            
            System.err.println("Exception.");
            ex.printStackTrace ();
        }
    }
      public int getNextId(){
        int newEmployeeId = -1;
        
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT MAX(EMPLOYEE_ID) AS MAX_EMPLOYEE_ID FROM "
                    + " APP.EMPLOYEE_TABLE");
            

            ResultSet rs = ps.executeQuery();

            
            if (!rs.next()) {  
                return -1;
            }

            newEmployeeId = rs.getInt("MAX_EMPLOYEE_ID") + 1;

        } catch (SQLException sqle) {
            System.err.println("SQLException in getNextEmployeeID()");
            sqle.printStackTrace();
        }
          
        return newEmployeeId;
    }
    
    public HospitalEmployee getEmployeeDetails(String employeeId ) {
        HospitalEmployee hospitalEmployee = null;
        
        try {
                PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM APP.EMPLOYEE_TABLE WHERE "  
                     + "(EMPLOYEE_ID = ?)");
               
                ps.setString(1, employeeId);
                
                 
                        
                             
                ResultSet rs = ps.executeQuery();
                
                if (!rs.next()){
                    return null;
                }  
                
                hospitalEmployee = new HospitalEmployee (
                 rs.getString("EMPLOYEE_ID"),
                 rs.getString("EMPLOYEE_ADDRESS"),
                 rs.getString("EMPLOYEE_GENDER"),
                 rs.getString("EMPLOYEE_QUALIFICATION"),
                 rs.getString("EMPLOYEE_STATUS"),
                 rs.getString("DATE_OF_COMMENCEMENT"),
                 rs.getString("DATE_OF_EXIT"),
                 rs.getString("EMPLOYEE_NAME")
                );                    
        }
        catch (SQLException sqle){
            System.err.println("SQLException in getEmployeeDetails ()");
            sqle.printStackTrace();
        }
        return hospitalEmployee;
                                  
    }
    
    public List<HospitalEmployee> getAllEmployees() {
        List<HospitalEmployee> hospitalEmployees
                = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM APP.EMPLOYEE_TABLE");

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                  HospitalEmployee hospitalEmployee = new HospitalEmployee(
                
                        rs.getString("EMPLOYEE_ID"),
                        rs.getString("EMPLOYEE_ADDRESS"),
                        rs.getString("EMPLOYEE_GENDER"),
                        rs.getString("EMPLOYEE_QUALIFICATION"),
                        rs.getString("EMPLOYEE_STATUS"),
                        rs.getString("DATE_OF_COMMENCEMENT"),
                        rs.getString("DATE_OF_EXIT"),
                        rs.getString("EMPLOYEE_NAME"));
                
         
                hospitalEmployees.add(hospitalEmployee);
            }

        } catch (SQLException sqle) {
            System.err.println("SQLException in getAccountDetails()");
            sqle.printStackTrace();
        }

        return hospitalEmployees;

    }

    public void deleteHospitalEmployee(String employeeId) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM APP.EMPLOYEE_TABLE WHERE "
                    + "(EMPLOYEE_ID = ?)");
            ps.setString(1, employeeId);
            

            ps.executeUpdate();

        } catch (SQLException sqle) {
            System.err.println("SQLException in deleteHospitalEmployee()");
            sqle.printStackTrace();
        }
    }

    public void deleteAllEmployees() {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM APP.EMPLOYEE_TABLE");

            ps.execute();

        } catch (SQLException sqle) {
            System.err.println("SQLException in deleteAllEmployees()");
            sqle.printStackTrace();
        }

    }

    public String addHospitalEmployee(HospitalEmployee he) {
        
        String id= String.valueOf(getNextId());
        System.out.println(id);
        
        try {
            // make sure that this account is not already
            // in the db
           
            if (getEmployeeDetails(he.getEmployeeId()) != null) {
                // bank account is already in the db
                return "-1";
            } else {
                // account is not in the db already
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO APP.EMPLOYEE_TABLE"
                                + "(EMPLOYEE_ID, EMPLOYEE_ADDRESS,"
                                + "EMPLOYEE_GENDER, EMPLOYEE_QUALIFICATION, EMPLOYEE_STATUS,"
                                + "DATE_OF_COMMENCEMENT, DATE_OF_EXIT, EMPLOYEE_NAME) "
                                + "VALUES (?,?,?,?,?,?,?,?)");
                                 System.out.println(id);
                                  System.out.println(he.getEmployeeAddress());
                                   System.out.println(he.getEmployeeGend());
                                   
                                    System.out.println(he.getEmployeeQua());
                                   System.out.println(he.getEmployeeStatus());
                                    System.out.println(he.getDateCommencement());
                                   System.out.println(he.getDateExit());
                                   System.out.println(he.getEmployeeName());
                                   
                ps.setString(1, id);
                ps.setString(2, he.getEmployeeAddress());
                ps.setString(3, he.getEmployeeGend());
                ps.setString(4, he.getEmployeeQua());
                ps.setString(5, he.getEmployeeStatus());
                ps.setString(6, he.getDateCommencement());
                ps.setString(7, he.getDateExit());
                ps.setString(8, he.getEmployeeName());
                ps.executeUpdate();
            }   
        } catch (SQLException sqle) {
            System.err.println("SQLException in addHospitalEmployee()");
            sqle.printStackTrace();
            return "-1";
        }
        
        return id; 
    }

    public int updateHospitalEmployee(HospitalEmployee he) {
        try {
           
            if (getEmployeeDetails(he.getEmployeeId()) == null) {
                
                return -1;
            } else {
                 
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE APP.EMPLOYEE_TABLE "
                                + "SET EMPLOYEE_ADDRESS=?, EMPLOYEE_GENDER=?, EMPLOYEE_QUALIFICATION=?,"
                                + "EMPLOYEE_STATUS=?, DATE_OF_COMMENCEMENT=?, DATE_OF_EXIT = ?, EMPLOYEE_NAME=?"
                                + "WHERE (EMPLOYEE_ID=?)");

                ps.setString(1, he.getEmployeeAddress());
                ps.setString(2, he.getEmployeeGend());
                ps.setString(3, he.getEmployeeQua());
                ps.setString(4, he.getEmployeeStatus());
                ps.setString(5, he.getDateCommencement());
                ps.setString(6, he.getDateExit());
                ps.setString(7, he.getEmployeeName());
                ps.setString(8, he.getEmployeeId());
               

                ps.executeUpdate();
            }   
        } catch (SQLException sqle) {
            System.err.println("SQLException in addHospitalEmployee()");
            sqle.printStackTrace();
            return -1;
        }
        
        return 1; 
    }



  

}


