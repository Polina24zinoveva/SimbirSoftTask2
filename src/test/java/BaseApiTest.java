import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import pojo.Entity;

import static io.restassured.RestAssured.given;

public class BaseApiTest {
    protected static final String BASE_URL = "http://localhost:8080";
    protected static final String CREATE_ENDPOINT = "/api/create";
    protected static final String DELETE_ENDPOINT = "/api/delete/{id}";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Description("Создание тестовой сущности")
    protected String createTestEntity() {
        Entity entityPojo = Entity.builder().build();
        return given()
                .contentType("application/json")
                .body(entityPojo)
                .when()
                .post(CREATE_ENDPOINT)
                .then()
                .statusCode(200)
                .contentType("text/plain")
                .extract()
                .asString();
    }

    @Description("Удаление тестовой сущности")
    protected void deleteTestEntity(String id) {
        given()
                .pathParam("id", id)
                .when()
                .delete(DELETE_ENDPOINT)
                .then()
                .statusCode(204);
    }
}