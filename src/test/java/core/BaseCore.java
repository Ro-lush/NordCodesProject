package core;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;


import static core.Configuration.*;


public class BaseCore {
    protected RequestSpecification requestSpec;

    @BeforeEach
    void setup() {
        // Базовая спецификация для всех запросов
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(getBaseUrl())
                .setContentType(ContentType.URLENC)
                .setAccept(ContentType.JSON)
                .addHeader("X-Api-Key", getApiKey())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new AllureRestAssured())
                .build();

        // Настройка REST Assured
        RestAssured.config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 10000)
                        .setParam("http.socket.timeout", 10000));
    }

}
