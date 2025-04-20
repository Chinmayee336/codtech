import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ApiConsumer {

    private static final String API_KEY = "73c3cdecc27530d64253c1e7217dd82a"; // Replace with your API key
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather"; // Example: OpenWeatherMap

    public static void main(String[] args) {
        String city = "Mylavaram"; // Current location: Mylavaram
        String data = fetchDataFromApi(city);

        if (data != null) {
            displayWeatherData(data);
        } else {
            System.out.println("Failed to fetch data.");
        }
    }

    public static String fetchDataFromApi(String city) {
        String urlString = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric"; // Build the API URL
        String result = "";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Open the connection
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
            }
            connection.disconnect();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void displayWeatherData(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            System.out.println("Weather data for " + jsonObject.getString("name")); // City Name
            JSONObject main = jsonObject.getJSONObject("main"); // Main weather details
            System.out.println("Temperature: " + main.getDouble("temp") + "°C"); // Temperature in Celsius
            System.out.println("Humidity: " + main.getInt("humidity") + "%"); // Humidity
            System.out.println("Pressure: " + main.getInt("pressure") + " hPa"); // Pressure
            JSONObject wind = jsonObject.getJSONObject("wind"); // Wind details
            System.out.println("Wind speed: " + wind.getDouble("speed") + " m/s"); // Wind speed
            System.out.println("Wind direction: " + wind.getInt("deg") + "°"); // Wind direction
            JSONObject sys = jsonObject.getJSONObject("sys");
            System.out.println("Sunrise: " + sys.getLong("sunrise") + " seconds since epoch"); // Sunrise
            System.out.println("Sunset: " + sys.getLong("sunset") + " seconds since epoch"); // Sunset

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}