package com.amat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.amat.consts.SMSConstants;
import com.amat.entity.CandidateMapper;


/**
 * @author admin
 * May 21, 2014
 */
public class NexmoSmsController extends SMSController
{
	
	
	private static final Logger log = Logger.getLogger(NexmoSmsController.class.getName());


	/**
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  May 12, 2017
	 * @Description:  Constructor
	 */
	public NexmoSmsController(CandidateMapper can, String textMessage) 
	{
		super(can, textMessage);
	}

//	/**
//	 * @throws Exception 
//	 * @Author: 	  Moshe Herskovits
//	 * @Date: 		  May 12, 2017
//	 * @Description:  Execute flow
//	 */
//	@Override
//	public void execute()  
//	{
//		sendSMS();	
//		
//	}


	


	/**
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Populate Parameters before sending SMS
	 */
	public   List<NameValuePair> populateSMSParameters() {
		List <NameValuePair> nvps = new ArrayList <NameValuePair>(5);
		nvps.add(new BasicNameValuePair(SMSConstants.SMS_VENDOR_PARAM_NAME_API_KEY, SMSConstants.SMS_VENDOR_PARAM_VALUE_API_KEY));
		nvps.add(new BasicNameValuePair(SMSConstants.SMS_VENDOR_PARAM_NAME_API_SECRET, SMSConstants.SMS_VENDOR_PARAM_VALUE_API_SECRET));
		nvps.add(new BasicNameValuePair(SMSConstants.SMS_VENDOR_PARAM_NAME_SENDER_ID, getFromNumber()));
		nvps.add(new BasicNameValuePair(SMSConstants.SMS_VENDOR_TO_NUMBER, getToNumber()));
		nvps.add(new BasicNameValuePair(SMSConstants.SMS_VENDOR_TEXT_MESSAGE, getTextMessage()));
//		if (!(FormatterUtils.isPureAscii(getTextMessage()))) //If text is not Pure ASCII, send Type 
//		{
//			nvps.add(new BasicNameValuePair(SMSConstants.SMS_VENDOR_PARAM_NAME_SENDER_TYPE, SMSConstants.SMS_VENDOR_PARAM_VALUE_SENDER_TYPE));
//		}
		log.severe("textMessage " + getTextMessage());
		return nvps;
	}

	@Override
	public String getFromNumber() {
		return SMSConstants.SMS_VENDOR_PARAM_VALUE_SENDER_ID;
	}


	/**
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  Jun 12, 2014
	 * @Description:  Get SMS Gateway URL
	 */
	public String getSMSGatewayURL() 
	{
		return SMSConstants.NEXMO_GATEWAY_URL;
	}



	

	
	



}
