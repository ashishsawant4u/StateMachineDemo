package com.omsms.sm.automatic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component("autoStateMachineHandler")
public class AutoStateMachineHandler 
{
	@Autowired
	@Qualifier("autoStateMachineConfiguration2")
	StateMachineFactory<AlphabatesStates, AlphabatesEvents> autoStateMachineConfiguration;
	
	@Resource(name = "autoStateMachineListener")
	AutoStateMachineListener autoStateMachineListener;
	
	@Resource(name = "autoStateMachineInterceptor")
	AutoStateMachineInterceptor autoStateMachineInterceptor;
	
	public StateMachine<AlphabatesStates, AlphabatesEvents> newInstance(AlphabatesStates atState)
	{
		StateMachine<AlphabatesStates, AlphabatesEvents> stateMachine = autoStateMachineConfiguration.getStateMachine();
		
		stateMachine.addStateListener(autoStateMachineListener);
		
		stateMachine.stop();
		
		stateMachine.getStateMachineAccessor()
					.doWithAllRegions(sm->{
						sm.addStateMachineInterceptor(autoStateMachineInterceptor);
						sm.resetStateMachine(new DefaultStateMachineContext<>(atState, null, null, null));
					});
		
		stateMachine.start();
		
		return stateMachine;
	}
}
