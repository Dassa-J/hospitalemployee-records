package server;


import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/hospitals")
public class HospitalResource {
    
        @Context
        private UriInfo context;
    
    
          private HospitalEmployeeDAO dao = new HospitalEmployeeDAO ();
	@GET
	@Produces({ MediaType.APPLICATION_XML })

	public List<HospitalEmployee> getHotels() throws Exception {
		return dao.getAllEmployees();
	}

   
 
    @OPTIONS    
    public Response doOptionsCollection(){


  Response r = Response.ok("Http request with OPTIONS, body content")
                           .header("Allow", "GET")
                           .header("Allow", "HEAD")
                           .header("Allow", "POST")
                            .header("Allow", "DELETE")
                             .header("Allow", "PUT")
                           .build();
      return r;
               
        
    }
       @HEAD   
    public Response getMetadata(){
        return Response
                .noContent()
                .status(Response.Status.NO_CONTENT)
                .build();
    }
    
      @GET
    @Path ("/{employeeId}")
    @Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getEmployeeDetails ( @PathParam ("employeeId") String employeeId){
        

     HospitalEmployee hospitalEmployee = dao.getEmployeeDetails(employeeId);
     
     if (hospitalEmployee == null){
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("<employeeNotFound />")
                .build ();
}
     else{
         System.out.println(hospitalEmployee);
    return Response
            .status(Response.Status.OK)
            .entity(hospitalEmployee)
            .build();
}
     
}
     @POST   
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response postXml(HospitalEmployee hospitalEmployee) throws Exception{
      
        
       String id= dao.addHospitalEmployee(hospitalEmployee);
       
       HospitalEmployee hos= new HospitalEmployee(id,hospitalEmployee.getEmployeeAddress(),hospitalEmployee.getEmployeeGend(),hospitalEmployee.getEmployeeQua(),hospitalEmployee.getEmployeeStatus(),hospitalEmployee.getDateCommencement(),hospitalEmployee.getDateExit(),hospitalEmployee.getEmployeeName());
       
        
       
        dao.addHospitalEmployee(hospitalEmployee);
        
      
        
        return Response
                .status(Response.Status.CREATED) 
                
                .entity(hos)
                .build();
        

    }
        
    @DELETE
    @Produces({ MediaType.APPLICATION_XML })
    @Path("/{id}")
    public void deleteEmployee(@PathParam("id") String id) throws Exception {
        dao.deleteHospitalEmployee(id);
    }
    
    
    @PUT
    @Path("/{employeeId}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response putHospitalEmployee(HospitalEmployee updatedHospitalEmployee,
                                   @PathParam("employeeId") String employeeId){
        
        
        if(employeeId.equals(updatedHospitalEmployee.getEmployeeId())){
            
            if(dao.getEmployeeDetails(employeeId) == null){
               
                dao.addHospitalEmployee(updatedHospitalEmployee);
                return Response
                        .status(Response.Status.CREATED)
                        .header("Location", context.getAbsolutePath().toString())
                        .entity(updatedHospitalEmployee)
                        .build();
            }else{
                
                dao.updateHospitalEmployee(updatedHospitalEmployee);
                return Response
                        .status(Response.Status.OK)
                        .entity(updatedHospitalEmployee)
                        .build();
            }
            
        }else{
            
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity("<mismatchError />")
                    .build();
        }
    }

       @DELETE 
    public Response deleteAllAccounts(){
       dao.deleteAllEmployees();
        
        return Response
                .noContent()
                .build();
    }



}