package com.example.parsexmlarsyntask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLParser {

    public String getXmlFromUrl(String urlString) {
        StringBuilder xml = new StringBuilder();
        try {
            // Mở kết nối URL
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // timeout 5 giây
            connection.setReadTimeout(5000);
            connection.connect();

            // Đọc dữ liệu trả về
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                xml.append(line);
            }

            reader.close();
            inputStream.close();
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml.toString();
    }
}
