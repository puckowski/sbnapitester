package com.puckowski.apitest.gui;

import javax.swing.JTextArea;

public class TextAreaResponse extends JTextArea
{
    private static final long serialVersionUID = 1L;

    private final int DEFAULT_COLUMNS = 50;
    private final int DEFAULT_ROWS = 30;

    public TextAreaResponse() {
        this.setColumns(DEFAULT_COLUMNS);
        this.setRows(DEFAULT_ROWS);

        this.setEditable(false);
    }
}