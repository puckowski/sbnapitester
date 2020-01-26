package com.puckowski.apitest.gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.puckowski.apitest.json.JsonCompareMethods;

import org.skyscreamer.jsonassert.JSONCompareMode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class ComboBoxCompareType extends JComboBox<String> implements ActionListener
{
    private static final long serialVersionUID = 1L;

    private final JLabel METHOD_DETAILS_LABEL;

    private String mSelectedCompareType;
    private JsonCompareMethods mCompareMethods;

    public ComboBoxCompareType(final String[] optionStrings, final JsonCompareMethods jsonCompareMethods, final JLabel methodDetailsLabel) {
        super(optionStrings);

        METHOD_DETAILS_LABEL = methodDetailsLabel;

        mCompareMethods = jsonCompareMethods;

        this.addActionListener(this);
        this.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof JComboBox<?>) {
            JComboBox<String> sourceComboBox = (JComboBox<String>) actionEvent.getSource();

            mSelectedCompareType = (String) sourceComboBox.getSelectedItem();
            METHOD_DETAILS_LABEL.setText(mCompareMethods.getCompareModeDetails(mSelectedCompareType));
        }
    }

    public JSONCompareMode getSelectedCompareType() {
        return mCompareMethods.getCompareMode(mSelectedCompareType);
    }
}