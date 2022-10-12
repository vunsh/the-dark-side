package com.nighthawk.spring_portfolio.mvc.weather;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // annotation to create a RESTful web services
@RequestMapping("/api/weather")  //prefix of API
public class WeatherApiController {
    private JSONObject body; //last run result
    private JSONArray data;
    String last_run = null; //last run day of month
    private String temp;

    @GetMapping("/sandiego")   //added to end of prefix as endpoint
    public String getSD(String temp, Model model) {
        //calls API once a day, sets body and status properties
        String today = new Date().toString().substring(0,10); 
        if (last_run == null || !today.equals(last_run))
        {
            try {  //APIs can fail (ie Internet or Service down)

                //RapidAPI header
                HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.weatherbit.io/v2.0/current?lat=33.0167&lon=-117.1115&key=17270180ead94b27a3e874f0cdd513ec"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                //JSONParser extracts text body and parses to JSONObject
                this.body = (JSONObject) new JSONParser().parse(response.body());
                this.data = (JSONArray) this.body.get("data");
                this.temp = (String) ((JSONObject) this.data.get(0)).get("temp").toString();
                model.addAttribute("temp", this.temp);
                this.last_run = today;
                //get the temp from body
            }
            catch (Exception e) {  //capture failure info
                HashMap<String, String> status = new HashMap<>();
                status.put("status", "RapidApi failure: " + e);
                //Setup object for error
                this.last_run = null;
            }
        }

        return "weather";
    }
}
