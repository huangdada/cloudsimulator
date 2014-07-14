package com.sbs.batch;

public class Slot {
	
	public Slot(String vm){
		String[] para = vm.split(",");
		name=para[1]+"@"+para[0];
		address = para[2].trim();
		activity = false;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isActivity() {
		return activity;
	}
	public void setActivity(boolean activity) {
		this.activity = activity;
	}

	public void runJob(final Job job){
		job.setStat("running");
		this.activity=true;
		final int time = job.getTime();
		Thread t = new Thread(new Runnable(){  
			public void run(){  
				try {
					Thread.sleep(time*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				activity=false;
				Sbs.rmJob(job);
				
			}});  
			t.start();  
	}
	
	
	private String name;
	private String address;
	private boolean activity;
	
	
}
