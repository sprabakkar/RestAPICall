package gov.loc.cts.restapi;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.services.client.api.RemoteRuntimeEngineFactory;

public class RestAPILiveServerCall {
    private static String deploymentId = "com.loc.workflow:workflow:1.8";
    private static String serverInstanceUrl = "http://localhost:8080/kie-wb/";
    private static String username = "workbench";
    private static String password = "workbench1!";
    private static  String processId = "workflow.TestProcess";
    
    public static void remoteRestApi() {
        URL deploymentUrl = null;
		try {
			deploymentUrl = new URL(serverInstanceUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        RuntimeEngine engine = RemoteRuntimeEngineFactory.newRestBuilder()
            .addDeploymentId(deploymentId)
            .addTimeout(5000)
            .addUrl(deploymentUrl)
            .addUserName(username)
            .addPassword(password)
            .build(); 
        KieSession ksession = engine.getKieSession();
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("projectId", "CIP");
        params.put("bagId", "00000000000G136C43");
        params.put("version", "1");
        params.put("recordReceived", true);
        params.put("inventory", true);
        
        ProcessInstance processInstance = ksession.startProcess(processId);

       System.out.println("Started process instance: " + processInstance + " " + (processInstance == null ? "" : processInstance.getId()));

    }
    public static void main(String[] args) {
    	remoteRestApi();
	}
}
