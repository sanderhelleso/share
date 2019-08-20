package com.semanta.share.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import com.semanta.share.config.Config;

public class LookupIP {

    public static String lookup(String ip) {
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            return "Norway, NO"; // local ip
        }

        String apiKey = "?api-key=" + Config.IPDATA_API_KEY;
        String urlString = "https://api.ipdata.co/" + ip + apiKey;
        String res = null;

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            res = readStreamRes(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return extractCountryAndCode(res);
    }

    private static String readStreamRes(InputStream is) {
        String res = "";
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "utf8"))) {
            while ((line = bufferedReader.readLine()) != null) {
                res += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    private static String extractCountryAndCode(String res) {
        ArrayList<String> results = new ArrayList<String>();
        String[] lines = res.split("\n");

        // As data comes in a a stingified JSON body,
        // we need to split and parse only at the
        // specific keys we care about

        String countryNameDelimeter = "\"country_name\": ";
        String countryCodeDelimeter = "\"country_code\": ";
        String delimeter;

        for (String line : lines) {
            if (results.size() == 2) {
                break;
            } else if (results.size() == 0) {
                delimeter = countryNameDelimeter;
            } else {
                delimeter = countryCodeDelimeter;
            }

            String[] countryInfo = line.split(delimeter);

            if (countryInfo.length > 1) {
                results.add(clean(countryInfo[1]));
            }
        }

        return results.get(0) + ", " + results.get(1);
    }

    private static String clean(String s) {
        return s.split("\"")[1];
    }
}