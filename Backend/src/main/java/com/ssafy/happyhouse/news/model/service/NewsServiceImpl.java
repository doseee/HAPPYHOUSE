package com.ssafy.happyhouse.news.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {
	String clientId = "akrwocKbrRSeXp1WeVxt";// Client ID
	String clientSecret = "Bcoog3Yr9a";// Client Secret
	
	@Override
	public String newsList() {
		Random random = new Random();
		int start = random.nextInt(150) + 20;
		try {
			String text = URLEncoder.encode("부동산", "UTF-8"); // 검색어";
			String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text
					+ "&display=10&start=" + start + "&sort=sim"; //JSON
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200)
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			else 
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null)
				response.append(inputLine + "\n");
			
			br.close();
			return response.toString();
		} catch (Exception e) {
			return null;
		}
	}
}