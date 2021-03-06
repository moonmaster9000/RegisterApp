package api.stepDefs;

import cucumber.api.java8.En;

public class CreateItemStepDefs extends ApiStepDefs implements En {
    public CreateItemStepDefs() {
        When("^POST Content-Type:\"([^\"]*)\" url:\"([^\"]*)\" body:$", (String contentType, String url, String requestJsonBody) -> {
            apiClient.post(url, contentType, requestJsonBody);
        });
    }
}
