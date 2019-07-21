package sbt.com.weather.servletapi.servlets;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import sbt.com.weather.dto.WeatherDTO;
import sbt.com.weather.service.WeatherService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class WeatherServlet extends HttpServlet {

    @Autowired
    private WeatherService weatherService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        WeatherDTO weatherDTO = new WeatherDTO();
        String cityName = request.getPathInfo().split("/")[1];

        try {
            weatherDTO.setTemperature(weatherService.temperatureDetermination(cityName).getTemperature());
        } catch (HttpClientErrorException ex) {
            response.setStatus(ex.getStatusCode().value());
            return;
        }

        JSONObject jsonObject = new JSONObject(weatherDTO);
        String responseJson = jsonObject.toString();

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseJson);
        out.flush();

    }
}
