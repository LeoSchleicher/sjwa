package sjwa.core;

import javax.servlet.http.HttpServletRequest;

/**
 * Wrapper for HttpServletRequest
 */
public class Request {

    protected HttpServletRequest request;

    public Request(HttpServletRequest request) {
        this.request = request;
    }


}
