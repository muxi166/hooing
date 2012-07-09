package com.ocb.ocbhttp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ocb.pojo.Restaurant;

public class JsonUtil {
	public JsonUtil() {
	};
	
	public ArrayList<Restaurant> jsonToRestaurantList(String result) {
		ArrayList<Restaurant> rList=new ArrayList<Restaurant>();
		JSONObject jsonObject=null;
		JSONArray jsonArray=null;
		try {
			
			jsonObject = new JSONObject(result)
					.getJSONObject("restaurants");
            jsonArray= jsonObject.getJSONArray("restaurant");
    		for (int i = 0; i < jsonArray.length(); i++) {
    			JSONObject jsonObject2 = (JSONObject) jsonArray.opt(i);
    			Restaurant rs = new Restaurant();
    			rs.setRestanrantAddr(jsonObject2.getString("restanrantAddr"));
    			rs.setRestaurantID(Integer.parseInt(jsonObject2.getString("restanrantId")));
    			rs.setRestaurantIntro(jsonObject2.getString("restanrantIntro"));
    			rs.setRestaurantLogoPhoto(jsonObject2.getString("restanrantLogoPhoto"));
    			rs.setRestaurantLat(Double.parseDouble(jsonObject2.getString("restanrantLat")));
    			rs.setRestaurantName(jsonObject2.getString("restanrantName"));

    			rs.setRestaurantLng(Double.parseDouble(jsonObject2.getString("restanrantLng")));
    			rs.setFavorNote(jsonObject2.getString("favorNote"));
    			rs.setRestaurantPhone(jsonObject2.getString("restanrantPhone"));
    			rs.setRestaurantPhotos(jsonObject2.getString("restanrantPhotos"));
    			
    		}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rList;
	}
}
