package com.hnotify.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hnotify.dao.domain.LocationVO;

public class Utilities {
	Logger slf4jLogger = LoggerFactory.getLogger("cms");

	private Utilities() {
	}

	private static Utilities classObj = null;

	public static Utilities getInstance() {
		if (classObj == null) {
			classObj = new Utilities();
		}
		return classObj;
	}

	 public static String generateRandomNumber(int length){
	        String randomNumber="";
	        randomNumber = RandomStringUtils.randomNumeric(length);
	        return randomNumber;
	    }

	 public int calcDistance(double latA, double longA, double latB, double longB) {

			double theDistance = (Math.sin(Math.toRadians(latA))
					* Math.sin(Math.toRadians(latB)) + Math.cos(Math
					.toRadians(latA))
					* Math.cos(Math.toRadians(latB))
					* Math.cos(Math.toRadians(longA - longB)));

			return new Double(
					(Math.toDegrees(Math.acos(theDistance))) * 69.09 * 1.609344)
					.intValue();
		}
	 
	public LocationVO getGeoLocation(String gOOGLE_GEO_API, String latitude,
			String longitude) {
		gOOGLE_GEO_API = gOOGLE_GEO_API.replace("$LATITUDE$", latitude)
				.replace("$LONGITUDE$", longitude);
		LocationVO locationVO = new LocationVO();
		try {
			String response = sendGet(gOOGLE_GEO_API);
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
			if ((jsonObject.get("status")).getAsString().equalsIgnoreCase("OK")) {
				JsonArray resultsArry = (JsonArray) jsonObject.get("results");
				// System.out.println("arr count::"+resultsArry.size());
				JsonObject obj1 = (JsonObject) resultsArry.get(0);
				String address = obj1.get("formatted_address").getAsString();
				// parse the address
				String[] locs = address.split(",");
				int index = locs.length;
				String country = locs[index - 1].trim();
				String state = locs[index - 2].trim();
				state = getStateFromText(state.trim());
				String city = locs[index - 3].trim();
				// city = locs[index-4].trim();
				// parse address ends
				locationVO.setLatitude(latitude);
				locationVO.setLongitude(longitude);
				locationVO.setCountry(country);
				locationVO.setState(state);
				locationVO.setCity(city);
				return locationVO;
			} else {
				return locationVO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return locationVO;
	}
	

	public String getStateFromText(String state) {
		String[] strs = state.split(" ");
		try {
			long pin = Long.parseLong(strs[strs.length - 1]);
			String ss = "";
			for (int i = 0; i < strs.length - 1; i++) {
				ss += strs[i] + " ";
			}
			return ss.trim();
		} catch (Exception e) {
			return state.trim();
		}
	}

	
	private String sendGet(String gOOGLE_GEO_API) throws Exception {

		String USER_AGENT = "Mozilla/5.0";

		URL obj = new URL(gOOGLE_GEO_API);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out
				.println("\nSending 'GET' request to URL : " + gOOGLE_GEO_API);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		// System.out.println(response.toString());
		return response.toString();
	}


}
