package com.omsms.sm.guards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import com.omsms.sm.automatic.AlphabatesEvents;
import com.omsms.sm.automatic.AlphabatesStates;

import jakarta.annotation.Resource;

@Component("guardStateMachineHandler")
public class GuardStateMachineHandler 
{
	@Autowired
	@Qualifier("guardStateMachineConfigurationRef")
	StateMachineFactory<AlphabatesStates, AlphabatesEvents> guardStateMachineConfigurationRef;
	
	@Resource(name = "guardStateMachineListener")
	GuardStateMachineListener guardStateMachineListener;
	
	public StateMachine<AlphabatesStates, AlphabatesEvents> getInstance(AlphabatesStates atState)
	{
		StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine =guardStateMachineConfigurationRef.getStateMachine();
		
		stateMachine.stop();
		
		stateMachine.addStateListener(guardStateMachineListener);
		
		stateMachine.getStateMachineAccessor()
					.doWithAllRegions(sm->{
						sm.resetStateMachine(new DefaultStateMachineContext<>(atState, null, null, null));
					});
		
		stateMachine.start();
		
		return stateMachine;
	}
}
