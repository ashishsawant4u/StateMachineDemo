package com.omsms.sm.automatic;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.monitor.AbstractStateMachineMonitor;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component("autoStateMachineMonitor")
public class AutoStateMachineMonitor extends AbstractStateMachineMonitor<AlphabatesStates, AlphabatesEvents>
{
	
	Logger log = LoggerFactory.getLogger(AutoStateMachineMonitor.class);

	@Override
	public void transition(StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine,
			Transition<AlphabatesStates, AlphabatesEvents> transition, long duration) 
	{
		log.info("Monitor(transition)");	
		log.info("Current State ===> "+stateMachine.getState().getId().name());
		log.info("source ==> "+transition.getSource().getId().name());
		log.info("target ==> "+transition.getSource().getId().name());
		log.info("duration ==> "+duration);
	}

	@Override
	public void action(StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine,
			Function<StateContext<AlphabatesStates, AlphabatesEvents>, Mono<Void>> action, long duration) 
	{
		log.info("Monitor(action)");	
		log.info("Current State ===> "+stateMachine.getState().getId().name());
		log.info("action ==> "+action.getClass());
		log.info("duration ==> "+duration);
	}

}
