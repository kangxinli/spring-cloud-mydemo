package com.sample.demo.properties.schedule;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.sample.demo.properties.config.ValueConfig;

@Component
@RefreshScope
@EnableScheduling
public class DemoTask implements SchedulingConfigurer {
	
	@Autowired
    private ContextRefresher contextRefresher;
	
	@Autowired
    private ValueConfig valueConfig;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		
		Runnable task = new Runnable() {
			@Override
			public void run() {
				// 任务逻辑代码部分.
				System.out.println("I am going:" + LocalDateTime.now());
			}
		};
		Trigger trigger = new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				
				new Thread(() -> contextRefresher.refresh()).start();
				
				System.out.println("cron : " + valueConfig.getCron());
				
				// 任务触发，可修改任务的执行周期.
				CronTrigger trigger = new CronTrigger(valueConfig.getCron());
				Date nextExec = trigger.nextExecutionTime(triggerContext);
				return nextExec;
			}
		};
		taskRegistrar.addTriggerTask(task, trigger);
	}

}
