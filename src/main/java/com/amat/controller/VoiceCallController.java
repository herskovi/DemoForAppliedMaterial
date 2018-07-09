package com.amat.controller;

import com.amat.consts.SMSConstants;
import com.amat.consts.VoiceCallConstants;
import com.amat.controller.interfaces.ISMSController;
import com.amat.controller.interfaces.IVoiceController;
import com.amat.entity.CandidateMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Controller;
import java.nio.file.Paths;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.JWTAuthMethod;
import com.nexmo.client.voice.Call;
import com.nexmo.client.voice.CallEvent;

import java.util.List;
import java.util.logging.Logger;


/**
 * @author Moshe Herskovits
 * July 07, 2018
 */

@Controller
public abstract class VoiceCallController implements IVoiceController
{
    private static final Logger log = Logger.getLogger(VoiceCallController.class.getName());
    String textMessage = "";
    CandidateMapper can;

	public VoiceCallController(CandidateMapper can)
	{
		super();
		this.can = can;
	}


	@Override
	public void createPhoneCall()
	{

		try 
		{

            JWTAuthMethod auth = new JWTAuthMethod(VoiceCallConstants.APPLICATION_ID, Paths.get("src/main/resources/private.key"));
            NexmoClient client = new NexmoClient(auth);
            Call call = new Call(getToNumber(),getFromNumber(),
                    "http://applied-materials-hacakthon.appspot.com/tts.json");
            CallEvent event = client.getVoiceClient().createCall(call);
			log.info(" VoiceCallController createPhoneCall Respose Code from " + event == null ? " " : event.getStatus().name());

		} catch (Exception e) 
		{
			log.severe("Failed to create Phone Call  " + e.getMessage());

		}
	}

	@Override
	public String getTextToSpeachURL()
	{
		String textMessage = this.textMessage; 
		return textMessage;
	}


	@Override
	public String getFromNumber() 
	{
		return VoiceCallConstants.VOICE_VENDOR_PARAM_VALUE_VENDOR_ID_FOR_ISRAEL;
	}


	@Override
	public String getToNumber() 
	{
		return can.getTelephone();
	}


}
