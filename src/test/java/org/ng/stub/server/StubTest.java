package org.ng.stub.server;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ng.stub.server.config.StubServerConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.github.tomakehurst.wiremock.WireMockServer;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { StubServerConfigs.class })
public class StubTest {

	@Autowired
	private WireMockServer server;

	@BeforeClass
	public static void beforeClass() {
		System.setProperty("stubConfig.port", "9090");
		System.setProperty("stubConfig.configPath", "");
	}

	@Before
	public void setup() {
		server.start();
	}

	@Test
	public void shouldCallGET() throws Exception {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:9090/health");

		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuilder result = new StringBuilder();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		assertEquals("Stub is healthy and Up!", result.toString());
	}

	@After
	public void tearDown() {
		server.stop();
	}
}
