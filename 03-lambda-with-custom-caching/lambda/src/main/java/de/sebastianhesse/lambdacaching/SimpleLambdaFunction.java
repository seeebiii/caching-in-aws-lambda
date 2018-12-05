package de.sebastianhesse.lambdacaching;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * An example Lambda function to show that constructor/initialization time is not charged.
 */
public class SimpleLambdaFunction implements RequestStreamHandler {

    public SimpleLambdaFunction() {
        try {
            System.out.println("Now: " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("Now: " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void handleRequest(InputStream input,
            OutputStream output,
            Context context)
            throws IOException {
        System.out.println("Hello World.");
    }
}
