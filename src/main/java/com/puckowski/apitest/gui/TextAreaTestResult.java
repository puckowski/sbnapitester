package com.puckowski.apitest.gui;

import javax.swing.JTextArea;

public class TextAreaTestResult extends JTextArea
{
    private static final long serialVersionUID = 1L;

    private final int DEFAULT_COLUMNS = 100;
    private final int DEFAULT_ROWS = 24;

    public TextAreaTestResult() {
        this.setColumns(DEFAULT_COLUMNS);
        this.setRows(DEFAULT_ROWS);

        this.setEditable(false);
    }
}