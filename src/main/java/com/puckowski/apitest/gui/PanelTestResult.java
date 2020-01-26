package com.puckowski.apitest.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.FlowLayout;

public class PanelTestResult extends JPanel
{
    private static final long serialVersionUID = 1L;

    private TextAreaTestResult mResultTextArea;

    public PanelTestResult() {
        buildPanel();
    }

    private TextAreaTestResult buildTextAreaTestResult() {
        TextAreaTestResult testResultTextArea = new TextAreaTestResult();

        return testResultTextArea;
    }

    private void buildPanel() {
        this.setLayout(new FlowLayout());

        mResultTextArea = buildTextAreaTestResult();

        PanelTextAreaScroll testResultScrollPanel = new PanelTextAreaScroll("Test Result", mResultTextArea);

        this.add(testResultScrollPanel);

        this.setBorder(SwingUtils.getDefaultBorderTop());
    }

    public JTextArea getTestResultTextArea() {
        return mResultTextArea;
    }
}