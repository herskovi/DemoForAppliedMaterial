package com.amat.controller; 


import java.util.*;

import com.amat.model.ContactPerson;
import com.amat.utils.SMSAndVoiceUtils;
import org.seleniumhq.jetty7.util.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amat.consts.CandidateRestURIConstants;
import com.amat.consts.SMSConstants;
import com.amat.entity.CandidateMapper;
//import com.amat.main.AnagramMain;
import com.amat.model.Candidate;
import com.amat.services.CandidateService;


/**
 * Handles requests for the Employee service.
 */

@Controller
public class CandidateController 
{
	@Autowired
	private CandidateService candidateService;

	private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);
	//Map to store employees, ideally we should use database
	Map<Integer, CandidateMapper> canData = new HashMap<Integer, CandidateMapper>();

	@RequestMapping(value = CandidateRestURIConstants.DUMMY_CANDIDATE, method = RequestMethod.GET)
	public @ResponseBody CandidateMapper getDummyEmployee() {
		logger.info("Start getDummyEmployee");
		CandidateMapper can = new CandidateMapper();
		can.setId(9999);
		can.setFullName("Dummy");
		can.setCreatedDate(new Date());
		canData.put(9999, can);
		return can;
	}



	@RequestMapping(value = CandidateRestURIConstants.GET_CANDIDATE, method = RequestMethod.GET)
	public @ResponseBody CandidateMapper getCandidate(@PathVariable("id") int candidateId) 
	{
		logger.info("Start getCandidate. ID="+candidateId);
		return canData.get(candidateId);
	}

	@RequestMapping(value = CandidateRestURIConstants.GET_ALL_CANDIDATE, method = RequestMethod.GET)
	public @ResponseBody List<Candidate> getAllCandidates() 
	{
		logger.info("Start getAllCandidates.");
		List<Candidate> candidateList = candidateService.findAll();
		for(Candidate can : candidateList){
			logger.info("Candidate name is: " + can.getFullName());
			logger.info("Candidate Email is: " + can.getEmail());
		}
		
//		List<CandidateMapper> candidates = new ArrayList<CandidateMapper>();
//		Set<Integer> candidateIdKeys = canData.keySet();
//		for(Integer i : candidateIdKeys){
//			candidates.add(canData.get(i));
//		}
		return candidateList;
	}

