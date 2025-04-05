import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

@Epic("API Тесты")
@Feature("Тесты получения всех сущностей")
public class GetAllEntities extends BaseApiTest {
    private static final String GET_ALL_ENDPOINT = "/api/getAll";

    private String entityId;

    @BeforeClass
    @Description("Создание тестовой сущности")
    public void setup() {
        entityId = createTestEntity();
    }

    @Test
    @Description("Проверка получения всех сущностей")
    public void testAllEntities(){
        given()
                .when()
                .get(GET_ALL_ENDPOINT)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
        ;
    }

    @AfterClass
    @Description("Удаление тестовой сущности")
    public void cleanup() {
        if (entityId != null) {
            deleteTestEntity(entityId);
        }
    }
}
