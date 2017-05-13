package com.amat.controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;

import com.amat.controller.interfaces.IController;

@Controller
final class LightController implements IController
{

	public int numberOfTimesToTurnOn = 5;
	public final static String PHILIPHS_HUE_URL = "http://192.168.1.102/"
			+ "api/rdKNpvCg0ok63gqFYGRjSNI8vF8kKz5jLILXxEJZ/lights/3/state";
	
	
	
	public LightController(int numberOfTimesToTurnOn) 
	{
		super();
		this.numberOfTimesToTurnOn = numberOfTimesToTurnOn;
	}
	
	public LightController() 
	{
		super();
	}
	


	private void turnOntheLight() 
	{
		HttpResponse response = null;
		try {
			invokeRestAPIToTurnOnTheLight();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	
	private void invokeRestAPIToTurnOnTheLight()
			throws UnsupportedEncodingException, IOException, ClientProtocolException 
	{
		DefaultHttpClient httpClient = null;
		HttpPut putRequest = new HttpPut(PHILIPHS_HUE_URL);
		StringEntity turnOff = new StringEntity("{\"on\":false}");
		turnOff.setContentType("application/json");
		StringEntity turnOn = new StringEntity("{\"on\":true , \"sat\":254, \"bri\":254,\"hue\":10000 }");
		turnOn.setContentType("application/json");

		httpClient = turnOnTheLight(httpClient, putRequest, turnOff, turnOn);
		httpClient.getConnectionManager().shutdown();
	}

	/**
	 * 
	 * @param httpClient
	 * @param putRequest
	 * @param turnOff
	 * @param turnOn
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	
	private DefaultHttpClient turnOnTheLight(DefaultHttpClient httpClient, HttpPut putRequest, StringEntity turnOff, StringEntity turnOn) throws IOException, ClientProtocolException 
	{
		for (int i = 0; i < numberOfTimesToTurnOn; i++) 
		{
//			if (i== 0)
//			{
//			  SoundUtils sound = new SoundUtils();
//			  sound.playSound();
//			}
			httpClient = new DefaultHttpClient();
			putRequest.setEntity(turnOn);
			httpClient.execute(putRequest);
			sleep(3000);
			httpClient = new DefaultHttpClient();
			putRequest.setEntity(turnOff);
			httpClient.execute(putRequest);
			sleep(3000);
		}
		return httpClient;
	}

	private void sleep(int sleepInMilisecond) {
		try {
			Thread.sleep(sleepInMilisecond);
		} catch (InterruptedException e) {


		}
	}


	public void process() throws Exception
	{
		turnOntheLight();		
	}




}