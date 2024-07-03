package utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
        import utils.ImageToWord;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScreenshotListener implements IInvokedMethodListener, ITestListener {

    //    private WebDriver driver;
    private XWPFDocument document;
    private String docFilePath = "TestResults.docx";

    public static ArrayList<String> screenshotlist = new ArrayList<String>();

//    public ScreenshotListener(WebDriver driver) {
//        this.driver = driver;
//        this.document = new XWPFDocument();
//    }

//    private void insertScreenshotIntoDocument(File screenshotFile) throws IOException, InvalidFormatException {
//        XWPFParagraph paragraph = document.createParagraph();
//        XWPFRun run = paragraph.createRun();
//        run.setText(screenshotFile.getName());
//
//        FileInputStream fis = new FileInputStream(screenshotFile);
//        run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, screenshotFile.getName(), Units.toEMU(500), Units.toEMU(300));
//        fis.close();
//
//        FileOutputStream fos = new FileOutputStream(docFilePath);
//        document.write(fos);
//        fos.close();
//    }


    public void onFinish(ITestContext context) {
//        System.out.println(ScreenshotListener.screenshotlist);
        try {
            ImageToWord.appendImagesToWord(ScreenshotListener.screenshotlist,"output.docx" +System.currentTimeMillis() + ".docx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
