package de.sebastianhesse.lambdacaching;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * An example Lambda function to connect to a Hazelcast cluster.
 */
public class HazelcastLambdaFunction implements RequestStreamHandler {

    private HazelcastInstance hazelcastInstance;


    public HazelcastLambdaFunction() {
        connectToHazelcast();
    }


    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        if (hazelcastInstance != null) {
            System.out.println("Running Lambda function... Using Hazelcast: " + hazelcastInstance.getName());

            IMap<String, String> testMap = hazelcastInstance.getMap("TestMap");
            testMap.put("foo", "bar");
            System.out.println("Cached data: " + testMap.get("foo"));
        } else {
            System.out.println("Not connected to a Hazelcast instance.");
        }
    }


    private void connectToHazelcast() {
        System.out.println("Connecting to Hazelcast...");

        String hazelcastUrl = System.getenv("SPRING_BOOT_HAZELCAST_INSTANCE_IP");

        if (hazelcastUrl == null || "".equals(hazelcastUrl)) {
            System.out.println("Can not connect to Hazelcast: instance ip not set.");
        } else {
            System.out.println("Using instance ip: " + hazelcastUrl);

            ClientConfig clientConfig = new ClientConfig();
            clientConfig.getNetworkConfig().addAddress(hazelcastUrl + ":5701");
            hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);

            System.out.println("Connected to Hazelcast: " + hazelcastInstance.getName());
        }
    }
}
