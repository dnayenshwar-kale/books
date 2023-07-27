package com.ltim.books.configure;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltim.books.entity.SecretValue;

import lombok.extern.slf4j.Slf4j;
//Config class to manage AWS Secret manager value retrieving
@Configuration
@Slf4j
@Profile("prod")
public class RDSConfiguration {
	private ObjectMapper  objectMapper = new ObjectMapper();

    /**
     * Customize the data source config values reading from Bean class
     * Instead of reading from application.yaml
     */
    @Bean
    public DataSource dataSource() {
        SecretValue secretValue = getSecretValue();
        log.info("secretValue in DataSource -"+secretValue);
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .password(secretValue.getPassword())
                .username(secretValue.getUsername())
                .url("jdbc:" + secretValue.getEngine() + "://" + secretValue.getHost() + ":" + secretValue.getPort()
                        + "/" + secretValue.getDbname())
                .build();
    }

    private SecretValue getSecretValue() {

        String secretName = "prod/rds/mysql";
        String region = "us-east-1";

        // Create a Secrets Manager client
        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .build();

        String secret;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            throw e;
        }

        if (getSecretValueResult.getSecretString() != null) {
            secret = getSecretValueResult.getSecretString();
            log.info("secret -"+secret);
            try {
				return objectMapper.readValue(secret, SecretValue.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
        }
        return null;
    }
}
