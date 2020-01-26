package com.puckowski.apitest.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class PanelTextArea extends JPanel
{
    private static final long serialVersionUID = 1L;

    private TextAreaBody mBodyTextArea;
    private TextAreaResponse mResponseTextArea;
    private TextAreaExpected mExpectedTextArea;

    public PanelTextArea() {
        buildPanel();
    }

    private TextAreaBody buildTextAreaBody() {
        TextAreaBody bodyTextArea = new TextAreaBody();

        return bodyTextArea;
    }

    private TextAreaResponse buildTextAreaResponse() {
        TextAreaResponse responseTextArea = new TextAreaResponse();

        return responseTextArea;
    }

    private TextAreaExpected buildTextAreaExpected() {
        TextAreaExpected expectedTextArea = new TextAreaExpected();

        return expectedTextArea;
    }

    private void buildPanel() {
        this.setLayout(new FlowLayout());

        mBodyTextArea = buildTextAreaBody();
        mResponseTextArea = buildTextAreaResponse();
        mExpectedTextArea = buildTextAreaExpected();

        PanelTextAreaScroll bodyScrollPanel = new PanelTextAreaScroll("Request Body", mBodyTextArea);
        PanelTextAreaScroll responseScrollPanel = new PanelTextAreaScroll("Request Response", mResponseTextArea);
        PanelTextAreaScroll expectedScrollPanel = new PanelTextAreaScroll("Expected Response", mExpectedTextArea);

        this.add(bodyScrollPanel);
        this.add(responseScrollPanel);
        this.add(expectedScrollPanel);

        this.setBorder(SwingUtils.getDefaultBorderTop());
    }

    public JTextArea getBodyTextArea() {
        return mBodyTextArea;
    }

    public JTextArea getResponseTextArea() {
        return mResponseTextArea;
    }

    public JTextArea getExpectedTextArea() {
        return mExpectedTextArea;
    }
}