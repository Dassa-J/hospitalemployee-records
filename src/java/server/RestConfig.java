package server;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath ("/rest")
public class RestConfig extends Application{
    @Override
    public Set<Class<?>> getClasses (){
        final Set<Class<?>> classes;
        classes = new HashSet<>();
        classes.add(HospitalResource.class);
        return classes;
    }
    
}
