package com.puckowski.apitest.gui;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class ComboBoxRequestType extends JComboBox<String> implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private String mSelectedRequestType;

    public ComboBoxRequestType(final String[] optionStrings) {
        super(optionStrings);

        this.addActionListener(this);
        this.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof JComboBox<?>) {
            JComboBox<String> sourceComboBox = (JComboBox<String>) actionEvent.getSource();

            mSelectedRequestType = (String) sourceComboBox.getSelectedItem();
        }
    }

    public String getSelectedRequestType() {
        return mSelectedRequestType;
    }
}