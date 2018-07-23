package com.amat.consts;

public class SMSConstants {

	public static  final String SMS_VENDOR_PARAM_NAME_API_KEY = "api_key";
	public static  final String SMS_VENDOR_PARAM_VALUE_API_KEY = "971b736a";
	public static  final String SMS_VENDOR_PARAM_NAME_API_SECRET = "api_secret";
	public static  final String SMS_VENDOR_PARAM_VALUE_API_SECRET = "435ce2e93904ffc4";
	public static  final String SMS_VENDOR_PARAM_NAME_SENDER_ID = "from";
	public static  final String SMS_VENDOR_PARAM_VALUE_SENDER_ID = "Applied Material";
	public static  final String SMS_VENDOR_PARAM_VALUE_SENDER_ID_FOR_USA = "19852083212";
	public static final String NEXMO_GATEWAY_URL = "https://rest.nexmo.com/sms/json";
	public static  final String SMS_VENDOR_TO_NUMBER = "to";
	public static  final String SMS_VENDOR_TEXT_MESSAGE ="text";
	//public static  final String SMS_VENDOR_TEXT_MESSAGE_VALUE ="Hi Moshe, This is the 3rd time UVision is down in the last year. " +
	//		"If significant improvement in UVision stability is not achieved by end of 2018, your employment may be terminated.";
	public static  final String SMS_VENDOR_TEXT_MESSAGE_VALUE ="This is the 3rd time UVision is down due to your lousy development skills.";
	//public static final String NEXMO_API_SHORT_CODES_GATEWAY_URL = "https://rest.nexmo.com/sc/us/alert/json?";
/*
 * curl -X POST  https://rest.nexmo.com/sms/json \
-d api_key=971b736a \
-d api_secret=435ce2e93904ffc4 \
-d to=972524265342 \
-d from="NEXMO" \
-d text="Hello from Nexmo"
 */
	
	public static  final String SMS_VENDOR_PARAM_NAME_SENDER_TYPE = "type";
	public static  final String SMS_VENDOR_PARAM_VALUE_SENDER_TYPE = "unicode";
}