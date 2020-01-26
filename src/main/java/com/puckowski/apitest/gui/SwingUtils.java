package com.puckowski.apitest.gui;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.io.File;

public class SwingUtils 
{
    private static final Font DEFAULT_FONT = new Font("Courier", Font.PLAIN, 20);
    private static final Border DEFAULT_EMPTY_BORDER = new EmptyBorder(10, 10, 10, 10);
    private static final Border DEFAULT_EMPTY_BORDER_TOP = new EmptyBorder(10, 0, 0, 0);
    private static final Border DEFAULT_EMPTY_BORDER_RIGHT = new EmptyBorder(0, 0, 0, 10);
    private static final Border DEFAULT_EMPTY_BORDER_BOTTOM = new EmptyBorder(0, 0, 10, 0);
    private static final Border DEFAULT_EMPTY_BORDER_LEFT = new EmptyBorder(0, 10, 0, 0);

    public static void centerFrame(final JFrame frameToCenter) {
        frameToCenter.setLocationRelativeTo(null);
    }

    public static void attemptNativeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Font getDefaultFont() {
        return DEFAULT_FONT;
    }

    public static Border getDefaultBorder() {
        return DEFAULT_EMPTY_BORDER;
    }

    public static Border getDefaultBorderTop() {
        return DEFAULT_EMPTY_BORDER_TOP;
    }

    public static Border getDefaultBorderRight() {
        return DEFAULT_EMPTY_BORDER_RIGHT;
    }

    public static Border getDefaultBorderBottom() {
        return DEFAULT_EMPTY_BORDER_BOTTOM;
    }

    public static Border getDefaultBorderLeft() {
        return DEFAULT_EMPTY_BORDER_LEFT;
    }

    public static File selectFileFromChooser(final JFrame frame) {
        final JFileChooser fileChooser = new JFileChooser();
        final int returnVal = fileChooser.showOpenDialog(frame);

        File selectedFile = null;

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }

        return selectedFile;
    }

    public static void showWarningDialog(final JFrame frame, final String title, final String message) {
        JOptionPane.showMessageDialog(frame, title, message, JOptionPane.WARNING_MESSAGE);
    }
}