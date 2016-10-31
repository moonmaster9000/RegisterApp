package api.stepDefs;

import cucumber.api.java8.En;

public class CreateTransactionStepDefs extends ApiStepDefs implements En {
    public CreateTransactionStepDefs() {
        When("^POST Content-Type:\"([^\"]*)\" url:\"([^\"]*)\"$", (String contentType, String uri) -> {
            System.out.println("posting empty body to " + uri);
            apiClient.post(uri, contentType);
        });

        Then("^(\\d+) response:$", (Integer responseCode, String expectedJson) -> {
            System.out.println("asserting response code");
            assertResponseCode(responseCode);

            System.out.println("asserting expected json");
            assertJsonResponseIncludes(expectedJson);
        });
    }
}
