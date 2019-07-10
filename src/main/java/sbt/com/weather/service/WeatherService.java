package sbt.com.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import sbt.com.weather.dao.WeatherDAO;
import sbt.com.weather.model.Weather;

@Component("weatherService")
public class WeatherService {

    @Autowired
    private WeatherDAO weatherDAO;

    public Weather temperatureDetermination(String cityName) throws RestClientException {
        return weatherDAO.getCityTemperature(cityName);
    }
}
