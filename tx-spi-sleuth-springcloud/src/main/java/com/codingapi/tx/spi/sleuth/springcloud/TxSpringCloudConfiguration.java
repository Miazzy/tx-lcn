package com.codingapi.tx.spi.sleuth.springcloud;

import com.codingapi.tx.spi.sleuth.TracerHelper;
import com.codingapi.tx.spi.sleuth.listener.SleuthParamListener;
import com.codingapi.tx.spi.sleuth.springcloud.ribbon.loadbalance.TXLCNZoneAvoidanceRule;
import com.netflix.loadbalancer.IRule;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * Company: CodingApi
 * Date: 2018/12/13
 *
 * @author ujued
 */
@Data
@ComponentScan
@Configuration
@ConfigurationProperties(prefix = "tx-lcn.springcloud.loadbalance")
public class TxSpringCloudConfiguration {

    private boolean enabled = false;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "tx-lcn.springcloud.loadbalance.enabled",havingValue = "true")
    public IRule ribbonRule(SleuthParamListener sleuthParamListener,
                            Registration registration,
                            TracerHelper tracerHelper){
        return new TXLCNZoneAvoidanceRule(sleuthParamListener, registration, tracerHelper);
    }
}
