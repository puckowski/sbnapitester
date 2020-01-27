package com.puckowski.apitest.gui;

import java.awt.*;
import javax.swing.JPanel;

import com.puckowski.apitest.http.HttpRequestMethods;

public class PanelRequestInput extends JPanel
{
    private static final long serialVersionUID = 1L;

    private final PanelTextArea TEXT_AREA_PANEL;

    public PanelRequestInput(final PanelTextArea panelTextArea) {
        TEXT_AREA_PANEL = panelTextArea;

        this.buildPanel();
    }

    private ComboBoxRequestType buildRequestComboBox() {
        final HttpRequestMethods httpMethods = new HttpRequestMethods();
        ComboBoxRequestType requestComboBox = new ComboBoxRequestType(httpMethods.getHttpRequestMethodsSupported());

        return requestComboBox;
    }

    private TextFieldRequestUrl buildTextFieldRequestUrl() {
        TextFieldRequestUrl requestTextField = new TextFieldRequestUrl();

        return requestTextField;
    }

    private ButtonSendRequest buildSendRequestButton(final TextFieldRequestUrl requestTextField,
            final ComboBoxRequestType requestTypeComboBox, final LabelRequestResult resultLabel) {
        ButtonSendRequest sendButton = new ButtonSendRequest();

        sendButton.addActionListener(new ActionListenerSendRequest(requestTextField, requestTypeComboBox, resultLabel,
            TEXT_AREA_PANEL));

        return sendButton;
    }

    private LabelRequestResult buildLabelRequestResult() {
        LabelRequestResult resultLabel = new LabelRequestResult();

        return resultLabel;
    }

    private void buildPanel() {
        this.setLayout(new FlowLayout());

        final LabelRequestResult resultLabel = buildLabelRequestResult();
        final TextFieldRequestUrl requestTextField = buildTextFieldRequestUrl();
        final ComboBoxRequestType requestComboBox = buildRequestComboBox();
        final ButtonSendRequest sendButton = buildSendRequestButton(requestTextField, requestComboBox, resultLabel);
        
        JPanel panelMethodAndSend = new JPanel();
        panelMethodAndSend.setLayout(new BorderLayout());

        panelMethodAndSend.add(requestComboBox, BorderLayout.CENTER);
        panelMethodAndSend.add(sendButton, BorderLayout.EAST);

        this.add(requestTextField);
        this.add(panelMethodAndSend);
        this.add(resultLabel);

        this.setBorder(SwingUtils.getDefaultBorder());
    }
    
}