package com.omsms.sm.automatic;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.omsms.sm.guards.GuardStateMachineCommonGuard;

import jakarta.annotation.Resource;

@Configuration
@EnableStateMachineFactory(name="autoStateMachineConfiguration2")
public class AutoStateMachineConfiguration extends StateMachineConfigurerAdapter<AlphabatesStates, AlphabatesEvents>
{
	@Resource(name = "autoStateMachineCommonAction")
	AutoStateMachineCommonAction autoStateMachineCommonAction;
	
	@Resource(name = "stateBFailureAction")
	StateBFailureAction stateBFailureAction;
	
	@Resource(name = "guardStateMachineCommonGuard")
	GuardStateMachineCommonGuard guardStateMachineCommonGuard;
	
	@Resource(name = "autoStateMachineMonitor")
	AutoStateMachineMonitor autoStateMachineMonitor;
	
	@Override
	public void configure(StateMachineStateConfigurer<AlphabatesStates, AlphabatesEvents> states) throws Exception 
	{
		states.withStates()
			  .initial(AlphabatesStates.STATE_A)
			  .state(AlphabatesStates.STATE_B)
			  .state(AlphabatesStates.STATE_C)
			  .end(AlphabatesStates.STATE_B_FAIL)
			  .end(AlphabatesStates.STATE_C_FAIL)
			  .end(AlphabatesStates.STATE_D);
	}

	@Override
	public void configure(StateMachineConfigurationConfigurer<AlphabatesStates, AlphabatesEvents> config)
			throws Exception 
	{
		config.withConfiguration().autoStartup(false);
		config.withMonitoring().monitor(autoStateMachineMonitor);
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<AlphabatesStates, AlphabatesEvents> transitions)
			throws Exception 
	{
		transitions.withExternal()
				   .source(AlphabatesStates.STATE_A)
				   .target(AlphabatesStates.STATE_B)
				   .event(AlphabatesEvents.MOVE_TO_B)
				   .action(autoStateMachineCommonAction)
				   .guard(guardStateMachineCommonGuard);
		
		transitions.withExternal()
		   .source(AlphabatesStates.STATE_B)
		   .target(AlphabatesStates.STATE_C)
		   .event(AlphabatesEvents.MOVE_TO_C)
		   .action(autoStateMachineCommonAction,stateBFailureAction)
		   .guard(guardStateMachineCommonGuard);
		
		transitions.withExternal()
		   .source(AlphabatesStates.STATE_C)
		   .target(AlphabatesStates.STATE_D)
		   .event(AlphabatesEvents.MOVE_TO_D)
		   .action(autoStateMachineCommonAction)
		   .guard(guardStateMachineCommonGuard);
		
		transitions.withExternal()
		   .source(AlphabatesStates.STATE_B)
		   .target(AlphabatesStates.STATE_B_FAIL)
		   .event(AlphabatesEvents.MOVE_TO_B_FAIL)
		   .action(autoStateMachineCommonAction)
		   .guard(guardStateMachineCommonGuard);
		
		transitions.withExternal()
		   .source(AlphabatesStates.STATE_C)
		   .target(AlphabatesStates.STATE_C_FAIL)
		   .event(AlphabatesEvents.MOVE_TO_C_FAIL)
		   .action(autoStateMachineCommonAction)
		   .guard(guardStateMachineCommonGuard);
		
	}
	
	
	
}
