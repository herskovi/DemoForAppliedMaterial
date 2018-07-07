package com.amat.controller;

import com.amat.consts.SMSConstants;
import com.amat.controller.interfaces.ISMSController;
import com.amat.entity.CandidateMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.logging.Logger;


/**
 * @author admin
 * May 16, 2017
 */

@Controller
public abstract class VoiceCallController implements ISMSController
{
	private static final Logger log = Logger.getLogger(VoiceCallController.class.getName());
	CandidateMapper can = null;
	String textMessage = "";
	HttpClient client = new DefaultHttpClient();
	HttpPost post = null;
	HttpResponse response = null;


	public abstract  List<NameValuePair> populateSMSParameters();
	public abstract String getSMSGatewayURL();




	public VoiceCallController(CandidateMapper can, String textMessage)
	{
		super();
		this.can = can;
		this.textMessage = textMessage;
	}



	@Override
	public void sendSMS()  
	{
		post = new HttpPost( getSMSGatewayURL());
		HttpResponse response = null;
		List<NameValuePair> nvps = populateSMSParameters();
		try 
		{

			UrlEncodedFormEntity urEntity =  new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
			urEntity.setContentEncoding(HTTP.UTF_8);
			post.setEntity(urEntity);
			log.info("UTF Message" + urEntity.getContent().toString());
			response = client.execute(post);
			log.info(" SMSFlowController sendSMS Respose Code from " + response == null ? " " : response.toString());

		} catch (Exception e) 
		{
			log.severe("Failed to Send SMS " + e.getMessage());

		}
	}

	@Override
	public String getTextMessage() 
	{

		String textMessage = this.textMessage; 
		return textMessage;
	}


	@Override
	public String getFromNumber() 
	{
		return SMSConstants.SMS_VENDOR_PARAM_VALUE_SENDER_ID;
	}


	@Override
	public String getToNumber() 
	{
		String telNoForSMS =  can.getTelephone();
		//FIXME - REmove this code from here and move it to FE Validation.
		//        This is only for Israli mobile that were entered with out prefix of 972
		if (telNoForSMS != null && telNoForSMS.startsWith("0"))
		{
			telNoForSMS = "972"  + telNoForSMS.substring(1);
			log.severe("getToNumber in SMSFLOWCOntroller - This code should not be reachable !! ");
		}
		log.severe(" SMS Number  " + telNoForSMS);	

		return telNoForSMS;

	}


}
