package com.sample;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkItemHandler;

/**
 * This is a sample file to test a process.
 */
public class ProcessTest extends JbpmJUnitBaseTestCase {

	@SuppressWarnings("deprecation")
	@Test
	public void testProcess() {
		RuntimeManager manager = createRuntimeManager("com/myspace/testemail/Test.bpmn");
		RuntimeEngine engine = getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ksession.getWorkItemManager().registerWorkItemHandler("Email", 
               (WorkItemHandler) new org.jbpm.process.workitem.email.EmailWorkItemHandler("smtp.gmail.com", "587", "prafultest21@gmail.com", "Prafultest@21","true"));
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("to", "prafultest21@gmail.com");
        params.put("from", "prafultest21@gmail.com");
        params.put("body", "prafultest21@gmail.com from eclipse on "+new Date());
        params.put("subject", "prafultest21@gmail.com");
		ProcessInstance processInstance = ksession.startProcess("TestEmail.Test",params);
		// check whether the process instance has completed successfully
		assertProcessInstanceCompleted(processInstance.getId(), ksession);
		System.out.println("ID=="+processInstance.getId());
		assertNodeTriggered(processInstance.getId(), "Script Task 1");
		assertNodeTriggered(processInstance.getId(), "Email");
		
		manager.disposeRuntimeEngine(engine);
		manager.close();
	}

}