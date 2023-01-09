package com.omsms.sm.automatic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component("stateBFailureAction")
public class StateBFailureAction implements Action<AlphabatesStates, AlphabatesEvents>
{
	Logger log = LoggerFactory.getLogger(StateBFailureAction.class);
	
	@Override
	public void execute(StateContext<AlphabatesStates, AlphabatesEvents> context) 
	{
		log.info("#############################################################################");
		log.info("State B Failed triggering failure event ");
		log.info("#############################################################################\n");
		
		log.info("Publishing MOVE_TO_B_FAIL <0<0<0<0<0<0<0<0<0<0<0<0<0<0");
		
		context.getExtendedState().getVariables().putIfAbsent("divby", 10);
		
		context.getStateMachine().sendEvent(AlphabatesEvents.MOVE_TO_C_FAIL);
		
	}

}
