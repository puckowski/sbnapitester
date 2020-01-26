package com.puckowski.apitest.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.FlowLayout;

public class PanelFormatArea extends JPanel
{
    private static final long serialVersionUID = 1L;

    private TextAreaBody mToFormatArea;
    private TextAreaResponse mFormattedArea;

    public PanelFormatArea() {
        buildPanel();
    }

    private TextAreaBody buildTextAreaToFormat() {
        TextAreaBody toFormatTextArea = new TextAreaBody();

        return toFormatTextArea;
    }

    private TextAreaResponse buildTextAreaFormatted() {
        TextAreaResponse formattedTextArea = new TextAreaResponse();

        return formattedTextArea;
    }

    private void buildPanel() {
        this.setLayout(new FlowLayout());

        mToFormatArea = buildTextAreaToFormat();
        mFormattedArea = buildTextAreaFormatted();

        PanelTextAreaScroll toFormatScrollPanel = new PanelTextAreaScroll("Raw JSON", mToFormatArea);
        PanelTextAreaScroll formattedScrollPanel = new PanelTextAreaScroll("Test Formatted", mFormattedArea);

        this.add(toFormatScrollPanel);
        this.add(formattedScrollPanel);

        this.setBorder(SwingUtils.getDefaultBorderTop());
    }

    public JTextArea getToFormatTextArea() {
        return mToFormatArea;
    }

    public JTextArea getFormattedTextArea() {
        return mFormattedArea;
    }
}