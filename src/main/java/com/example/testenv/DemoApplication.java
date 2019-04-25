package com.example.testenv;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

	public static Map<String, Object> dotEnv() {
		return Dotenv.configure()
				.ignoreIfMissing()
				.load()
				.entries()
				.stream()
				.collect(Collectors.toMap(DotenvEntry::getKey, DotenvEntry::getValue));
	}

	public static ConfigurableEnvironment preloadedEnv(ConfigurableEnvironment configurableEnvironment, Map<String, Object> keyValues, String sourceName) {
		MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
		propertySources.addFirst(new SystemEnvironmentPropertySource(sourceName, keyValues));
		return configurableEnvironment;
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setEnvironment(preloadedEnv(new StandardEnvironment(), dotEnv(), "dotEnv"));
		app.run(args);
	}
}
