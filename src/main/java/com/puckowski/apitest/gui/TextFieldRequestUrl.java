package com.puckowski.apitest.gui;

import javax.swing.JTextField;

public class TextFieldRequestUrl extends JTextField
{
    private static final long serialVersionUID = 1L;

    private final int DEFAULT_COLUMNS = 50;

    public TextFieldRequestUrl() {
        this.setColumns(DEFAULT_COLUMNS);
        this.setEditable(true);
        this.setFont(SwingUtils.getDefaultFont());
    }
}