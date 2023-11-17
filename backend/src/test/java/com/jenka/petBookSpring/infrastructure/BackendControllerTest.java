package com.jenka.petBookSpring.infrastructure;

import com.jenka.petBookSpring.PetBookSpringApplication;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SpringBootTest(
    classes = PetBookSpringApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class BackendControllerTest {

  @LocalServerPort
  private int port;

  @BeforeEach
  public void init() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }

  @Test
  public void saysHello() {
    when()
        .get("http://localhost:\" + port + \"/api/hello")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .assertThat()
        .body(is(equalTo(BackendController.HELLO_TEXT)));
  }

//  @Test
//  public void addNewUserAndRetrieveItBack() {
//    User norbertSiegmund = new User("Norbert", "Siegmund");
//
//    Long userId =
//        given()
//            .pathParam("firstName", "Norbert")
//            .pathParam("lastName", "Siegmund")
//            .when()
//            .post("/api/user/{lastName}/{firstName}")
//            .then()
//            .statusCode(is(HttpStatus.SC_CREATED))
//            .extract()
//            .body().as(Long.class);
//
//    User responseUser =
//        given()
//            .pathParam("id", userId)
//            .when()
//            .get("/api/user/{id}")
//            .then()
//            .statusCode(HttpStatus.SC_OK)
//            .assertThat()
//            .extract().as(User.class);
//
//    // Did Norbert came back?
//    assertThat(responseUser.getFirstName(), is("Norbert"));
//    assertThat(responseUser.getLastName(), is("Siegmund"));
  }
