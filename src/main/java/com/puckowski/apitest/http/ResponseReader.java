package com.puckowski.apitest.http;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface ResponseReader {

    public String read(final HttpURLConnection conn) throws IOException;

}
