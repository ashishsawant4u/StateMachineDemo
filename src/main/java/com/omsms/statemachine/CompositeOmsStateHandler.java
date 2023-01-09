package com.omsms.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

@Component("compositeOmsStateHandler")
public class CompositeOmsStateHandler 
{
	Logger log = LoggerFactory.getLogger(CherryPickStateHandler.class);
	
	@Autowired
	@Qualifier("omsStateMachineConfiguration2")
	StateMachineFactory<OmsStates,OmsStateEvents> omsStateMachineConfiguration;
	
	@Resource(name = "compositeStateMachineInterceptor")
	CompositeStateMachineInterceptor compositeStateMachineInterceptor;
	
	public StateMachine<OmsStates, OmsStateEvents> createNewStateMachineInstance(String referenceId,OmsStates atState)
	{
		StateMachine<OmsStates, OmsStateEvents> stateMachine = omsStateMachineConfiguration.getStateMachine(referenceId);
		
		stateMachine.stop();
		
		stateMachine.getStateMachineAccessor().doWithAllRegions(sm->{
			sm.addStateMachineInterceptor(compositeStateMachineInterceptor);
			sm.resetStateMachine(new DefaultStateMachineContext<OmsStates, OmsStateEvents>(atState, null, null, null));
		});
		
		stateMachine.start();
		
		return stateMachine;
	}
	
	public StateMachine<OmsStates, OmsStateEvents> initQC(String referenceId) 
	{
		StateMachine<OmsStates, OmsStateEvents> stateMachine = createNewStateMachineInstance(referenceId,OmsStates.SOURCING);
		
		stateMachine.sendEvent(OmsStateEvents.INIT_QC);
		
		return stateMachine;
	}
	
	public StateMachine<OmsStates, OmsStateEvents> initDelivery(String referenceId) 
	{
		StateMachine<OmsStates, OmsStateEvents> stateMachine = createNewStateMachineInstance(referenceId,OmsStates.QUALITYCHECK);
		
		stateMachine.sendEvent(OmsStateEvents.INIT_DELIVERY);
		
		return stateMachine;
	}
	
}
