import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherClient {

    // Replace with your OpenWeatherMap API key
    static final String API_KEY = "281f3ff43accbc409d1b43e840052452";
    static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public static void main(String[] args) {
        String city = "Delhi"; // You can change this or accept user input
        String urlString = BASE_URL + city + "&appid=" + API_KEY + "&units=metric";

        try {
            // Create URL and connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse JSON
            JSONObject json = new JSONObject(response.toString());

            // Extract data
            String weather = json.getJSONArray("weather").getJSONObject(0).getString("description");
            double temp = json.getJSONObject("main").getDouble("temp");
            int humidity = json.getJSONObject("main").getInt("humidity");

            // Display structured data
            System.out.println("Weather in " + city + ":");
            System.out.println("Condition: " + weather);
            System.out.println("Temperature: " + temp + "°C");
            System.out.println("Humidity: " + humidity + "%");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
