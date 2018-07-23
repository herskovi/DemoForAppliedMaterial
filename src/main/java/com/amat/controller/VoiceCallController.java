package com.amat.controller;

import com.amat.consts.SMSConstants;
import com.amat.consts.VoiceCallConstants;
import com.amat.controller.interfaces.ISMSController;
import com.amat.controller.interfaces.IVoiceController;
import com.amat.entity.CandidateMapper;
import com.amat.model.ContactPerson;
import com.amat.model.ContactPersonList;
import com.amat.utils.SMSAndVoiceUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nexmo.client.voice.CallDirection;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.nio.file.Paths;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.JWTAuthMethod;
import com.nexmo.client.voice.Call;
import com.nexmo.client.voice.CallEvent;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;


/**
 * @author Moshe Herskovits
 * July 07, 2018
 */

@Controller
public abstract class VoiceCallController implements IVoiceController {
    private static final Logger log = Logger.getLogger(VoiceCallController.class.getName());
    String textMessage = "";
    CandidateMapper can;

    public VoiceCallController(CandidateMapper can) {
        super();
        this.can = can;
    }


    @Override
    public void createPhoneCall() {
        try {
            String url = "http://a520be3e.ngrok.io/tts.json";
            //String url = "https://applied-materials-hacakthon.appspot.com/tts.json";
            JWTAuthMethod auth = null;
            NexmoClient client = null;
            Call call = null;
            CallEvent event = null;
            String name = "";
            int i = 0;
            String str = "Hi, Your new version of Geomtery Server sucks. Please rollback your application for the previous version.\\n\" +\n" +
                    "                        \"Next time it will happened, you will be layed off. This message will destroy itself in 5 seconds. Goodbye";
            //String url = "http://a520be3e.ngrok.io/tts.json";
            if (true)
            {
                Collection<ContactPerson> list = SMSAndVoiceUtils.readPhoneList();
                for (ContactPerson contactPerson : list) {
                    try {
                        log.info("    contactPerson.getName() " + contactPerson.getName() + "    contactPerson.getPhone() " + contactPerson.getPhone());
                        auth = new JWTAuthMethod(VoiceCallConstants.APPLICATION_ID, Paths.get("src/main/resources/private.key"));
                        client = new NexmoClient(auth);
                        call = new Call(contactPerson.getPhone(), getFromNumber(), url);
                        event = client.getVoiceClient().createCall(call);
                    } catch (Exception e) {
                        log.severe("Failed to create Phone Call to  " + contactPerson.getName() + " BECAUSE " + e.getMessage());
                        log.info("Status " + event.getStatus());
                        log.info("UUID " + event.getConversationUuid());
                    }

                }
            }//if(flase)

        } catch (Exception e) {
            log.severe("Failed to create Phone Call 4 " + e.getMessage());
        }
    }

    @Override
    public String getTextToSpeachURL() {
        String textMessage = this.textMessage;
        return textMessage;
    }


    @Override
    public String getFromNumber() {
        return VoiceCallConstants.VOICE_VENDOR_PARAM_VALUE_VENDOR_ID_FOR_ISRAEL;
    }


    @Override
    public String getToNumber() {
        return can.getTelephone();
    }



}