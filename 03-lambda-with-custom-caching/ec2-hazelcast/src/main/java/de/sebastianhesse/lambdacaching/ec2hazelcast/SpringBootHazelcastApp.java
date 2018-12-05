package de.sebastianhesse.lambdacaching.ec2hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MaxSizeConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * Example Sprint Boot app using Hazelcast with a test map.
 */
@SpringBootApplication
public class SpringBootHazelcastApp {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootHazelcastApp.class, args);
    }


    @Bean
    public Config createConfig() {
        Config cfg = new Config();

        cfg.setInstanceName("SpringBootHazelcastApp");

        JoinConfig joinConfig = cfg.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().setEnabled(false);
        joinConfig.getAwsConfig().setEnabled(false);

        int tenMinutes = 10 * 60;
        cfg.getMapConfig("TestMap")
                .setTimeToLiveSeconds(tenMinutes)
                .setMaxSizeConfig(new MaxSizeConfig(100, MaxSizeConfig.MaxSizePolicy.PER_NODE))
                .setEvictionPolicy(EvictionPolicy.LRU);

        return cfg;
    }
}
