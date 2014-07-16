package com.sbs.batch;

import javax.swing.JTable;

public class Job {
	private int time;
	private String stat;
	private String id;
	private static int currI=0;
	public Job(int t){
		stat="waiting";
		setTime(t);
		setId();
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void setStat(String string) {
		// TODO Auto-generated method stub
		JTable jt = Sbs.mf.getTable();
		int row = 0;
		for(int i=0;i<jt.getRowCount();i++){
			if(jt.getValueAt(i, 0).toString().equalsIgnoreCase(id)){
				row = i;
				break;
			}
		}
		
		jt.setValueAt(string, row, 1);
		
		stat=string;
	}
	public String getStat(){
		return stat;
	}
	public String getId() {
		return id;
	}
	public void setId() {
		this.id = currI+++"";
	}

}
