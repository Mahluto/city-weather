package sbt.com.weather.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sbt.com.weather.db.entities.WeatherHistory;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherHistory, Long> {
}
