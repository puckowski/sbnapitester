package com.puckowski.apitest.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.puckowski.apitest.json.RestAutomator;

public class PanelTestResultControls extends JPanel
{
    private static final long serialVersionUID = 1L;

    private final PanelTestResult TEST_RESULT_PANEL;
    private final JTabbedPane MAIN_TAB_PANE;
    private final JFrame MAIN_FRAME;

    public PanelTestResultControls(final PanelTestResult testResultPanel, 
        final JTabbedPane mainTabPane, final JFrame mainFrame) {
        TEST_RESULT_PANEL = testResultPanel;
        MAIN_TAB_PANE = mainTabPane;
        MAIN_FRAME = mainFrame;

        this.buildPanel();
    }

    private JButton buildCopyButton() {
        JButton copyButton = new JButton("Copy");
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                final String testResult = TEST_RESULT_PANEL.getTestResultTextArea().getText();
                
                StringSelection stringSelection = new StringSelection(testResult);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });
        
        return copyButton;
    }

    private JButton buildRunTestButton() {
        JButton runTestFileButton = new JButton("Run Test File");
        runTestFileButton.addActionListener(new ActionListenerRunTests(MAIN_FRAME, MAIN_TAB_PANE, TEST_RESULT_PANEL));

        return runTestFileButton;
    }

    private void buildPanel() {
        this.setLayout(new BorderLayout());

        final JButton copyButton = buildCopyButton();
        final JButton runTestFileButton = buildRunTestButton();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());

        contentPanel.add(copyButton);
        contentPanel.add(runTestFileButton);

        this.add(contentPanel, BorderLayout.CENTER);

        this.setBorder(SwingUtils.getDefaultBorderTop());
    }
    
}