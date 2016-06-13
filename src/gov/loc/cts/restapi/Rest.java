package gov.loc.cts.restapi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.services.client.api.RemoteRuntimeEngineFactory;

public class Rest {

	public static void main(String[] args) {
        URL deploymentUrl = null;
		try {
			deploymentUrl = new URL("http://localhost:8080/kie-wb/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        RuntimeEngine engine = RemoteRuntimeEngineFactory.newRestBuilder()
            .addDeploymentId("gov.loc.cts:workflow:1.0")
            .addUrl(deploymentUrl)
            .addUserName("workbench")
            .addPassword("workbench1!")
            .build(); 
        KieSession ksession = engine.getKieSession();
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("projectId", "CIP");
        params.put("bagId", "00000000000G136C433");
        params.put("version", "44");
        params.put("recordReceived", true);
        params.put("inventory", true);
        
        ProcessInstance processInstance = ksession.startProcess("workflow.Receive", params);

       System.out.println("Started process instance: " + processInstance + " " + (processInstance == null ? "" : processInstance.getId()));

	}

}
