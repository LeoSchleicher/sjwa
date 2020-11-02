package sjwa.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import sjwa.core.annotations.Application;

public class BasicApplication {

    protected static final Logger log = LoggerFactory.getLogger("sjwa.core.Application");

    public String name;


    public void configure(){
        List<Application> hie = this.appHierarchy(this.getClass());
        for (Application app : hie){
            log.debug(app.configurationName());
        }
    }

    private List<Application> appHierarchy(Class clazz){
        List<Application> res = new ArrayList<>();
        if (clazz == null) {
            clazz = this.getClass();
        }
        if(clazz.getSuperclass() != null) {
            res.addAll(this.appHierarchy(clazz.getSuperclass()));
        }
        Annotation app =  clazz.getAnnotation(Application.class);
        if(app instanceof Application){
            res.add((Application) app);
        }
        return res;
    }

}
