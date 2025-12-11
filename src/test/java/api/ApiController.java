package api;

import core.BaseCore;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import models.ApiResponse;

import static core.Configuration.getBaseUrl;
import static io.restassured.RestAssured.given;

public class ApiController extends BaseCore {

    @Step("Отправить запрос с action: {action} и statusCode: {statusCode}")
    protected ApiResponse executeRequest(String token, String action, int statusCode) {
        return given()
                .spec(requestSpec)
                .formParam("token", token)
                .formParam("action", action)
                .when()
                .post("/endpoint")
                .then()
                .statusCode(statusCode)
                .extract().as(ApiResponse.class);
    }

    @Step("Отправить запрос с action: {action} и statusCode: {statusCode}")
    protected ApiResponse executeRequestWithCustomApiKey(String apiKey, String token, String action) {
        return given()
                .spec(new RequestSpecBuilder()
                        .setBaseUri(getBaseUrl())
                        .setContentType(ContentType.URLENC)
                        .setAccept(ContentType.JSON)
                        .addHeader("X-Api-Key", apiKey)
                        .addFilter(new AllureRestAssured())
                        .build())
                .formParam("token", token)
                .formParam("action", action)
                .when()
                .post("/endpoint")
                .then()
                .extract().as(ApiResponse.class);
    }
}
