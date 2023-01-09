package com.omsms.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.stereotype.Component;

@Component("compositeStateMachineInterceptor")
public class CompositeStateMachineInterceptor  extends StateMachineInterceptorAdapter<OmsStates , OmsStateEvents>
{
Logger log = LoggerFactory.getLogger(OmsStateMachineInterceptor.class);
	
	
	@Override
	public StateContext<OmsStates , OmsStateEvents> postTransition(StateContext<OmsStates , OmsStateEvents> stateContext) 
	{
		log.info("====CompositeStateMachineInterceptor========================================================");
		log.info("postTransition ==> "+stateContext.getStateMachine().getState().getId().name());
		log.info("====================================================================");
		
		return stateContext;
	}	
}
