package org.example;

import jdk.jfr.Description;
import jxl.read.biff.BiffException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;

import static util.Constants.HOME_PAGE_URL;

public class HomePageTest extends BasePageTest{
    HomePage homePage;
    ExcelControl excelControl = new ExcelControl();

    @BeforeEach
    public void before(){
        super.before();
        homePage = goToUrl(new HomePage(driver, wait), HOME_PAGE_URL);
    }

    @Description("Input search value and then remove current value")
    @Test
    public void checkFakeSearch() throws BiffException, IOException {
        homePage.closePopups();
        homePage.fakeSearch(excelControl.readExcel().get(0));
    }

    @Description("Input Search value and then click current value")
    @Test
    public void checkSearch() throws BiffException, IOException {
        homePage.closePopups();
        homePage.search(excelControl.readExcel().get(1));
    }
}
