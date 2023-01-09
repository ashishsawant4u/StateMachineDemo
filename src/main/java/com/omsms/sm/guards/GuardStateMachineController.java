package com.omsms.sm.guards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omsms.sm.automatic.AlphabatesEvents;
import com.omsms.sm.automatic.AlphabatesStates;

import jakarta.annotation.Resource;

@RequestMapping("/guardsm")
@Controller
public class GuardStateMachineController 
{
	Logger log = LoggerFactory.getLogger(GuardStateMachineController.class);
	
	@Resource(name = "guardStateMachineHandler")
	GuardStateMachineHandler guardStateMachineHandler;
	
	@GetMapping("/init")
	@ResponseBody
	public ResponseEntity<String> initGuardStateMachine()
	{
		StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine = guardStateMachineHandler.getInstance(AlphabatesStates.STATE_A);
	
		log.info("Publishing MOVE_TO_B <0<0<0<0<0<0<0<0<0<0<0<0<0<0");
		
		Message<AlphabatesEvents> MOVE_TO_B = MessageBuilder.withPayload(AlphabatesEvents.MOVE_TO_B)
												.setHeader("someheader", "randomvalue").build();
		
		stateMachine.sendEvent(MOVE_TO_B);
		
		return new ResponseEntity<String>("OK",HttpStatus.OK);
	}
}
