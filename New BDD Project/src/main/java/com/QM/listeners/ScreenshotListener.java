package com.QM.listeners;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.testng.*;
import java.io.IOException;
import java.util.ArrayList;

public class ScreenshotListener implements IInvokedMethodListener, ITestListener {

    private XWPFDocument document;
    private String docFilePath = "TestResults.docx";

    public static ArrayList<String> screenshotlist = new ArrayList<String>();

    public void onFinish(ITestContext context) {
//        System.out.println("JKA: "+ScreenshotListener.screenshotlist);
        System.out.println("JKA:");
        // try {
        //     // ImageToWord.appendImagesToWord(ScreenshotListener.screenshotlist,"ReportsOTROP/Output.docx");
        //     System.out.println("JKA: "+ScreenshotListener.screenshotlist);
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }
    }
}
