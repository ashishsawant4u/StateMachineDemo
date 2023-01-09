package com.omsms.sm.guards;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.omsms.sm.automatic.AlphabatesEvents;
import com.omsms.sm.automatic.AlphabatesStates;

import jakarta.annotation.Resource;

@Configuration
@EnableStateMachineFactory(name = "guardStateMachineConfigurationRef")
public class GuardStateMachineConfiguration extends StateMachineConfigurerAdapter<AlphabatesStates, AlphabatesEvents>
{
	
	@Resource(name = "guardStateMachineCommonGuard")
	GuardStateMachineCommonGuard guardStateMachineCommonGuard;
	
	@Resource(name = "guardStateMachineCommonAction")
	GuardStateMachineCommonAction guardStateMachineCommonAction;

	@Override
	public void configure(StateMachineStateConfigurer<AlphabatesStates, AlphabatesEvents> states) throws Exception 
	{	
		states.withStates()
			  .initial(AlphabatesStates.STATE_A)
			  .states(EnumSet.allOf(AlphabatesStates.class))
			  .end(AlphabatesStates.STATE_D)
			  .end(AlphabatesStates.STATE_B_FAIL)
			  .end(AlphabatesStates.STATE_C_FAIL);
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<AlphabatesStates, AlphabatesEvents> transitions)
			throws Exception 
	{
		transitions.withExternal()
				   .source(AlphabatesStates.STATE_A)
				   .target(AlphabatesStates.STATE_B)
				   .event(AlphabatesEvents.MOVE_TO_B)
				   .guard(guardStateMachineCommonGuard)
				   .action(guardStateMachineCommonAction);
		
		transitions.withExternal()
				   .source(AlphabatesStates.STATE_B)
				   .target(AlphabatesStates.STATE_C)
				   .event(AlphabatesEvents.MOVE_TO_C)
				   .guard(guardStateMachineCommonGuard)
				   .action(guardStateMachineCommonAction);
		
		transitions.withExternal()
				   .source(AlphabatesStates.STATE_C)
				   .target(AlphabatesStates.STATE_D)
				   .event(AlphabatesEvents.MOVE_TO_D)
				   .guard(guardStateMachineCommonGuard)
				   .action(guardStateMachineCommonAction);
		
		
		transitions.withExternal()
				   .source(AlphabatesStates.STATE_B)
				   .target(AlphabatesStates.STATE_B_FAIL)
				   .event(AlphabatesEvents.MOVE_TO_B_FAIL)
				   .guard(guardStateMachineCommonGuard)
				   .action(guardStateMachineCommonAction);
		
		transitions.withExternal()
				   .source(AlphabatesStates.STATE_C)
				   .target(AlphabatesStates.STATE_C_FAIL)
				   .event(AlphabatesEvents.MOVE_TO_C_FAIL)
				   .guard(guardStateMachineCommonGuard)
				   .action(guardStateMachineCommonAction);
	}
	
	
	
}
