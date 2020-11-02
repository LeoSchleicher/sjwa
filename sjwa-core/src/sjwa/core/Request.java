package sjwa.core;

import javax.servlet.http.HttpServletRequest;

/**
 * Wrapper for HttpServletRequest
 * this is a part of Application
 */
public class Request {

    protected HttpServletRequest request;

    public Request(HttpServletRequest request) {
        this.request = request;
    }


}
