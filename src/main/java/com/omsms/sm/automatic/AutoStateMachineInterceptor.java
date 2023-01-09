package com.omsms.sm.automatic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Component("autoStateMachineInterceptor")
public class AutoStateMachineInterceptor extends StateMachineInterceptorAdapter<AlphabatesStates,AlphabatesEvents>
{
	Logger log = LoggerFactory.getLogger(AutoStateMachineInterceptor.class);
	
	@Override
	public Message<AlphabatesEvents> preEvent(Message<AlphabatesEvents> message,
			StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine) {
		log.info("\n\n\n");
		log.info("$$___ AutoStateMachineInterceptor(preEvent) ");
		return super.preEvent(message, stateMachine);
	}

	@Override
	public void preStateChange(State<AlphabatesStates, AlphabatesEvents> state, Message<AlphabatesEvents> message,
			Transition<AlphabatesStates, AlphabatesEvents> transition,
			StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine,
			StateMachine<AlphabatesStates, AlphabatesEvents> rootStateMachine) {
		log.info("$$___ AutoStateMachineInterceptor(preStateChange)");
		log.info("postTransition VARS ===> "+stateMachine.getExtendedState().getVariables());
		
		 super.preStateChange(state, message, transition, stateMachine, rootStateMachine);
	}

	@Override
	public void postStateChange(State<AlphabatesStates, AlphabatesEvents> state, Message<AlphabatesEvents> message,
			Transition<AlphabatesStates, AlphabatesEvents> transition,
			StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine,
			StateMachine<AlphabatesStates, AlphabatesEvents> rootStateMachine) {
		log.info("$$___ AutoStateMachineInterceptor(postStateChange)");
		super.postStateChange(state, message, transition, stateMachine, rootStateMachine);
	}

	@Override
	public StateContext<AlphabatesStates, AlphabatesEvents> preTransition(
			StateContext<AlphabatesStates, AlphabatesEvents> stateContext) {
		log.info("$$___ AutoStateMachineInterceptor(preTransition)");
		return super.preTransition(stateContext);
	}

	@Override
	public StateContext<AlphabatesStates, AlphabatesEvents> postTransition(
			StateContext<AlphabatesStates, AlphabatesEvents> stateContext) 
	{
		log.info("$$___ AutoStateMachineInterceptor(postTransition)");
		log.info("current state ==> "+stateContext.getStateMachine().getState().getId().name());
		
		AlphabatesStates currentState = stateContext.getStateMachine().getState().getId();
		
		log.info("postTransition VARS ===> "+stateContext.getStateMachine().getExtendedState().getVariables());
		
		switch(currentState)
		{
			case STATE_B : 
			{
				log.info("Publishing MOVE_TO_C  <0<0<0<0<0<0<0<0<0<0<0<0<0<0 ");
				stateContext.getStateMachine().sendEvent(AlphabatesEvents.MOVE_TO_C);
				break;
			}
			case STATE_C : 
			{
				log.info("Publishing MOVE_TO_D  <0<0<0<0<0<0<0<0<0<0<0<0<0<0 ");
				stateContext.getStateMachine().sendEvent(AlphabatesEvents.MOVE_TO_D);
				break;
			}
			case STATE_D : 
			{
				log.info("DONE! ");
				break;
			}
			default :
			{
				log.info("$$___ AutoStateMachineInterceptor(do nothing)");
			}
		}
		
		
		return stateContext;
	}

	@Override
	public Exception stateMachineError(StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine,
			Exception exception) 
	{
		log.info("$$___ AutoStateMachineInterceptor(stateMachineError) "+exception.getMessage());
		return super.stateMachineError(stateMachine, exception);
	}
	
	
	
}
