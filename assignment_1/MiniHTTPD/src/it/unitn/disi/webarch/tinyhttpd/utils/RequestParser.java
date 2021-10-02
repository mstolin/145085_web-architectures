package it.unitn.disi.webarch.tinyhttpd.utils;

import java.util.StringTokenizer;

public class RequestParser {

    private String request;
    private String method;
    private String protocol;
    private String fullPath;
    private String path;
    private String queryString;

    /**
     * The RequestParser is able to parse a HTTP request
     * into its single parts
     *
     * @param request
     */
    public RequestParser(String request) throws Exception {
        this.request = request;
        this.parseRequest();
        this.parsePath();
    }

    private void parseRequest() throws Exception {
        StringTokenizer tokenizer = new StringTokenizer(this.request);
        if (tokenizer.countTokens() < 3) {
            throw new Exception("The request \"" + this.request + "\" is invalid");
        }

        this.method = tokenizer.nextToken();
        this.fullPath = tokenizer.nextToken();
        this.protocol = tokenizer.nextToken();
    }

    private void parsePath() {
        StringTokenizer tokenizer = new StringTokenizer(this.fullPath, "?");

        this.path = tokenizer.nextToken();
        if (tokenizer.hasMoreTokens()) {
            this.queryString = tokenizer.nextToken();
        }
    }

    public String getMethod() {
        return this.method;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public String getPath() {
        return this.path;
    }

    public String getQueryString() {
        return this.queryString;
    }
}
