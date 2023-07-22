package com.ltim.books;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.ltim.books.handler.AsynchronousLambdaHandler;
import com.ltim.books.handler.SynchronousLambdaHandler;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class BooksApplicationTests {

	//@Test
	void contextLoads() {
	}

	
	@Test
	void whenTheUsersPathIsInvokedViaSyncLambda_thenShouldReturnAList() throws IOException {
		SynchronousLambdaHandler lambdaHandler = new SynchronousLambdaHandler();
	    AwsProxyRequest req = new AwsProxyRequestBuilder("/api/book", "GET").build();
	    Context lambdaContext = null;
		AwsProxyResponse resp = lambdaHandler.handleRequest(req, lambdaContext);
		log.info("res-"+resp.getBody().isEmpty());
	    Assertions.assertNotNull(resp.getBody());
	    Assertions.assertEquals(200, resp.getStatusCode());
	}
	
	
	//@Test
	void whenTheUsersPathIsInvokedViaAsyncLambda_thenShouldReturnAList() throws IOException, ContainerInitializationException {
		AsynchronousLambdaHandler lambdaHandler = new AsynchronousLambdaHandler();
	    AwsProxyRequest req = new AwsProxyRequestBuilder("/api/book", "GET").build();
	    Context lambdaContext = null;
		AwsProxyResponse resp = lambdaHandler.handleRequest(req, lambdaContext);
		log.info("res-"+resp.getBody().isEmpty());
	    Assertions.assertNotNull(resp.getBody());
	    Assertions.assertEquals(200, resp.getStatusCode());
	}
}
