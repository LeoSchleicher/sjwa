package sjwa.core;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sjwa.core.annotations.Application;
import sjwa.core.helper.ListHelper;

public class Servlet extends GenericServlet {

    protected static final Logger log = LoggerFactory.getLogger("sjwa.core.Servlet");

    private List<BasicApplication> applications = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        log.debug("in init");

        // find all annotated classes
        Reflections ref = new Reflections("");
        for (Class<?> cl : ref.getTypesAnnotatedWith(Application.class)) {
            Application app = cl.getAnnotation(Application.class);
            log.info("found class: "+app.getClass().getName()+" annotated as: "+app.name());
            try {
                Object appInst = cl.newInstance();
                if(appInst instanceof BasicApplication){
                    BasicApplication basicApplication = (BasicApplication) appInst;
                    basicApplication.name = app.name(); // just transfer annotation to property
                    this.applications.add(basicApplication);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void destroy() {
        log.debug("in destroy");
        super.destroy();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        this.service((HttpServletRequest)req, (HttpServletResponse)res);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("in service. count registered applications: "+applications.size());
        // select here current application
        BasicApplication currentApplication = ListHelper.find(this.applications, e -> {
            return true;
        });
        currentApplication.request = new Request(request);
        currentApplication.response = new Response(response);

        currentApplication.response.write("Serving app: "+currentApplication.name+" ook!");
    }


}
