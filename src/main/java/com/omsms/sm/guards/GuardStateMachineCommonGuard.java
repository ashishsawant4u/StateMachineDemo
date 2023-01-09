package com.omsms.sm.guards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import com.omsms.sm.automatic.AlphabatesEvents;
import com.omsms.sm.automatic.AlphabatesStates;

@Component("guardStateMachineCommonGuard")
public class GuardStateMachineCommonGuard  implements Guard<AlphabatesStates, AlphabatesEvents>
{
	Logger log = LoggerFactory.getLogger(GuardStateMachineCommonGuard.class);

	@Override
	public boolean evaluate(StateContext<AlphabatesStates, AlphabatesEvents> context) 
	{
		log.info("Guarding in "+context.getStateMachine().getState().getId().name());
		log.info("MSG HEADER ====> "+context.getMessage().getHeaders());
		
		return true;
	}
	
}
