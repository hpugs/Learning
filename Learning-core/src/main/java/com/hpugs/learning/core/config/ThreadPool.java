package com.hpugs.learning.core.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.hpugs.learning.common.constant.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/24 下午6:03
 */
@Configuration
public class ThreadPool {

    @Value("${threadPool.codeSize}")
    public Integer codeSize;

    @Value("${threadPool.maxSize}")
    public Integer maxSize;

    @Value("${threadPool.keepAliveSeconds}")
    public Integer keepAliveSeconds;

    @Value("${threadPool.queueLength}")
    public Integer queueLength;

    @Bean(Constants.THREAD_POOL_NAME)
    public Executor initThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(codeSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxSize);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        threadPoolTaskExecutor.setQueueCapacity(queueLength);
        threadPoolTaskExecutor.setThreadNamePrefix("ThreadPool:");

        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolTaskExecutor.initialize();

        return TtlExecutors.getTtlExecutor(threadPoolTaskExecutor);
    }

}
