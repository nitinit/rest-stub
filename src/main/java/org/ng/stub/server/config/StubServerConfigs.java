package org.ng.stub.server.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

@Configuration
public class StubServerConfigs {

	private static Logger logger = LoggerFactory.getLogger(StubServerConfigs.class);

	@Value("${stubConfig.port}")
	private int port;

	@Value("${stubConfig.configPath}")
	private String configPath;

    /**
     * WireMockServer Bean
     * @return
     */
    @Bean
    public WireMockServer wireMockServer() {

        WireMockServer wireMockServer =  new WireMockServer(
                WireMockConfiguration.options()
						.extensions(new ResponseTemplateTransformer(true))
						.port(port)
						.withRootDirectory(StringUtils.isBlank(configPath)
								? getClass().getClassLoader().getResource("stub_templates").getFile()
								: configPath));

		logger.info("WireMock monitoring directory: " + wireMockServer.getOptions().filesRoot().getPath());

		return wireMockServer;
    }
}