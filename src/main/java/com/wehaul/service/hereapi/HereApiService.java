package com.wehaul.service.hereapi;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wehaul.dto.hereapi.LocationDetails;

@Service
public class HereApiService {

	private static final Logger log = LoggerFactory.getLogger(HereApiService.class);

	@Value("${hereapi.appid}")
	private String APP_ID;

	@Value("${hereapi.appcode}")
	private String APP_CODE;

	@Value("${hereapi.geocoderbaseurl}")
	private String GEOCODER_BASE_URL;

	@Autowired
	private RestTemplate restTemplate;

	public LocationDetails getdetailsByLocationId(String locid) throws Exception {
		String GEOCODER_URL = GEOCODER_BASE_URL + "?app_id=" + APP_ID + "&app_code=" + APP_CODE + "&locationid="
				+ locid;
		LocationDetails locDetails = new LocationDetails();
		ResponseEntity<String> jsonInString = restTemplate.exchange(GEOCODER_URL, HttpMethod.GET, null, String.class);
		JSONObject jsonObj = new JSONObject(jsonInString.getBody());
		JSONObject viewObj = (JSONObject) jsonObj.getJSONObject("Response").getJSONArray("View").get(0);
		JSONObject resultObj = (JSONObject) viewObj.getJSONArray("Result").get(0);
		JSONObject dispLocObj = resultObj.getJSONObject("Location").getJSONObject("DisplayPosition");
		JSONObject addObj = resultObj.getJSONObject("Location").getJSONObject("Address");
		locDetails.setLocid(locid);
		locDetails.setLat(dispLocObj.getDouble("Latitude"));
		locDetails.setLon(dispLocObj.getDouble("Longitude"));
		locDetails.setLable(addObj.getString("Label"));
		if (!addObj.isNull("Country"))
			locDetails.setCountry(addObj.getString("Country"));
		if (!addObj.isNull("State"))
			locDetails.setState(addObj.getString("State"));
		if (!addObj.isNull("County"))
			locDetails.setCounty(addObj.getString("County"));
		if (!addObj.isNull("City"))
			locDetails.setCity(addObj.getString("City"));
		if (!addObj.isNull("PostalCode"))
			locDetails.setPostcode(addObj.getString("PostalCode"));
		log.debug("Location : {} ", locDetails);
		return locDetails;
	}
}