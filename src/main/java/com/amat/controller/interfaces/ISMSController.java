package com.amat.controller.interfaces;

import java.util.List;

import org.apache.http.NameValuePair;

public interface ISMSController 
{
	public void sendSMS() throws Exception;
	public String getSMSGatewayURL();
	public String getTextMessage();
	public String getToNumber();
	public String getFromNumber();
	public List<NameValuePair> populateSMSParameters();

}
