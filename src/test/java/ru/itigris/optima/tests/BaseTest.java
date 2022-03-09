package ru.itigris.optima.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.Selenide.*;


public class BaseTest {


    @BeforeEach
    public void beforeEachSetUp(){
        String login = "client";
        String pass = "client2020";

        Configuration.baseUrl = "https://optima.itigris.ru/devops1?utm_source=yandex&utm_medium=email&utm_campaign" +
                "=shablon&utm_content=promo&utm_term=optima&b24form_user" +
                "=2.17241-1648032500-b21acfb53c00d2bf769574b1f6097025bf35b04149d283625b6af2ef515fe6eb";

        Configuration.proxyEnabled = true;
        Configuration.fileDownload = PROXY;
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 15000;

        Configuration.browserSize = "1900x900";
        open("");

        $("#login").setValue(login);
        $("#password").setValue(pass);
        $("#enterButton").click();
        $("#companyHeaderName").should(appear);
    }

    @AfterEach
    public void afterEachTearDown(){
        closeWebDriver();
    }

}
