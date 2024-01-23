package oksana.dvornitska.interview.utils;

import com.google.gson.Gson;
import okhttp3.*;
import oksana.dvornitska.interview.dtos.HitDto;
import oksana.dvornitska.interview.dtos.ResponseDto;
import oksana.dvornitska.interview.dtos.ResultDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApiUtil {

    private final OkHttpClient client = new OkHttpClient();

    public String getRequest(String apiUrl) throws IOException {
        Request request = new Request.Builder().url(apiUrl).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected response: " + response.code());
            }
        }
    }
}
