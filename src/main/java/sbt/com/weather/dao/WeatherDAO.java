package sbt.com.weather.dao;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import sbt.com.weather.model.Weather;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

@Component("weatherDAO")
public class WeatherDAO {

    private String APIKEY = "f24f40b1c24505685fce3b8acd0fcffc";
    private HttpHeaders headers = new HttpHeaders();

    public URI buildUrl(String cityName) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("api.openweathermap.org")
                .path("/data/2.5/weather")
                .queryParam("q", cityName)
                .queryParam("appid", APIKEY).build();

        return uriComponents.toUri();
    }

    public String responseToOpenWeatherMap(String cityName) throws RestClientException{
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(buildUrl(cityName), HttpMethod.GET, entity, String.class);


        return response.getBody();
    }

    public Weather getCityTemperature(String cityName){
        Weather weather = new Weather();
        JSONObject json = new JSONObject(responseToOpenWeatherMap(cityName));
        Float temp = json.optJSONObject("main").optFloat("temp");
        weather.setTemperature(Integer.toString(temp.intValue() - 273));
        return weather;
    }
}
