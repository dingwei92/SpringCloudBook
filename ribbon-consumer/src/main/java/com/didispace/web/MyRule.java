package com.didispace.web;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/***
 * 自定义负载均衡器，这里需要实现“IRule”接口
 * 比如端口为8081的服务器是新买的，不想让它处理太多的任务，那么可以用随机数去控制它的访问量
 * @author lpx
 *
 */
public class MyRule implements IRule {

    private ILoadBalancer lb;// 声明负载均衡器接口

    @Override
    public Server choose(Object key) {
        // 获取服务器列表
        List<Server> servers = lb.getAllServers();
        // 生产随机数
        Random r = new Random();
        System.out.println("Random+"+r);
        int rand = r.nextInt(10);
        if(rand > 7){
            return getServerByPort(servers, 8081);
        }else{
            return getServerByPort(servers, 8082);
        }
    }
    /**
     * 根据传入的端口号，返回服务对象
     * @param servers
     * @param port
     * @return
     */
    private Server getServerByPort(List<Server> servers, int port){
        for(Server s : servers){
            if(s.getPort() == port){
                return s;
            }
        }
        return null;
    }
    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }
    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.lb;
    }
}