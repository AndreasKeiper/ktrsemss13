package com.ktrsem.akka;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import akka.dispatch.DispatcherPrerequisites;
import akka.dispatch.ExecutorServiceConfigurator;
import akka.dispatch.ExecutorServiceFactory;

import com.google.appengine.api.ThreadManager;
import com.typesafe.config.Config;

public class GAEThreadPoolExecutorService extends ExecutorServiceConfigurator
		implements ExecutorServiceFactory {

	public GAEThreadPoolExecutorService(Config config,
			DispatcherPrerequisites prerequisites) {
		super(config, prerequisites);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExecutorServiceFactory createExecutorServiceFactory(String arg0,
			ThreadFactory arg1) {
		return this;
	}

	@Override
	public ExecutorService createExecutorService() {
		ThreadFactory gaethreadfactory = ThreadManager
				.currentRequestThreadFactory();
		ExecutorService gaeexec = Executors
				.newCachedThreadPool(gaethreadfactory);
		return gaeexec;
	}

}
