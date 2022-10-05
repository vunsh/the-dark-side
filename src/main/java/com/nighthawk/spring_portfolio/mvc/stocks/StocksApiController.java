package com.nighthawk.spring_portfolio.mvc.stocks;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // annotation to create a RESTful web services
@RequestMapping("/api/stocks")  //prefix of API
public class StocksApiController {
    private JSONObject body; //last run result
    private HttpStatus status; //last run status
    String last_run = null; //last run day of month

    @GetMapping("/tata")   //added to end of prefix as endpoint
    public ResponseEntity<JSONObject> getTATA() {

        //calls API once a day, sets body and status properties
        String today = new Date().toString().substring(0,10); 
        if (last_run == null || !today.equals(last_run))
        {
            try {  //APIs can fail (ie Internet or Service down)

                //RapidAPI header
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://latest-stock-price.p.rapidapi.com/price?Indices=NIFTY%20AUTO&Identifier=TATAMOTORSEQN"))
                    .header("X-RapidAPI-Key", "8a72a745admsh4f6260fd91442a9p17d011jsn6dc5cd0e2605")
                    .header("X-RapidAPI-Host", "latest-stock-price.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

                //RapidAPI request and response
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                String body = response.body();
                System.out.println(body);

                //JSONParser extracts text body and parses to JSONObject
                JSONArray arr =  (JSONArray) new JSONParser().parse(body);
                this.body = (JSONObject) arr.get(0);
                this.status = HttpStatus.OK;  //200 success
                this.last_run = today;
            }
            catch (Exception e) {  //capture failure info
                HashMap<String, String> status = new HashMap<>();
                status.put("status", "RapidApi failure: " + e);

                //Setup object for error
                this.body = new JSONObject(status);
                this.status = HttpStatus.INTERNAL_SERVER_ERROR; //500 error
                this.last_run = null;
            }
        }

        //return JSONObject in RESTful style
        return new ResponseEntity<>(body, status);
    }
}