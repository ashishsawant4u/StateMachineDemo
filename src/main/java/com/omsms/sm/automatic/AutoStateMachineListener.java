package com.omsms.sm.automatic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Component("autoStateMachineListener")
public class AutoStateMachineListener extends StateMachineListenerAdapter<AlphabatesStates,AlphabatesEvents>
{
	Logger log = LoggerFactory.getLogger(AutoStateMachineListener.class);

	@Override
	public void transition(Transition<AlphabatesStates, AlphabatesEvents> transition) 
	{
		log.info(">>__ AutoStateMachineListener(transition)");
		log.info("source ==> "+transition.getSource().getId().name());
		log.info("target ==> "+transition.getSource().getId().name());
	}

	@Override
	public void transitionStarted(Transition<AlphabatesStates, AlphabatesEvents> transition) 
	{
		log.info(">>__  AutoStateMachineListener(transitionStarted)");
		log.info("source ==> "+transition.getSource().getId().name());
		log.info("target ==> "+transition.getSource().getId().name());
	}

	@Override
	public void transitionEnded(Transition<AlphabatesStates, AlphabatesEvents> transition) 
	{
		log.info(">>__  AutoStateMachineListener(transitionEnded)");
		log.info("source ==> "+transition.getSource().getId().name());
		log.info("target ==> "+transition.getSource().getId().name());
	}

	@Override
	public void stateChanged(State<AlphabatesStates, AlphabatesEvents> from,
			State<AlphabatesStates, AlphabatesEvents> to) 
	{
		log.info(">>__  AutoStateMachineListener(stateChanged)");
		log.info("from ==> "+from.getId().name());
		log.info("to ==> "+to.getId().name());
	}

	@Override
	public void stateEntered(State<AlphabatesStates, AlphabatesEvents> state) 
	{
		log.info(">>__  AutoStateMachineListener(stateEntered)");
		log.info("current state ==> "+state.getId().name());
	}

	@Override
	public void stateExited(State<AlphabatesStates, AlphabatesEvents> state) 
	{
		log.info(">>__  AutoStateMachineListener(stateExited)");
		log.info("current state ==> "+state.getId().name());
	}

	@Override
	public void stateMachineError(StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine, Exception exception) 
	{
		log.info(">>__  AutoStateMachineListener(stateMachineError) "+exception.getMessage());
	}
	
	
	
	
	
}
