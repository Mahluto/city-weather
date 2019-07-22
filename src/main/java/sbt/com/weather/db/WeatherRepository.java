package sbt.com.weather.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sbt.com.weather.db.entities.WeatherHistory;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherHistory, Long> {

    @Query("SELECT wh FROM WeatherHistory wh WHERE wh.city = ?1")
    WeatherHistory findByCityName(String city);

}
