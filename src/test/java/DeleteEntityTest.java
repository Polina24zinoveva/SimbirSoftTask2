import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Epic("API Тесты")
@Feature("Тесты удаления сущности")
public class DeleteEntityTest extends BaseApiTest {

    @Test
    @Description("Проверка удаления сущности")
    public void testDeleteEntity() {
        String entityId = createTestEntity();

        given()
                .pathParam("id", entityId)
                .when()
                .delete(DELETE_ENDPOINT)
                .then()
                .statusCode(204);
    }
}
