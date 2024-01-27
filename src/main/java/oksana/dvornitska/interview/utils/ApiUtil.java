package oksana.dvornitska.interview.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ApiUtil {

    private final OkHttpClient client = new OkHttpClient();

    public String getRequest(String apiUrl) throws IOException {
        Request request = new Request.Builder().url(apiUrl).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    return response.body().string();
                }
            }
            log.error("Unexpected response: " + response.code());
            throw new IOException("Unexpected response: " + response.code());
        }
    }
}
