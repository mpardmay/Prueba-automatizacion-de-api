package com.everis.base;

import io.cucumber.java.Before;
import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.*;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class freeToGameService {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(freeToGameService.class);
    static private final String BASE_URL = "https://www.freetogame.com/api/games";

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    private Response response;
    private RequestSpecBuilder builder;
    private RequestSpecification requestSpecification;
    private String bodyPost;

    @Before
    public void init() {

        LOGGER.info(" Inicializa el constructor request ");
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();

        LOGGER.info(" Inicializa el constructor response ");
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }



    public void validateStatusCode(int i) {
        LOGGER.info("validadon codigo de estado" + lastResponse().statusCode());
        assertThat(lastResponse().statusCode(), is(i));
    }

    public void printIdAndTittleOfEachGame(){
        LOGGER.info("imprimiendo los titulos de los juegos");
        String id = lastResponse().jsonPath().param("", 0).getString("id");
        String title = lastResponse().jsonPath().param("", 0).getString("title");
        System.out.println(id + " - " + title);
    }

    @Step("obtiene la lista de juegos")
    public void listGames(String platform, String category) {

        given().
                spec(requestSpec).
                queryParam("platform", platform).
                queryParam("category", category).
                when().
                then().
                spec(responseSpec);
    }
}
