package com.puckowski.apitest.gui;

import javax.swing.JButton;

public class ButtonSendRequest extends JButton
{
    private static final long serialVersionUID = 1L;

    private final String DEFAULT_TEXT = "Send";

    public ButtonSendRequest() {
        this.setText(DEFAULT_TEXT);
    }
}