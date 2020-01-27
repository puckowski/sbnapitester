package com.puckowski.apitest.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

public class ResponseReaderGzip {

    public ResponseReaderGzip() {

    }

    public String read(final HttpURLConnection conn) throws IOException {
        final StringBuilder responseBuilder = new StringBuilder(4000);

        InputStream inStream = new GZIPInputStream(conn.getInputStream());
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

        return responseBuilder.toString();
    }
}
