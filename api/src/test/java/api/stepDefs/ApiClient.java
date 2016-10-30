package api.stepDefs;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
class ApiClient {
    private final HttpClient httpClient;
    private HttpResponse response;
    private String responseBody;

    ApiClient(){
        httpClient = HttpClientBuilder.create().build();
    }

    void resetState(){
        response = null;
        responseBody = null;
    }

    void post(String uri, String contentType) {
        HttpPost request = createBaseJsonRequest(uri, contentType);

        executeRequestAndSaveResponse(request);
    }

    private HttpPost createBaseJsonRequest(String uri, String contentType) {
        HttpPost request = new HttpPost("http://localhost:8080" + uri);
        request.addHeader("content-type", contentType);
        return request;
    }

    private void executeRequestAndSaveResponse(HttpPost request) {
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException("http request failed unexpectedly", e);
        }
    }

    String getResponseBody() {
        if (responseBody == null){
            extractBodyFromResponse();
        }

        return responseBody;
    }

    Integer getResponseStatusCode() {
        return (Integer)response.getStatusLine().getStatusCode();
    }

    void post(String uri, String contentType, String requestJsonBody) {
        HttpPost request = createBaseJsonRequest(uri, contentType);
        StringEntity requestBody = createRequestBody(requestJsonBody);

        request.setEntity(requestBody);
        executeRequestAndSaveResponse(request);
    }

    private void extractBodyFromResponse() {
        try {
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Streaming HTTP response body to a string failed unexpectedly", e);
        }
    }

    private StringEntity createRequestBody(String requestJsonBody) {
        StringEntity requestBody;

        try {
            requestBody = new StringEntity(requestJsonBody);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding request body failed unexpectedly", e);
        }
        return requestBody;
    }
}
