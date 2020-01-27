package com.puckowski.apitest.gui;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import com.puckowski.apitest.http.RestRequest;
import com.puckowski.apitest.http.RestResponse;
import com.puckowski.apitest.json.RestAutomator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionListenerRunTests implements ActionListener
{
    private final JFrame MAIN_FRAME;
    private final JTabbedPane MAIN_TABBED_PANE;
    private final PanelTestResult TEST_RESULT_PANEL;

    public ActionListenerRunTests(final JFrame mainFrame, final JTabbedPane mainTabPane, final PanelTestResult testResultPanel) {
        MAIN_FRAME = mainFrame;
        MAIN_TABBED_PANE = mainTabPane;
        TEST_RESULT_PANEL = testResultPanel;
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        File selectedFile = SwingUtils.selectFileFromChooser(MAIN_FRAME);
        String queryStringToAppend = JOptionPane.showInputDialog("Optional query string to append: ");
        RestAutomator restAutomator = new RestAutomator(MAIN_TABBED_PANE, TEST_RESULT_PANEL, queryStringToAppend);
        restAutomator.runTestFile(selectedFile, MAIN_FRAME);
    }
}