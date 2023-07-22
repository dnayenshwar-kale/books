package com.ltim.books.handler;

import javax.ws.rs.core.Application;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AsynchronousLambdaHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
    //Asynchronous – where the start-up time of our application is likely to take longer
	private SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public AsynchronousLambdaHandler() throws ContainerInitializationException {
        handler = (SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse>) 
          new SpringBootProxyHandlerBuilder()
            .springBootApplication(Application.class)
            .asyncInit()
            .buildAndInitialize();
    }

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest input, Context context) {
        return handler.proxy(input, context);
    }
}
