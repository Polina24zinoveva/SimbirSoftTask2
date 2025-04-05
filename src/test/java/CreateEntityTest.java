import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("API Тесты")
@Feature("Тесты создания сущности")
public class CreateEntityTest extends BaseApiTest {

    @Test
    @Description("Проверка создания сущности")
    public void testCreateEntity() {
        String entityId = createTestEntity();

        given()
                .pathParam("id", entityId)
                .when()
                .get("/api/get/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(Integer.parseInt(entityId)));

        deleteTestEntity(entityId);
    }
}