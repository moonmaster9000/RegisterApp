package api.stepDefs;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
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
        httpClient = httpClientWithLargeConnectionPool();
    }

    void resetState(){
        response = null;
        responseBody = null;
    }

    void post(String uri, String contentType) {
        resetState();
        HttpPost request = createBaseRequest(uri, contentType);
        executeRequestAndSaveResponse(request);
    }

    void post(String uri, String contentType, String requestJsonBody) {
        resetState();
        HttpPost request = createBaseRequest(uri, contentType);
        request.setEntity(createRequestBody(requestJsonBody));
        executeRequestAndSaveResponse(request);
    }

    void get(String uri) {
        resetState();
        HttpGet request = new HttpGet(url(uri));
        executeRequestAndSaveResponse(request);
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

    private void executeRequestAndSaveResponse(HttpPost request) {
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException("http request failed unexpectedly", e);
        }
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

    private CloseableHttpClient httpClientWithLargeConnectionPool() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        HttpHost localhost = new HttpHost("locahost", 8080);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);
        return HttpClientBuilder.create().setConnectionManager(cm).build();
    }

    private HttpPost createBaseRequest(String uri, String contentType) {
        HttpPost request = new HttpPost(url(uri));
        request.addHeader("content-type", contentType);
        return request;
    }

    private String url(String uri) {
        return "http://localhost:8080" + uri;
    }

    private void executeRequestAndSaveResponse(HttpGet request) {
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException("http request failed unexpectedly", e);
        }
    }
}
