package praktikum.client;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClient {
    public RestAssuredClient() {
        RestAssured.defaultParser = Parser.JSON;
    }
    public static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://qa-scooter.praktikum-services.ru/")
                .build();
    }

    }
