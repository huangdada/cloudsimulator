package com.sbs.batch;

import java.util.ArrayList;


public class Sbs {
	static{
		pool = new ArrayList<Slot>();
		queue = new ArrayList<Job>();
	}
	public static boolean addSlot(String vm){
		Slot slot = new Slot(vm);
		return pool.add(slot);
	}
	
	public static boolean rmSlot(String name){
		Slot slot=null;
		for(Slot s : pool){
			if(s.getName().equalsIgnoreCase(name)){
				slot=s;
				break;
			}
		}
		return pool.remove(slot);
	}
	public static void start(){
		Thread t = new Thread(new Runnable(){  
			public void run(){  
				execute();				
			}});  
			t.start();  
	}
	public static void execute(){
		while(true){
			if(queue.isEmpty()||pool.isEmpty())continue;
			for(Job job:queue){
				
				if(job.getStat().equalsIgnoreCase("waiting")){
					for(Slot slot : pool){
						if(!slot.isActivity()){
							slot.runJob(job);
							break;
						}
					}
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static boolean addJob(int t){
		return queue.add(new Job(t));
	}
	
	public static boolean submit(Job job){
		return queue.add(job);
	}
	public static boolean rmJob(Job job){
		return queue.remove(job);
	}
	
	public static void listPool(){
		System.out.println("Name  Address  Activity");
		System.out.println("-------------------------------------------");
		for(Slot slot:pool){
			String str = slot.getName()+"  "+slot.getAddress()+"  "+slot.isActivity();
			System.out.println(str);
		}
	}
	public static void listQ(){
		System.out.println("Total  running  waiting");
		int total = queue.size();
		int running = 0;
		int waiting;
		for(Job job:queue){
			if(job.getStat().equalsIgnoreCase("running"))running++;
		}
		waiting = total-running;
		System.out.println(total+"  "+running+"  "+waiting);
		
	}
	private static ArrayList<Slot> pool;
	private static ArrayList<Job> queue;
}
