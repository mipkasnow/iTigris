package ru.itigris.optima.pages;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class Methods {

    public void opencContacLenses(){
        $(withText("Контактные линзы")).click();
        $(withText("Зарплатный отчет по продавцам")).should(appear);
    }

    public void openAppointmentForm(){
        $(".menuPanel .superfish #newTaskCount").hover();
        $(byText("Запись на прием")).click();
        $("#datepicker").should(appear);
    }

    public void openSearchTabOnAppointmentForm(){
        $("#searchTab").click();
    }

    public void clickFilterButton(){
        $("[onclick*='filterForm']").click();
    }

}
