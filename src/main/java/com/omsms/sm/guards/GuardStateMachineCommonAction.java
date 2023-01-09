package com.omsms.sm.guards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import com.omsms.sm.automatic.AlphabatesEvents;
import com.omsms.sm.automatic.AlphabatesStates;

@Component("guardStateMachineCommonAction")
public class GuardStateMachineCommonAction implements Action<AlphabatesStates, AlphabatesEvents>
{
	Logger log = LoggerFactory.getLogger(GuardStateMachineCommonAction.class);
	
	@Override
	public void execute(StateContext<AlphabatesStates, AlphabatesEvents> context)
	{
		log.info("#############################################################################");
		log.info("Performing Some Action For "+context.getStateMachine().getState().getId().name()+" ...");
		log.info("#############################################################################");
		
	} 
	
}
