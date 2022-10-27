package com.browserstack;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetSessionLogs {
    public static String session_id;
    public static String build_id;
    public static String username;
    public static String access_key;

    GetSessionLogs(String session_id, String build_id, String username, String access_key) {
        this.session_id = session_id;
        this.build_id = build_id;
        this.username = username;
        this.access_key = access_key;
    }

    public static void download() throws IOException {

//        https://automate.browserstack.com/builds/56a097a0effd56133ff9b71b090820df01789ab9/sessions/06c33b31b88de9fcf95931bdeb9456552515710c/logs
        URL url = new URL("https://automate.browserstack.com/builds/"+build_id+"/sessions/"+session_id+"/logs");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        byte[] message = (username+":"+access_key).getBytes("UTF-8");
        String basicAuth = DatatypeConverter.printBase64Binary(message);
        httpConn.setRequestProperty("Authorization", "Basic " + basicAuth);

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";


        System.out.println(response);




    }

    public static List<String> extractUrls(String text)
    {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find())
        {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        return containedUrls;
    }







}