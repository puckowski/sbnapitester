package com.puckowski.apitest.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.puckowski.apitest.json.JsonCompareMethods;

import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class PanelCompareResponse extends JPanel
{
    private static final long serialVersionUID = 1L;

    private final PanelTextArea TEXT_AREA_PANEL;

    private ComboBoxCompareType mCompareModeComboBox;
    private JLabel mResultLabel;
    private JLabel mMethodDetailsLabel;

    public PanelCompareResponse(final PanelTextArea panelTextArea) {
        TEXT_AREA_PANEL = panelTextArea;

        this.buildPanel();
    }

    private ComboBoxCompareType buildCompareModeComboBox() {
        JsonCompareMethods jsonCompareMethods = new JsonCompareMethods();
        ComboBoxCompareType compareModeComboBox = new ComboBoxCompareType(jsonCompareMethods.getCompareMethods(), jsonCompareMethods, mMethodDetailsLabel);

        return compareModeComboBox;
    }

    private JButton buildCompareButton() {
        JButton compareButton = new JButton("Compare");
        compareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                final String responseText = TEXT_AREA_PANEL.getResponseTextArea().getText();
                final String expectedText = TEXT_AREA_PANEL.getExpectedTextArea().getText();
                final JSONCompareMode compareMode = mCompareModeComboBox.getSelectedCompareType();

                try {
                    JSONAssert.assertEquals(responseText, expectedText, compareMode);
                    mResultLabel.setText("Match.");
                } catch (final AssertionError assertionError) {
                    mResultLabel.setText("No match.");
                }
            }
        });
        
        return compareButton;
    }

    private JLabel buildResultLabel() {
        JLabel resultLabel = new JLabel("");
        
        return resultLabel;
    }

    private JLabel buildMethodDetailsLabel() {
        JLabel methodDetailsLabel = new JLabel("", SwingConstants.CENTER);
        
        return methodDetailsLabel;
    }

    private void buildPanel() {
        this.setLayout(new BorderLayout());

        final JButton compareButton = buildCompareButton();
        mMethodDetailsLabel = buildMethodDetailsLabel();
        mCompareModeComboBox = buildCompareModeComboBox();
        mResultLabel = buildResultLabel();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());

        contentPanel.add(mCompareModeComboBox);
        contentPanel.add(compareButton);

        this.add(mResultLabel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(mMethodDetailsLabel, BorderLayout.SOUTH);

        this.setBorder(SwingUtils.getDefaultBorderTop());
    }
    
}