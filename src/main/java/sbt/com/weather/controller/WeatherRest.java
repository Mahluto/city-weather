package sbt.com.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import sbt.com.weather.dto.WeatherDTO;
import sbt.com.weather.service.WeatherService;


@RestController
@RequestMapping("weather")
public class WeatherRest {

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/city/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherDTO> currentCityWeather(@PathVariable String name) {

        WeatherDTO weatherDTO = new WeatherDTO();
        try {
            weatherDTO.setTemperature(weatherService.temperatureDetermination(name).getTemperature());
            return new ResponseEntity<>(weatherDTO, HttpStatus.OK);
        } catch (RestClientException ex) {
            return new ResponseEntity<>(weatherDTO, HttpStatus.NOT_FOUND);
        }
    }
}