//	@RequestMapping(value = CandidateRestURIConstants.CREATE_CANDIDATE, method = RequestMethod.POST)
//	public @ResponseBody CandidateMapper createCandidates(@RequestBody CandidateMapper can) {
//		logger.info("Start createCandidates.");
//
//		logger.info("Start anagram ID=");
//		//String args[0] = anagramText;
//		String[] array = can.getComments().split(" ");
////		try {
////			AnagramMain.main(array);
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//
//		try {
//			saveToDatabase(can);
//			//turnOnTheLight();
//			//turnOnTheSound();
//			sendSMS(can);
//		} catch (Exception e) {
//			logger.error("TurnOnTheLight Failed!!!!!");
//
//		}
//		return can;
//	}

    @RequestMapping(value = CandidateRestURIConstants.ROLLBACK, method = RequestMethod.GET)
    public @ResponseBody void rollback()
    {
        CandidateMapper candidateMapper = new CandidateMapper();
        sendSMS(candidateMapper);
        makePhoneCall();
        turnOnTheLight();
    }

    @RequestMapping(value = CandidateRestURIConstants.SEND_SMS, method = RequestMethod.GET)
    public @ResponseBody void sendSMS()
    {
        logger.info("Start deploying new software version.");
        String defaultPhoneNumber = "972524265342";
        CandidateMapper candidateMapper = new CandidateMapper();
        candidateMapper.setTelephone(defaultPhoneNumber);
        sendSMS(candidateMapper);
        turnOnTheLight();
        logger.info("Start deploying new software version.");
    }

    @RequestMapping(value = CandidateRestURIConstants.TURN_ON_THE_LIGHT, method = RequestMethod.GET)
    public @ResponseBody void turnLightsOn()
    {
        turnOnTheLight();
    }

    @RequestMapping(value = CandidateRestURIConstants.MAKE_PHONE_CALL, method = RequestMethod.GET)
    public @ResponseBody void makePhoneCall()
    {
        String phoneNumber = "";
        String defaultPhoneNumber = "972524265342";
        CandidateMapper candidateMapper = new CandidateMapper();
        if (phoneNumber == null || phoneNumber.length() == 0) {
            candidateMapper.setTelephone(defaultPhoneNumber);
        }else{
            candidateMapper.setTelephone(phoneNumber);
        }
        try {
            placeVoiceCallToUser(candidateMapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

//    @RequestMapping(value = CandidateRestURIConstants.CREATE_CANDIDATE, method = RequestMethod.POST)
//    public @ResponseBody void sendSMS(@RequestBody String phoneNumber)
//    {
//        CandidateMapper candidateMapper = new CandidateMapper();
//        candidateMapper.setTelephone(phoneNumber);
//        sendSMS(candidateMapper);
//    }

    @RequestMapping(value = CandidateRestURIConstants.DEPLOY, method = RequestMethod.POST)
	public void deploy() {
		logger.info("Start deploying new software version.");

		try {

			//turnOnTheLight();
			turnOnTheSound();

		} catch (Exception e) {
			logger.error("turnOnTheSound Failed!!!!!");

		}
        logger.info("Finish deploying new software version.");

    }

//	@RequestMapping(value = CandidateRestURIConstants.DEPLOY, method = RequestMethod.POST)
//	public void placeVoiceCall()
//	{
//		logger.info("Start deploying new software version.");
//
//		try {
//
//			//turnOnTheLight();
//			turnOnTheSound();
//
//		} catch (Exception e) {
//			logger.error("turnOnTheSound Failed!!!!!");
//
//		}
//		logger.info("Finish deploying new software version.");
//
//	}
	/**
	 * 
	 * @param can
	 */
	private void saveToDatabase(CandidateMapper can) 
	{
		can.setCreatedDate(new Date());
		canData.put(can.getId(), can);  //TODO - Check if map is still requrired. 
		Candidate saveCan = new Candidate();
		saveCan.setEmail(can.getEmail());
		saveCan.setFullName(can.getFullName());
		saveCan.setTelephone(can.getTelephone());
		saveCan.setCompany(can.getCompany());
		saveCan.setComments(can.getComments());
		saveCan.setCreationDate(can.getCreatedDate());
		Candidate candidate = candidateService.save(saveCan);
		Log.info("Candidate was successfully created: " + candidate.getId());
	}
	/**
	 *
	 */
	private void sendSMS(CandidateMapper can) 
	{
        SMSController smsController = null;
        Collection<ContactPerson> list = SMSAndVoiceUtils.readPhoneList();
        for (ContactPerson contactPerson : list) {
            try
            {
                String text = "Hi " + contactPerson.getName()  + ", " + SMSConstants.SMS_VENDOR_TEXT_MESSAGE_VALUE;
                can.setTelephone(contactPerson.getPhone());
                smsController = new NexmoSmsController(can, text);
                smsController.sendSMS();
            }catch(Exception ex)
            {
                logger.error("sendSMS Failed!!!!! "  + ex.getMessage());
            }
        }


	}

	private void turnOnTheLight() {
		LightController lightController = new LightController(10);

        try {
            lightController.process();
        } catch (Exception e) {
            logger.info("turnOnTheLight failed ." + e.getMessage());
        }
    }

	private void turnOnTheSound() throws Exception {
		SoundController soundController = new SoundController(1);
		soundController.process();
	}

	private void placeVoiceCallToUser(CandidateMapper can) throws Exception {
        VoiceCallController voiceCallController = new NexmoVoiceCallController(can);
        voiceCallController.createPhoneCall();
    }




	@RequestMapping(value = CandidateRestURIConstants.DELETE_CANDIDATE, method = RequestMethod.PUT)
	public @ResponseBody CandidateMapper deleteCandidate(@PathVariable("id") int candidateId) {
		logger.info("Start deleteCandidate.");
		CandidateMapper emp = canData.get(candidateId);
		canData.remove(candidateId);
		return emp;
	}

	/**
	 * 
	 */




}