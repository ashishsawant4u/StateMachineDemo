package com.omsms.sm.automatic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component("autoStateMachineCommonAction")
public class AutoStateMachineCommonAction implements Action<AlphabatesStates, AlphabatesEvents>
{
	Logger log = LoggerFactory.getLogger(AutoStateMachineCommonAction.class);
	
	@Override
	public void execute(StateContext<AlphabatesStates, AlphabatesEvents> context)
	{
		log.info("#############################################################################");
		log.info("Performing Some Action For "+context.getStateMachine().getState().getId().name()+" ...");
		log.info("#############################################################################");
		
		if(context.getStateMachine().getState().getId().name().equals(AlphabatesStates.STATE_B.name()))
		{
			log.info("setting variable in for STATE_B...");
			context.getExtendedState().getVariables().putIfAbsent("somedata", "somedata from state B");
			int divBy = (int)context.getExtendedState().getVariables().getOrDefault("divby", 10);
			log.info("VAR divBy "+divBy);
			int x = 100/divBy;
		}
		
	} 
	
}
