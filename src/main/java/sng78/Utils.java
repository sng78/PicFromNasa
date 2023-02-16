package sng78;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Utils {

    //Создаем HTTP-клиент
    private static CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setSocketTimeout(30000)
                    .setRedirectsEnabled(false)
                    .build())
            .build();

    private static ObjectMapper mapper = new ObjectMapper();

    public static String getURL(String url) throws IOException {
        //Отправляем запрос и получаем ответ response в виде json
        CloseableHttpResponse response = httpClient.execute(new HttpGet(url));
        //Создаем NasaObject из response
        NasaObject nasaObject = mapper.readValue(response.getEntity().getContent(), NasaObject.class);

        return nasaObject.getUrl();
    }

}
