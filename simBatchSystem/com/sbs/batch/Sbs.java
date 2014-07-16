package com.sbs.batch;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sbs.gui.MyFrame;


public class Sbs {
	static{
		pool = new ArrayList<Slot>();
		queue = new ArrayList<Job>();
		mf = new MyFrame();
	}
	public static boolean addSlot(String num){
		int n = Integer.parseInt(num);
		for(int i=0;i<n;i++){
			String vm = "cloud1,v00"+i+",ip00"+i;
			Slot slot = new Slot(vm);
			if(!pool.add(slot)){
				return false;
			}
		}

		return true;
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
			//System.out.println("Batch system is running......");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(queue.isEmpty()||pool.isEmpty())continue;
			//System.out.println("executing......");
		
			try {
				for(Job job:queue){				
					if(job.getStat().equalsIgnoreCase("waiting")){
						for(Slot slot : pool){
							//System.out.println(job.getId());
							if(!slot.isActivity()){
								//System.out.println(slot.getName());
								slot.runJob(job);
								break;
							}
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				continue;
			}

		}
	}

	public static boolean addJob(int t){
		Job job = new Job(t);
		queue.add(job);	
		return true;
	}
	
	public static boolean submit(Job job){
		JTable jt = mf.getTable();
		int row = 0;
		for(int i=0;i<jt.getRowCount();i++){
			if(jt.getValueAt(i, 0)==null||jt.getValueAt(i, 0).toString().equalsIgnoreCase("null")){
				row = i;
				break;
			}
		}
		if(row ==0){
			DefaultTableModel dtm =(DefaultTableModel) jt.getModel();
			String[] data = new String[]{job.getId(),job.getStat()};
			dtm.addRow(data);
		}else{
			jt.setValueAt(job.getId(), row, 0);
			jt.setValueAt(job.getStat(), row, 1);
		}
		//jt.updateUI();
		return queue.add(job);
	}
	public static boolean rmJob(Job job){
		JTable jt = mf.getTable();
		int row = 0;
		while(true){
			try {
				for(int i=1;i<jt.getRowCount();i++){		
					if(jt.getValueAt(i, 0).toString().equalsIgnoreCase(job.getId())){
						row = i;
						break;
					}	
				}
				break;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				continue;
			}
		}
		DefaultTableModel dtm =(DefaultTableModel) jt.getModel();
		dtm.removeRow(row);
		
		//dtm.addRow(new String[10]);
		//jt.removeRowSelectionInterval(row, row);		
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
	public static MyFrame mf;
}
