package sjwa.core;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * wrapper for HttpServletResponse
 */
public class Response {

    protected HttpServletResponse response;

    public Response(HttpServletResponse response){
        this.response = response;
    }

    public void write(String s){
        try {
            this.response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
