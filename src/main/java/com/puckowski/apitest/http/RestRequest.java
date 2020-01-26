package com.puckowski.apitest.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class RestRequest {
    public RestRequest() {

    }

    public RestResponse makeRequest(final String requestUrl, final String requestMethod,
        final String body, final String userAgentString, final String encoding)
            throws IOException {
        RestResponse response = new RestResponse();
        boolean isGzipEnabled = false;

        URL url = new URL(requestUrl);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(requestMethod);

        if (userAgentString != null && userAgentString.isEmpty() == false) {
            // User-Agent: "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36"
            con.setRequestProperty("User-Agent", userAgentString);
        }

        if (encoding != null && encoding.isEmpty() == false) {
            if (encoding.equals("gzip") == true) {
                //con.setRequestProperty("Accept-Encoding", "gzip, deflate");
                con.setRequestProperty("Accept-Encoding", "gzip");
                isGzipEnabled = true;
            }
        }

        con.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        //con.setRequestProperty("Accept", "application/json");
        //con.setRequestProperty("Connection", "keep-alive");
        //con.setRequestProperty("Cookie", "__cfduid=d78f0bb5e2f68bd3ce7429aa427c588871579975354");
        //con.setRequestProperty("Host", "jsonplaceholder.typicode.com");
        //con.setRequestProperty("Upgrade-Insecure-Requests", "1");

        if (body != null && body.isEmpty() == false) {
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        final int statusCode = con.getResponseCode();
        response.setStatusCode(statusCode);

        StringBuilder responseBuilder = new StringBuilder(4000);

        if (isGzipEnabled == false) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    responseBuilder.append(responseLine.trim());
                    responseBuilder.append('\n');
                }
            }
        } else if (isGzipEnabled == true) {            
            InputStream inStream = new GZIPInputStream(con.getInputStream());
            try (Reader br = new InputStreamReader(inStream)) {
                int ch;
                while (true) {
                    ch = br.read();
                    
                    if (ch == -1) {
                       break;
                    }
                    
                    responseBuilder.append((char) ch);
                 }
            }
        }

        response.setResponseText(responseBuilder.toString());

        return response;
    }

    public RestResponse makeRequest(final String requestUrl, final String requestMethod, final String body)
            throws IOException {
        return this.makeRequest(requestUrl, requestMethod, body, null, null);
    }
}