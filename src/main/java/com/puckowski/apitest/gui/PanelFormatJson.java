package com.puckowski.apitest.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.puckowski.apitest.json.JsonTestFormatter;

public class PanelFormatJson extends JPanel
{
    private static final long serialVersionUID = 1L;

    private final PanelFormatArea FORMAT_AREA_PANEL;

    public PanelFormatJson(final PanelFormatArea panelFormatArea) {
        FORMAT_AREA_PANEL = panelFormatArea;

        this.buildPanel();
    }

    private JButton buildFormatButton() {
        JButton formatButton = new JButton("Format");
        formatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                final String toFormatText = FORMAT_AREA_PANEL.getToFormatTextArea().getText();
                JsonTestFormatter jsonTetsFormatter = new JsonTestFormatter();
                final String formattedText = jsonTetsFormatter.formatRawJson(toFormatText);

                FORMAT_AREA_PANEL.getFormattedTextArea().setText(formattedText);
            }
        });
        
        return formatButton;
    }

    private void buildPanel() {
        this.setLayout(new BorderLayout());

        final JButton formatButton = buildFormatButton();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());

        contentPanel.add(formatButton);

        this.add(contentPanel, BorderLayout.CENTER);

        this.setBorder(SwingUtils.getDefaultBorderTop());
    }
    
}