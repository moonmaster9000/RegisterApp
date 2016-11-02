package api.stepDefs;

import cucumber.api.java8.En;

public class BeforeScenarios extends ApiStepDefs implements En {
    public BeforeScenarios() {
        Before(() -> {
            apiClient.resetState();
            transactionRepository.deleteAll();
            itemRepository.deleteAll();
        });
    }
}
