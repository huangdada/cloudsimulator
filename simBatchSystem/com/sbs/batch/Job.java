package com.sbs.batch;

public class Job {
	private int time;
	private String stat;
	public Job(int t){
		setStat("waiting");
		setTime(t);
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void setStat(String string) {
		// TODO Auto-generated method stub
		stat=string;
	}
	public String getStat(){
		return stat;
	}

}
