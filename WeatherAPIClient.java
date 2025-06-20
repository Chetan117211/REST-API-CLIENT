import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherAPIClient {
    public static void main(String[] args) {
        try {
            String apiKey = "YOUR_API_KEY";      // ← Replace this with your real key
            String city = "Tirupati,IN";         // City + country code
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" 
                + city + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) response.append(inputLine);
            in.close();

            JSONObject obj = new JSONObject(response.toString());
            String cityName = obj.getString("name");
            double temperature = obj.getJSONObject("main").getDouble("temp");

            System.out.println("City: " + cityName);
            System.out.println("Temperature: " + temperature + " °C");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
