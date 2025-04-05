import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("API Тесты")
@Feature("Тесты получения сущности")
public class GetEntityTest extends BaseApiTest{
    private static final String GET_ENDPOINT = "/api/get/{id}";
    private String entityId;

    @BeforeClass
    @Description("Создание тестовой сущности")
    public void setup() {
        entityId = createTestEntity();
    }

    @Test
    @Description("Проверка получения сущностей")
    public void testGetEntity(){
        given()
                .pathParam("id", entityId)
                .when()
                .get(GET_ENDPOINT)
                .then()
                .statusCode(200)
                .body("id", equalTo(Integer.parseInt(entityId)));
    }

    @AfterClass
    @Description("Удаление тестовой сущности")
    public void cleanup() {
        if (entityId != null) {
            deleteTestEntity(entityId);
        }
    }
}
