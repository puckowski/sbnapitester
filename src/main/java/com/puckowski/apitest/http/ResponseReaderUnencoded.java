package com.puckowski.apitest.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ResponseReaderUnencoded implements ResponseReader {

    public ResponseReaderUnencoded() {

    }

    public String read(final HttpURLConnection conn) throws IOException {
        final StringBuilder responseBuilder = new StringBuilder(4000);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                responseBuilder.append(responseLine.trim());
                responseBuilder.append('\n');
            }
        }

        return responseBuilder.toString();
    }
}
