package sbt.com.weather.db.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class WeatherHistory {

    public WeatherHistory() {
        super();
    }

    public WeatherHistory(String city, String temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    @Id
    @GeneratedValue
    private Long Id;

    private String city;

    private String temperature;

}
