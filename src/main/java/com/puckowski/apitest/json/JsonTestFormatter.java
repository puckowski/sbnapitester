package com.puckowski.apitest.json;

public class JsonTestFormatter
{
    public JsonTestFormatter() {

    }

    public String formatRawJson(final String rawJson) {
        String formattedJson = rawJson.replaceAll("\"","\\\\\"");
        formattedJson = formattedJson.replaceAll("\\\\n", "\\\\\\\\n");
        formattedJson = formattedJson.replaceAll("\n", " ");

        return "\"" + formattedJson + "\"";
    }

    public String removeNewlinesFromRawJson(final String rawJson) {
        String formattedJson = rawJson.replaceAll("\n", " ");

        return formattedJson;
    }
}