package com.amat.controller;

import com.amat.consts.SMSConstants;
import com.amat.consts.VoiceCallConstants;
import com.amat.entity.CandidateMapper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * @author Moshe Herskovits
 * July 09, 2018
 */
public class NexmoVoiceCallController extends VoiceCallController
{


	private static final Logger log = Logger.getLogger(NexmoVoiceCallController.class.getName());


	/**
	 * @Author: 	  Moshe Herskovits
	 * @Date: 		  July 06, 2018
	 * @Description:  Constructor
	 */
	public NexmoVoiceCallController(CandidateMapper can)
	{
		super(can);
	}



	@Override
	public String getFromNumber() {
		return VoiceCallConstants.VOICE_VENDOR_PARAM_VALUE_VENDOR_ID_FOR_ISRAEL;
	}






	

	
	



}
