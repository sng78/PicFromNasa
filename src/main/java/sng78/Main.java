package sng78;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static final String URI = "https://api.nasa.gov/planetary/apod?api_key=" +
            "KVhqJ7nkVobbbRPzpbIotmc2Za4dO7E5ndRRlmpG";
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        //Создаем HTTP-клиент
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        //Отправляем запрос и получаем ответ response в виде json
        CloseableHttpResponse response = httpClient.execute(new HttpGet(URI));

        //Создаем NasaObject из response
        NasaObject nasaObject = mapper.readValue(response.getEntity().getContent(), NasaObject.class);

        //Отправляем запрос и получаем ответ responsePicture в виде ссылки на фотографию
        CloseableHttpResponse responsePicture = httpClient.execute(new HttpGet(nasaObject.getUrl()));

        //Получаем имя файла из поля url объекта nasaObject
        String[] parts = nasaObject.getUrl().split("/");
        String fileName = parts[parts.length - 1];

        //Проверяем что наш ответ не null, и если все ок, то сохраняем картинку в файл
        HttpEntity entity = responsePicture.getEntity();
        if (entity != null) {
            FileOutputStream fos = new FileOutputStream(fileName);
            entity.writeTo(fos);
            fos.close();
        } else {
            System.out.println("Ошибка! Объект не найден!");
        }

    }
}
