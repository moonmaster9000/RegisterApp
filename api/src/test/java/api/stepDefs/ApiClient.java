package api.stepDefs;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
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

    void post(String uri, String mimeType) {
        HttpPost request = new HttpPost("http://localhost:8080" + uri);
        request.addHeader("content-type", "application/json");

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

    private void extractBodyFromResponse() {
        try {
            ResponseHandler<String> handler = new BasicResponseHandler();
            responseBody = handler.handleResponse(response);
        } catch (IOException e) {
            throw new RuntimeException("Streaming HTTP response body to a string failed unexpectedly", e);
        }
    }

    public void post(String url, String contentType, String requestJsonBody) {
        try {
            HttpPost request = new HttpPost("http://localhost:8080" + url);
            request.addHeader("content-type", contentType);
            StringEntity requestBody = new StringEntity(requestJsonBody);
            request.setEntity(requestBody);
            response = httpClient.execute(request);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding request body failed unexpectedly", e);
        } catch (IOException e) {
            throw new RuntimeException("http request failed unexpectedly", e);
        }
    }
}
