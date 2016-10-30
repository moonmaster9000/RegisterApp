package api.stepDefs;

import cashregister.domain.repositories.interfaces.ItemRepository;
import cucumber.api.java8.En;

public class BeforeScenarios extends ApiStepDefs implements En {
    public BeforeScenarios() {
        Before(() -> {
            apiClient.resetState();
        });
    }
}
