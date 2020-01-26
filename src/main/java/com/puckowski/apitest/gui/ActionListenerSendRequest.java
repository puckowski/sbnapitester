package com.puckowski.apitest.gui;

import java.awt.event.ActionListener;
import java.io.IOException;

import com.puckowski.apitest.http.RestRequest;
import com.puckowski.apitest.http.RestResponse;

import java.awt.event.ActionEvent;

public class ActionListenerSendRequest implements ActionListener 
{
    private final TextFieldRequestUrl REQUEST_URL_TEXT_FIELD;
    private final ComboBoxRequestType REQUEST_TYPE_COMBO_BOX;
    private final LabelRequestResult RESULT_LABEL;
    private final PanelTextArea TEXT_AREA_PANEL;

    private int mRequestCounter;

    public ActionListenerSendRequest(final TextFieldRequestUrl requestTextField,
            final ComboBoxRequestType requestTypeComboBox, final LabelRequestResult resultLabel,
            final PanelTextArea panelTextArea) {
        REQUEST_URL_TEXT_FIELD = requestTextField;
        REQUEST_TYPE_COMBO_BOX = requestTypeComboBox;
        RESULT_LABEL = resultLabel;
        TEXT_AREA_PANEL = panelTextArea;

        mRequestCounter = 0;
    }

    private String getRequestFailedText() {
        return "(" + mRequestCounter + ") Request failed.";
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        final String requestUrl = REQUEST_URL_TEXT_FIELD.getText();
        final String requestMethod = REQUEST_TYPE_COMBO_BOX.getSelectedRequestType();
        final String requestBody = TEXT_AREA_PANEL.getBodyTextArea().getText();

        RestRequest restRequest = new RestRequest();
        mRequestCounter++;
        
        try {
            final RestResponse response = restRequest.makeRequest(requestUrl, requestMethod, requestBody);

            RESULT_LABEL.setText("Status Code: " + response.getStatusCode());
            TEXT_AREA_PANEL.getResponseTextArea().setText(response.getResponseText());
        } catch (final IOException ioException) {
            ioException.printStackTrace();

            RESULT_LABEL.setText(getRequestFailedText());
            TEXT_AREA_PANEL.getResponseTextArea().setText(getRequestFailedText());
        }
    }
}