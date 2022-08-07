package com.everis.base.stepDefinitions;

import com.everis.base.freeToGameService;
//import com.everis.base.steps.NetflixSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author jovallep
 */
public class freeToGameSD {

    @Steps
    public freeToGameService netflix;


    @When("obtiene los parametros platform {string} y category {string}")
    public void obtieneLosParametrosPlatformYCategory(String platform, String category) {
        netflix.listGames(platform, category);
    }

    @Then("valido que la respuesta sea {int}")
    public void validoQueLaRespuestaSea(int response) {
        netflix.validateStatusCode(response);
    }

    @And("imprima el id y el title de todos los registros de la consulta")
    public void imprimaElIdYElTitleDeTodosLosRegistrosDeLaConsulta() {
        netflix.printIdAndTittleOfEachGame();
    }

    @Given("que la aplicacion este operativa")
    public void queLaAplicacionEsteOperativaYSuRespuestaSea() {
    }

}
