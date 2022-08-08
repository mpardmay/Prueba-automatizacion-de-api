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


    @Step("verifica que los codigos de respuestas sean iguales")
    public void validateStatusCode(int i) {
        LOGGER.info("validadon codigo de estado " + lastResponse().statusCode());
        restAssuredThat(response -> response.statusCode(i));
    }
    @Step("Imprime el id y el titulo de cada juego encontrado")
    public void printIdAndTittleOfEachGame(){
        LOGGER.info("imprimiendo los titulos de los juegos");
        String idString = lastResponse().jsonPath().param("", 0).getString("id");
        String titleString = lastResponse().jsonPath().param("", 0).getString("title");
        titleString = titleString.replace("[", "")
                .replace("]", "");
        String[] idList = idString.split(",");
        String[] titleList = titleString.split(",");


        for (int i=0; i < idList.length; i++){
            //eliminacion de ",", "[" y "]"
            titleList[i] = titleList[i].replace(",", "")
                    .replace("[", "")
                    .replace("]", "");
            idList[i] = idList[i].replace(",", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "");
            //eliminacion del primer caracter " " que se encuentra en el segundo elemento en adelante
            titleList[i] = (i > 0) ? titleList[i].substring(1, titleList[i].length()- 1):titleList[i];
            //impresion de el id del juego junto a su titulo
            System.out.println(idList[i] + " - " + titleList[i]);
        }


    }

    @Step("obtiene la lista de juegos")
    public void listGames(String platform, String category) {
        LOGGER.info("i lista de juegos");
        given().
                spec(requestSpec).
                queryParam("platform", platform).
                queryParam("category", category).
                get();

    }
}
