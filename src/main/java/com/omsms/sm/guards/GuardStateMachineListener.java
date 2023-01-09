package com.omsms.sm.guards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import com.omsms.sm.automatic.AlphabatesEvents;
import com.omsms.sm.automatic.AlphabatesStates;

@Component("guardStateMachineListener")
public class GuardStateMachineListener extends StateMachineListenerAdapter<AlphabatesStates,AlphabatesEvents>
{
	Logger log = LoggerFactory.getLogger(GuardStateMachineListener.class);

	@Override
	public void stateChanged(State<AlphabatesStates, AlphabatesEvents> from,
			State<AlphabatesStates, AlphabatesEvents> to) 
	{
		log.info("Listener (stateChanged) ");
		log.info("from ==> "+from.getId().name());
		log.info("to ==> "+to.getId().name());
	}

	@Override
	public void stateEntered(State<AlphabatesStates, AlphabatesEvents> state) 
	{
		log.info("Listener (stateEntered) ");
		log.info("current state ==> "+state.getId().name());
	}

	@Override
	public void stateExited(State<AlphabatesStates, AlphabatesEvents> state) 
	{
		log.info("Listener (stateExited) ");
		log.info("current state ==> "+state.getId().name());
	}

	@Override
	public void transitionStarted(Transition<AlphabatesStates, AlphabatesEvents> transition) 
	{
		log.info("Listener (transitionStarted) ");
		log.info("source ==> "+transition.getSource().getId().name());
		log.info("target ==> "+transition.getSource().getId().name());
	}

	@Override
	public void transitionEnded(Transition<AlphabatesStates, AlphabatesEvents> transition) 
	{
		log.info("Listener (transitionEnded) ");
		log.info("source ==> "+transition.getSource().getId().name());
		log.info("target ==> "+transition.getSource().getId().name());
	}
	
	
}
