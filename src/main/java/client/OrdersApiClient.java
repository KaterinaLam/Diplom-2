package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Authorization;
import models.Order;

import static io.restassured.RestAssured.given;


public class OrdersApiClient extends BaseHttpClient {

    @Step("Create order")
    public Response makeOrder(Order orderParameters, Authorization authorizationInfo) {
        return given()
                .spec(getRequestSpecification())
                .auth().oauth2(authorizationInfo.getAccessToken())
                .body(orderParameters.toJson())
                .when()
                .post("orders");
    }

    @Step("Create order without auth")
    public Response makeOrderWithoutAuth(Order orderParameters) {
        return given()
                .spec(getRequestSpecification())
                .body(orderParameters.toJson())
                .when()
                .post("orders");
    }

    @Step("Accept Order")
    public Response getOrders(Authorization authorizationInfo) {
        return given()
                .spec(getRequestSpecification())
                .auth().oauth2(authorizationInfo.getAccessToken())
                .when()
                .get("orders");
    }
}
