package org.ng.stub.server;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.github.tomakehurst.wiremock.WireMockServer;

@SpringBootApplication
public class StubApplication implements CommandLineRunner {

    @Autowired
    private WireMockServer wireMockServer;

    public static void main(String[] args) {
		new SpringApplicationBuilder(StubApplication.class).web(WebApplicationType.NONE)
				.run(args);
    }

    @Override
    public void run(String[] args) throws Exception {
        wireMockServer.start();
    }

    @PreDestroy
    public void tearDown() {
        wireMockServer.shutdown();
    }
}