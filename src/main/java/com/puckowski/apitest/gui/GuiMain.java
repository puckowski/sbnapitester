package com.puckowski.apitest.gui;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.puckowski.apitest.json.RestAutomator;
import com.puckowski.apitest.utils.Utils;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GuiMain {
    private final String FRAME_TITLE = "SBN API Tester";
    private final int FRAME_PREFERRED_WIDTH_PIXELS = 1200;
    private final int FRAME_PREFERRED_HEIGHT_PIXELS = 740;

    private JFrame mFrame;
    private PanelTestResult mPanelTestResult;

    public GuiMain() {
        buildFrame();
    }

    private String getFrameTitle() {
        return this.FRAME_TITLE + " v" + Utils.APP_VERSION_STRING;
    }

    private PanelRequestInput buildPanelRequestInput(final PanelTextArea textAreaPanel) {
        PanelRequestInput requestInputPanel = new PanelRequestInput(textAreaPanel);

        return requestInputPanel;
    }

    private PanelTextArea buildPanelTextArea() {
        PanelTextArea panelTextArea = new PanelTextArea();

        return panelTextArea;
    }

    private PanelFormatArea buildPanelFormatArea() {
        PanelFormatArea panelFormatArea = new PanelFormatArea();

        return panelFormatArea;
    }

    private PanelTestResult buildPanelTestResult() {
        PanelTestResult panelTestResult = new PanelTestResult();

        return panelTestResult;
    }

    private JMenuBar buildMenuBar(final JTabbedPane mainTabPane, final PanelTestResult testResultPanel) {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("File");

        JMenuItem openTestFileItem = new JMenuItem("Open test file...");
        openTestFileItem.addActionListener(new ActionListenerRunTests(mFrame, mainTabPane, testResultPanel));
        menu.add(openTestFileItem);

        menuBar.add(menu);

        return menuBar;
    }

    private JScrollPane buildMainContentPane() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        PanelTextArea panelTextArea = buildPanelTextArea();

        contentPanel.add(panelTextArea, BorderLayout.CENTER);

        PanelRequestInput requestInputPanel = new PanelRequestInput(panelTextArea);

        contentPanel.add(requestInputPanel, BorderLayout.NORTH);

        PanelCompareResponse compareResponsePanel = new PanelCompareResponse(panelTextArea);

        contentPanel.add(compareResponsePanel, BorderLayout.SOUTH);

        JScrollPane contentScrollPane = new JScrollPane(contentPanel);

        return contentScrollPane;
    }

    private JScrollPane buildFormatContentPane() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        PanelFormatArea panelFormatArea = buildPanelFormatArea();

        contentPanel.add(panelFormatArea, BorderLayout.CENTER);

        PanelFormatJson formatJsonPanel = new PanelFormatJson(panelFormatArea);

        contentPanel.add(formatJsonPanel, BorderLayout.SOUTH);

        JScrollPane contentScrollPane = new JScrollPane(contentPanel);

        return contentScrollPane;
    }

    private JScrollPane buildTestResultPane(JTabbedPane mainTabPane) {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        mPanelTestResult = buildPanelTestResult();

        contentPanel.add(mPanelTestResult, BorderLayout.CENTER);

        PanelTestResultControls testResultControlsPanel = new PanelTestResultControls(mPanelTestResult, mainTabPane, mFrame);

        contentPanel.add(testResultControlsPanel, BorderLayout.SOUTH);

        JScrollPane contentScrollPane = new JScrollPane(contentPanel);

        return contentScrollPane;
    }

    private void buildFrame() {
        mFrame = new JFrame(getFrameTitle());
        mFrame.setLayout(new BorderLayout());
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setPreferredSize(new Dimension(FRAME_PREFERRED_WIDTH_PIXELS, FRAME_PREFERRED_HEIGHT_PIXELS));

        JTabbedPane tabPane = new JTabbedPane();
        JScrollPane mainScrollPane = buildMainContentPane();
        JScrollPane formatScrollPane = buildFormatContentPane();
        JScrollPane testResultScrollPane = buildTestResultPane(tabPane);

        tabPane.addTab("Manual Testing", mainScrollPane);
        tabPane.addTab("Format JSON", formatScrollPane);
        tabPane.addTab("Automated Testing", testResultScrollPane);

        mFrame.add(tabPane, BorderLayout.CENTER);

        JMenuBar menuBar = buildMenuBar(tabPane, mPanelTestResult);
        mFrame.setJMenuBar(menuBar);

        mFrame.pack();
        SwingUtils.centerFrame(mFrame);
        mFrame.setVisible(true);
    }
    
}