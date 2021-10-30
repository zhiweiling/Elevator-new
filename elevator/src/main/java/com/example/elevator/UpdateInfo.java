package com.example.elevator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

@RestController
public class UpdateInfo {
    private static final List DeviceList = new ArrayList<>(Arrays.asList("750679070"));
    private static final String ApiKey = "1=avChcZH9KCg9ffuR=tLmgH7SE=";
    private static final String temperature = "temperature";
    private static final String humidity = "humidity";

    private static HttpURLConnection connection = null;
    static InputStream in = null;
    static BufferedReader br = null;
    static String result = null;// 返回结果字符串
    static String jo;

    @GetMapping("/update/{id}")
    public Object saydata(@PathVariable(value = "id") Integer id){
        String urlT = "http://api.heclouds.com/devices/" + DeviceList.get(id - 1) +
                "/datapoints?datastream_id=" + temperature;
        String urlH = "http://api.heclouds.com/devices/" + DeviceList.get(id - 1) +
                "/datapoints?datastream_id=" + humidity;
        String res1 = get(urlT);
        String res2 = get(urlH);
        return new ArrayList<>(Arrays.asList(res1, res2));
    }

    static String get(String url) {
        try {
            URL Url = new URL(url);

            connection = (HttpURLConnection) Url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("api-key", ApiKey);
            in = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            result = response.toString();
            JSONObject j1 = JSONObject.parseObject(result);
            j1 = JSONObject.parseObject(j1.get("data").toString());
            jo = j1.get("datastreams").toString();
            jo = jo.substring(1, jo.length() - 1);
            j1 = JSONObject.parseObject(jo);
            jo = j1.get("datapoints").toString();
            jo = jo.substring(1, jo.length() - 1);
            j1 = JSONObject.parseObject(jo);
            jo = j1.get("value").toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jo;
    }

}
