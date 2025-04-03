package ru.frigesty.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;
import static ru.frigesty.helpers.CustomAllureListener.withCustomTemplates;

public class ApiSpecs {
    public static RequestSpecification requestSpecBase = with()
            .log().all()
            .filter(withCustomTemplates())
            .contentType(JSON);

    public static ResponseSpecification responseSpecBase = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();
}