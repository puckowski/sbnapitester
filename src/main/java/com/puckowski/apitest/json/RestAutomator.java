package com.puckowski.apitest.json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.puckowski.apitest.gui.PanelTestResult;
import com.puckowski.apitest.gui.SwingUtils;
import com.puckowski.apitest.http.RestRequest;
import com.puckowski.apitest.http.RestResponse;
import com.puckowski.apitest.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;

public class RestAutomator
{
    private final JTabbedPane MAIN_TAB_PANE;
    private final PanelTestResult TEST_RESULT_PANEL;

    private int mTestIndex;

    public RestAutomator(final JTabbedPane mainTabPane, final PanelTestResult testResultPanel) {
        MAIN_TAB_PANE = mainTabPane;
        TEST_RESULT_PANEL = testResultPanel;

        mTestIndex = 1;
    }

    public void runTestFile(final File selectedFile, final JFrame frame) {
        try {
            final String textFileStringContent = new String(Files.readAllBytes(selectedFile.toPath()));
            JSONObject mainObject = new JSONObject(textFileStringContent);
            JSONArray testArray = mainObject.getJSONArray("tests");

            final StringBuilder resultBuilder = new StringBuilder(4000);
            final JsonTestFormatter jsonTestFormatter = new JsonTestFormatter();
            RestRequest restRequest = new RestRequest();
            JsonCompareMethods jsonCompareMethods = new JsonCompareMethods();

            testArray.forEach(testItem -> {
                JSONObject obj = (JSONObject) testItem;

                String testUrl, testMethod, testBody, userAgent, encoding, expectedResult, compareType;

                try {
                testUrl = obj.getString("url");
                } catch (final JSONException jsonException) {
                    testUrl = "";
                }

                try {
                    testMethod = obj.getString("method");
                } catch (final JSONException jsonException) {
                    testMethod = "";
                }

                try {
                    testBody = obj.getString("body");
                } catch (final JSONException jsonException) {
                    testBody = "";
                }

                try {
                    userAgent = obj.getString("user-agent");
                } catch (final JSONException jsonException) {
                    userAgent = "";
                }

                try {
                    encoding = obj.getString("encoding");
                } catch (final JSONException jsonException) {
                    encoding = "";
                }

                try {
                    expectedResult = obj.getString("expected");
                } catch (final JSONException jsonException) {
                    expectedResult = "";
                }
                
                expectedResult = expectedResult.replaceAll("\\\"", "\"");
                //expectedResult = expectedResult.replaceAll("\\n", "\\n");

                try {
                    compareType = obj.getString("compare");
                } catch (final JSONException jsonException) {
                    compareType = "";
                }

                if (compareType == null || compareType.isEmpty() == true) {
                    compareType = "NON_EXTENSIBLE";
                }

                resultBuilder.append(Utils.quoteWrapString(String.valueOf(mTestIndex)));
                resultBuilder.append(",");

                String responseJson = "";

                try {
                    RestResponse restResponse = restRequest.makeRequest(testUrl, testMethod, testBody, userAgent, encoding);

                    responseJson = restResponse.getResponseText();
                    JSONAssert.assertEquals(expectedResult, responseJson, jsonCompareMethods.getCompareMode(compareType));

                    resultBuilder.append(Utils.quoteWrapString("PASS"));
                    resultBuilder.append(",");
                    resultBuilder.append(Utils.quoteWrapString(String.valueOf(restResponse.getStatusCode())));
                    resultBuilder.append(",");
                } catch (final IOException ioException) {
                    resultBuilder.append(Utils.quoteWrapString("FAIL IO"));
                    resultBuilder.append(",");
                    resultBuilder.append(Utils.quoteWrapString("-1"));
                    resultBuilder.append(",");
                } catch (final AssertionError assertionError) {
                    resultBuilder.append(Utils.quoteWrapString("FAIL JSON COMPARE"));
                    resultBuilder.append(",");
                    resultBuilder.append(Utils.quoteWrapString("-1"));
                    resultBuilder.append(",");
                } catch (final JSONException jsonException) {
                    resultBuilder.append(Utils.quoteWrapString("FAIL JSON PARSE"));
                    resultBuilder.append(",");
                    resultBuilder.append(Utils.quoteWrapString("-1"));
                    resultBuilder.append(",");
                }

                resultBuilder.append(Utils.quoteWrapString(testUrl));
                resultBuilder.append(",");
                resultBuilder.append(Utils.quoteWrapString(testMethod));
                resultBuilder.append(",");
                resultBuilder.append(Utils.quoteWrapString(testBody));
                resultBuilder.append(",");
                resultBuilder.append(Utils.quoteWrapString(compareType));
                resultBuilder.append(",");
                resultBuilder.append(Utils.quoteWrapString(jsonTestFormatter.removeNewlinesFromRawJson(responseJson)));
                resultBuilder.append("\n");

                mTestIndex++;

                TEST_RESULT_PANEL.getTestResultTextArea().setText(resultBuilder.toString());
                MAIN_TAB_PANE.setSelectedIndex(2);
            });

            System.out.println(resultBuilder.toString());
        } catch (final IOException ioException) {
            SwingUtils.showWarningDialog(frame, "File Error", "Could not open test file.");
        }
    }
}