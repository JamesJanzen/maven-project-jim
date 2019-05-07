package com.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;

public class HelloMavenTest extends FunctionalTestCase {

	@Rule
	public DynamicPort myPort = new DynamicPort("http.port"); // sets up a dynamic http port

	@Test
	public void mavenFlowReturnsHelloMaven() throws Exception {
		System.out.println("\n\nTestcase #1 -------> myPort: " + myPort.getNumber());
		runFlowAndExpect("mavenFlow", "Hello Maven");
	}

	@Test
	public void retrieveFlightsAddsAppropriateHeader() throws Exception {
		System.out.println("\n\nTestcase #2 -------> myPort: " + myPort.getNumber());
		MuleEvent event = runFlow("retrieveFlights");
		String contentType = event.getMessage().getOutboundProperty("Content-Type");
		assertEquals("application/json", contentType);
	}

	@Override
	protected String getConfigFile() {
		return "maven-project.xml";
	}

}
