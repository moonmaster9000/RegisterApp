package api.stepDefs;

import api.App;
import cashregister.domain.repositories.interfaces.ItemRepository;
import cashregister.domain.repositories.interfaces.TransactionRepository;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    loader = SpringApplicationContextLoader.class,
    classes = App.class
)
@WebIntegrationTest
public class ApiStepDefs {
    @Autowired
    protected ApiClient apiClient;

    @Autowired
    protected ItemRepository itemRepository;

    @Autowired
    protected TransactionRepository transactionRepository;

    protected void assertResponseCode(Integer responseCode) {
        assertNotNull(responseCode);
        assertEquals(responseCode, apiClient.getResponseStatusCode());
    }

    protected void assertJsonResponseIncludes(String desiredJson) {
        JSONAssert.assertEquals(desiredJson, apiClient.getResponseBody(), false);
    }

}
