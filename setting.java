import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {
        // Replace this URL with the website you want to capture data from
        String url = vlmair666.github.io;

        // Capture data from the website
        String data = captureDataFromWebsite(url);

        // Replace these variables with your actual bot token and chat ID
        String botToken = 6418191634:AAGh-_HgAIJe3TD1ui8Er64yuusfgUQqvIw;
        String chatId = "-4127769558";

        // Send the captured data to the Telegram bot
        sendToTelegramBot(botToken, chatId, data);
    }

    private static String captureDataFromWebsite(String url) throws IOException {
        // Fetch HTML content from the website using Jsoup
        Document doc = Jsoup.connect(url).get();

        // Parse HTML content to extract data (you need to adjust this part according to your specific requirements)
        // For example, here we're just extracting all text from the webpage
        return doc.text();
    }

    private static void sendToTelegramBot(String botToken, String chatId, String message) throws IOException {
        // Telegram Bot API endpoint URL
        String apiUrl = "https://api.telegram.org/bot" + botToken + "/sendMessage";

        // Create the JSON payload for the message
        String jsonPayload = "{\"chat_id\":\"" + chatId + "\",\"text\":\"" + message + "\"}";

        // Create HTTP connection
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        // Write JSON payload to output stream
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonPayload.getBytes("utf-8");
            outputStream.write(input, 0, input.length);
        }

        // Send the request and check the response status
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code : " + responseCode);
    }
}
