package com.amat.controller;
import org.springframework.stereotype.Controller;

import com.amat.controller.interfaces.IController;
import com.amat.utils.SoundUtils;

@Controller
final class SoundController implements IController
{

	public int numberOfTimesToTurnOn = 5;



	public SoundController(int numberOfTimesToTurnOn) 
	{
		super();
		this.numberOfTimesToTurnOn = numberOfTimesToTurnOn;
	}

	public SoundController() 
	{
		super();
	}



	private void turnOntheSound() 
	{
		for (int i = 0; i < numberOfTimesToTurnOn; i++) 
		{
			if (i== 0)
			{
				SoundUtils sound = new SoundUtils();
				sound.playSound();
			}
		}
	}


	public void process() throws Exception
	{
		turnOntheSound();		
	}




}