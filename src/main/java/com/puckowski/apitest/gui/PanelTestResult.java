package com.puckowski.apitest.gui;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class PanelTestResult extends JPanel
{
    private static final long serialVersionUID = 1L;

    private TextAreaTestResult mResultTextArea;
    private JLabel mResultLabel;

    public PanelTestResult() {
        buildPanel();
    }

    private TextAreaTestResult buildTextAreaTestResult() {
        TextAreaTestResult testResultTextArea = new TextAreaTestResult();

        return testResultTextArea;
    }

    private JLabel buildResultLabel() {
        JLabel resultLabel = new JLabel("");

        return resultLabel;
    }

    private void buildPanel() {
        this.setLayout(new BorderLayout());

        mResultTextArea = buildTextAreaTestResult();
        mResultLabel = buildResultLabel();

        PanelTextAreaScroll testResultScrollPanel = new PanelTextAreaScroll("Test Result", mResultTextArea);

        this.add(testResultScrollPanel, BorderLayout.CENTER);
        this.add(mResultLabel, BorderLayout.SOUTH);

        this.setBorder(SwingUtils.getDefaultBorderTop());
    }

    public JTextArea getTestResultTextArea() {
        return mResultTextArea;
    }

    public JLabel getResultLabel() {
        return mResultLabel;
    }

    public void setPassAndFailCountMessage(final int passCount, final int failCount) {
        mResultLabel.setText("Pass: " + passCount + ", Fail: " + failCount);
    }
}