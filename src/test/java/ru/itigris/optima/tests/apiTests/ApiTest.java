package ru.itigris.optima.tests.apiTests;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiTest {

    @Test
    public void ApiGetToken(){
        // Отправляем post запрос по методу login и достаем куки. Проверяем, что статус запроса 200
        Map<String, String> authCookie =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("login=client&password=client2020&key=&versionDesc=559_21.02.2022&" +
                                "&userId=&uuidValue=d936ba35-845c-4c83-8fb7-86ca56bdbabc&pageUUID=9a657331-38a3-4615-abb7-4e9422840cd8&companyUUID=devops1")
                        .when()
                        .log().all()
                        .post("https://optima.itigris.ru/devops1/login/login")
                        .then()
                        .statusCode(200)
                        .extract().cookies();

        String JSESSIONID = authCookie.get("JSESSIONID");
        String route = authCookie.get("route");

        System.out.println(JSESSIONID);
        System.out.println(route);
    }
}
