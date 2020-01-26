package com.puckowski.apitest.http;

public class HttpRequestMethods
{
    private final String[] HTTP_REQUEST_METHODS_SUPPORTED = {
        "GET",
        "POST"
    };

    public HttpRequestMethods() {

    }

    public String[] getHttpRequestMethodsSupported() {
        return this.HTTP_REQUEST_METHODS_SUPPORTED;
    }
}