package one.digitalinnovation.parking.controller;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {//extends AbstractContainerBase{

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {

        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicense("AAA-5464");
        createDTO.setModel("ESCORT");
        createDTO.setState("PB");

        RestAssured.given()

                .auth()
                .basic("user","Test@123")
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

//    @Test
//    void whenCreateThenCheckIsCreated() {
//
//        var createDTO = new ParkingCreateDTO();
//        createDTO.setColor("AMARELO");
//        createDTO.setLicense("AAA-5464");
//        createDTO.setModel("ESCORT");
//        createDTO.setState("PB");
//
//        RestAssured.given()
//                .auth()
//                .basic("user","Test@123")
//                .when()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(createDTO)
//                .post("/parking")
//                .then()
//                .statusCode(HttpStatus.CREATED.value())
//                .body("color", Matchers.equalTo("AMARELO"))
//                .body("license", Matchers.equalTo("AAA-5464"))
//                .body("model", Matchers.equalTo("ESCORT"))
//                .body("state", Matchers.equalTo("PB"));
//
//    }
}