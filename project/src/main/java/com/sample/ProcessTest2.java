package com.sample;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.process.instance.impl.demo.SystemOutWorkItemHandler;
import org.jbpm.runtime.manager.impl.SimpleRegisterableItemsFactory;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.internal.runtime.manager.RuntimeManagerFactory;

/**
 * This is a sample file to launch a process.
 */
public class ProcessTest2 {

    public static final void main(String[] args) {
        try {
        	
        	
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-process");

            // start a new process instance
           
            
            kSession.getWorkItemManager().registerWorkItemHandler("Email", 
            		                                       (WorkItemHandler) new org.jbpm.process.workitem.email.EmailWorkItemHandler("smtp.gmail.com", "587", "prafultest21@gmail.com", "Prafultest@21","true"));
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("variable1", "Dia duit Domhanda");
            params.put("variable2", "Hallo Welt");
            params.put("variable3", "Bonjour tout le monde");
            

            kSession.startProcess("com.sample.bpmn.hello", params);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
