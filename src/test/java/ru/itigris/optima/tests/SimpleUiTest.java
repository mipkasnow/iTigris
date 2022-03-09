package ru.itigris.optima.tests;

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Test;
import ru.itigris.optima.pages.Methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SimpleUiTest extends BaseTest{

    Methods method = new Methods();

    private ClassLoader cl = SimpleUiTest.class.getClassLoader();


    @Test
    public void checkExcelTest() throws Exception{
        // Открываем контактные линзы
        method.opencContacLenses();

        // Скачиваем Excel отчет
        File excelFile = $("[value='Экспорт в Excel']").download();

        // Парсим Excel отчет - проверяем выборочную ячейку по содержанию
        try(InputStream is = new FileInputStream(excelFile)) {
            XLS parsed = new XLS(is);
            assertThat(parsed.excel.getSheetAt(0).getRow(18).getCell(0).getStringCellValue()).
                   contains("Кадын Тимофей Юрьевич");
        }
    }

    @Test
    public void makeAppointmentSearchTest(){

        method.openAppointmentForm();
        method.openSearchTabOnAppointmentForm();

        // Выбираем в календаре произвольнцю дату (01 декабря 2021)
        $(".specialTd #startDate").click();
        while (($(".ui-datepicker-title").$(byText("2022")).is(exist))){
            $(".ui-datepicker-prev").click();
        }
        $(byText("1")).click();

        // Ищем записи, открываем третью по счету (Иванов)
        method.clickFilterButton();
        $$("[onclick*='registryRecord']").get(2).click();

        // Ассерт содержимого карточки
        $("#showDiv div .border").shouldHave(
                text("Специалист:"), text("Кадын Тимофей Юрьевич"),
                text("Департамент:"), text("Второй магазин"),
                text("Дата приема:"), text("07-02-2022"),
                text("Время приема:"), text("10:30"),
                text("Услуга:"), text("Подбор очков (0 руб.)"),
                text("Статус:"), text("Подтверждена"),
                text("ФИО клиента:"), text("Иванов"),
                text("Телефон клиента:"), text("89217778899")
        );
    }

}
