package api.stepDefs;

import cucumber.api.java.en.When;
import cucumber.api.java8.En;

public class ShowTransactionStepDefs  extends ApiStepDefs implements En {
    @When("^GET url:\"([^\"]*)\"$")
    public void getUrl(String uri) throws Throwable {
        apiClient.get(uri);
    }
}
