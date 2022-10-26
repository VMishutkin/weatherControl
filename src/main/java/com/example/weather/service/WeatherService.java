package com.example.weather.service;

import com.example.weather.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {
    private static String WEATHER_SERVICE_URL = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid=025d770aea0e15c0b0880ec8f64816a3&units=metric&lang=ru";

    Logger logger = LoggerFactory.getLogger(WeatherService.class);
    @Autowired
    private RestTemplate restTemplate;

    public Weather getWeather(String city) {
        logger.debug("Requesting weather for city : {}", city);
        Weather weather = restTemplate.exchange(WEATHER_SERVICE_URL,
                HttpMethod.GET,
                new HttpEntity<>(HttpHeaders.EMPTY),
                Weather.class,
                city
        ).getBody();
        logger.debug("The weather for {} is {}", city, weather);
        return weather;
    }

}
