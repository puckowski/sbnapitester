package com.puckowski.apitest;

import com.puckowski.apitest.gui.GuiMain;
import com.puckowski.apitest.gui.SwingUtils;

public final class App {

    public static void main(String[] args) {
        SwingUtils.attemptNativeLookAndFeel();
        GuiMain guiMain = new GuiMain();
    }
}
