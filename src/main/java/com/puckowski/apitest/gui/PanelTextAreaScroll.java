package com.puckowski.apitest.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelTextAreaScroll extends JPanel {
    private static final long serialVersionUID = 236310921677085783L;

    private JTextArea mTextArea;
    private JLabel mLabel;
    private JButton mClearButton;

    public PanelTextAreaScroll(final String label, final JTextArea textArea) {
        mLabel = new JLabel(label);
        mTextArea = textArea;

        buildPanel();
    }

    private JButton buildClearButton() {
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                mTextArea.setText("");
            }
        });

        return clearButton;
    }

    private void buildPanel() {
        this.setLayout(new BorderLayout());

        mLabel.setFont(SwingUtils.getDefaultFont());

        JScrollPane textAreaScrollPane = new JScrollPane(mTextArea);

        mClearButton = buildClearButton();

        this.add(mLabel, BorderLayout.NORTH);
        this.add(textAreaScrollPane, BorderLayout.CENTER);
        this.add(mClearButton, BorderLayout.SOUTH);
    }
}