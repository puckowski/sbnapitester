package com.puckowski.apitest.gui;

import javax.swing.JTextArea;

public class TextAreaBody extends JTextArea
{
    private static final long serialVersionUID = 1L;

    private final int DEFAULT_COLUMNS = 50;
    private final int DEFAULT_ROWS = 30;

    public TextAreaBody() {
        this.setColumns(DEFAULT_COLUMNS);
        this.setRows(DEFAULT_ROWS);

        this.setEditable(true);
    }
}