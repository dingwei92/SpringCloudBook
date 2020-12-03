package com.cfg;

import com.didispace.web.MyRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

/**
 * @program: spring-cloud-in-action
 * @description:
 * @author: dingwei
 * @createTime: 2020-12-01 14:19
 */


    public class LbConfig {

        @Bean
        public IRule getRule(){
            //return new WeightedResponseTimeRule();
            return new MyRule();
        }

    }

