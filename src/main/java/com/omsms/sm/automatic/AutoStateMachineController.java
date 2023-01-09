package com.omsms.sm.automatic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;

@RequestMapping("/autosm")
@Controller
public class AutoStateMachineController 
{
	Logger log = LoggerFactory.getLogger(AutoStateMachineController.class);
	
	@Resource(name = "autoStateMachineHandler")
	AutoStateMachineHandler autoStateMachineHandler;
	
	
	@GetMapping("/init")
	@ResponseBody
	public ResponseEntity<String> initAutoStateMachine()
	{
		StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine = autoStateMachineHandler.newInstance(AlphabatesStates.STATE_A);
	
		log.info("Publishing MOVE_TO_B <0<0<0<0<0<0<0<0<0<0<0<0<0<0");
		
		stateMachine.sendEvent(AlphabatesEvents.MOVE_TO_B);
		
		return new ResponseEntity<String>("OK",HttpStatus.OK);
	}
	
	@GetMapping("/bfail")
	@ResponseBody
	public ResponseEntity<String> bfail()
	{
		StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine = autoStateMachineHandler.newInstance(AlphabatesStates.STATE_B);
	
		log.info("Publishing MOVE_TO_B_FAIL <0<0<0<0<0<0<0<0<0<0<0<0<0<0");
		
		stateMachine.sendEvent(AlphabatesEvents.MOVE_TO_B_FAIL);
		
		return new ResponseEntity<String>("OK",HttpStatus.OK);
	}
}
