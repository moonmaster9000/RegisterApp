package api.stepDefs;

import api.App;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

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
    protected WebApplicationContext context;

    protected void assertResponseCode(Integer responseCode) {
        assertNotNull(responseCode);
        assertEquals(responseCode, apiClient.getResponseStatusCode());
    }

    protected void assertJsonResponseIncludes(String desiredJson) {
        JSONAssert.assertEquals(desiredJson, apiClient.getResponseBody(), false);
    }

}
