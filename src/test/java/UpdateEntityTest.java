import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.Entity;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("API Тесты")
@Feature("Тесты изменения сущности")
public class UpdateEntityTest extends BaseApiTest {
    private static final String PATCH_ENDPOINT = "/api/patch/{id}";
    private String entityId;

    @BeforeClass
    @Description("Создание тестовой сущности")
    public void setup() {
        entityId = createTestEntity();
    }

    @Test
    @Description("Проверка изменения сущности")
    public void testPatchEntity_Success() {
        Entity.Addition addition = Entity.Addition.builder()
                .additional_info("Updated Info")
                .additional_number(456)
                .build();

        Entity updateData = Entity.builder()
                .title("Updated Title")
                .verified(false)
                .important_numbers(List.of(1, 2, 3))
                .addition(addition)  // Передаем созданный объект Addition
                .build();

        given()
                .pathParam("id", entityId)
                .contentType("application/json")
                .body(updateData)
                .when()
                .patch(PATCH_ENDPOINT)
                .then()
                .statusCode(204);
    }

    @Test
    @Description("Проверка частичного изменения сущности")
    public void testPatchEntity_PartialUpdate() {
        Entity partialUpdate = Entity.builder()
                .title("Partial Update")
                .build();

        given()
                .pathParam("id", entityId)
                .contentType("application/json")
                .body(partialUpdate)
                .when()
                .patch(PATCH_ENDPOINT)
                .then()
                .statusCode(204);
    }

    @AfterClass
    @Description("Удаление тестовой сущности")
    public void cleanup() {
        if (entityId != null) {
            deleteTestEntity(entityId);
        }
    }
}