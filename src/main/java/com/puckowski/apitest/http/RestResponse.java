package com.puckowski.apitest.http;

public class RestResponse
{
    private final int DEFAULT_STATUS_CODE = -1;
    private final String DEFAULT_RESPONSE_TEXT = "";

    private int mStatusCode;
    private String mResponseText;

    public RestResponse() {
        mStatusCode = DEFAULT_STATUS_CODE;
        mResponseText = DEFAULT_RESPONSE_TEXT;
    }

    public RestResponse(final int statusCode, final String responseText) {
        mStatusCode = statusCode;
        mResponseText = responseText;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(final int newStatusCode) {
        mStatusCode = newStatusCode;
    }

    public String getResponseText() {
        return mResponseText;
    }

    public void setResponseText(final String newResponseText) {
        mResponseText = newResponseText;
    }
}