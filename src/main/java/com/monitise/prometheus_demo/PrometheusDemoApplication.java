package com.monitise.prometheus_demo;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnablePrometheusEndpoint // sets up the prometheus endpoint /prometheus-metrics
@EnableSpringBootMetricsCollector // exports the data at /metrics at a prometheus endpoint
public class PrometheusDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrometheusDemoApplication.class, args);
	}
}
