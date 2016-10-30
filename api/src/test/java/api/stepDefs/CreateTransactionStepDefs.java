package api.stepDefs;

import api.App;
import cucumber.api.java8.En;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    loader = SpringApplicationContextLoader.class,
    classes = App.class
)
@WebIntegrationTest(randomPort = true)
public class CreateTransactionStepDefs implements En {
    @Value("${local.server.port}")
    int port;

    @Autowired
    private WebApplicationContext context;

    private HttpResponse response;
    private HttpClient httpClient;
    private String responseBody;

    public CreateTransactionStepDefs() {
        Before(this::before);

        When("^POST Content-Type:\"([^\"]*)\" url:\"([^\"]*)\"$", (String contentType, String uri) -> {
            post(uri, contentType);
        });

        Then("^(\\d+) response:$", (Integer responseCode, String expectedJson) -> {
            assertResponseCode(responseCode);
            assertJsonResponseIncludes(expectedJson, getResponseBody());
        });
    }

    private void assertResponseCode(Integer responseCode) {
        assertNotNull(responseCode);
        assertEquals(responseCode, (Integer)response.getStatusLine().getStatusCode());
    }

    public void assertJsonResponseIncludes(String desiredJson, String actualJsonResponse) {
        JSONAssert.assertEquals(desiredJson, actualJsonResponse, false);
    }

    private String getResponseBody() {
        if (responseBody == null){
            extractBodyFromResponse();
        }

        return responseBody;
    }

    private void extractBodyFromResponse() {
        try {
            ResponseHandler<String> handler = new BasicResponseHandler();
            responseBody = handler.handleResponse(response);
        } catch (IOException e) {
            throw new RuntimeException("Streaming HTTP response body to a string failed unexpectedly", e);
        }
    }

    private void post(String uri, String mimeType) {
        HttpPost request = new HttpPost("http://localhost:"+ port + uri);
        request.addHeader("content-type", "application/json");

        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException("http request failed unexpectedly", e);
        }
    }

    private void before(){
        httpClient = HttpClientBuilder.create().build();
    }
}
