package com.amat.controller.interfaces;

import org.apache.http.NameValuePair;

import java.util.List;

public interface IVoiceController
{
	public void createPhoneCall() throws Exception;
	public String getTextToSpeachURL();
	public String getToNumber();
	public String getFromNumber();


}
