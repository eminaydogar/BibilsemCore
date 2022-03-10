package com.project.queue;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamicConsumer implements Runnable{
	
	private final static DynamicConsumer instance = new DynamicConsumer();
	
	private static HashMap<Integer, Queue<Object>> hashQueue = new HashMap<Integer, Queue<Object>>();
	
	public void sendMessageFromPublisher(int channel, Object value) {
		if (!hashQueue.containsKey(channel)) {
			Queue<Object> messageQueue = new PriorityQueue<>();
			hashQueue.put(channel, messageQueue);
		}

		hashQueue.get(channel).add(value);
	}
	
	private DynamicConsumer() {
		
	}
	
	public static DynamicConsumer getInstance() {
		return instance;
	}

	@Override
	public void run() {
          
			try {
				log.info("dynamicconsumer-run");
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}



}
