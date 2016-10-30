package api.stepDefs;

import cucumber.api.java8.En;

public class CreateTransactionStepDefs extends ApiStepDefs implements En {
    public CreateTransactionStepDefs() {
        When("^POST Content-Type:\"([^\"]*)\" url:\"([^\"]*)\"$", (String contentType, String uri) -> {
            apiClient.post(uri, contentType);
        });

        Then("^(\\d+) response:$", (Integer responseCode, String expectedJson) -> {
            assertResponseCode(responseCode);
            assertJsonResponseIncludes(expectedJson);
        });
    }
}
