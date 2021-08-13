package com.tao.mygecco.config;

import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.spring.SpringPipelineFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipelineFactoryConfig {

	@Bean
	public PipelineFactory springPipelineFactory() {
		return new SpringPipelineFactory();
	}
}

