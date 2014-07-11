package com.cloud.elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class StartHere {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cloud cloud1 = new Cloud(getConf("cloud1.conf"));
		cloud1.create();
		
		for(int i = 0 ; i<30; i++){
			for(Host h:cloud1.getHostList().values()){
				for(Vm vm:h.getVmList().values()){
					System.out.println(vm.getState());
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

	private static ArrayList<String> getConf(String confFile){
	
		
		ArrayList<String> confList = new  ArrayList<String>();		
		BufferedReader br ;
		try{
			br = new BufferedReader(new FileReader(confFile));
			String aLine = "";			
			while ((aLine = br.readLine()) != null) {
				confList.add(aLine);				
			}
			br.close();
			
		}catch(Exception e){
			System.out.println("Configuration file doesn't exist ,please check it!");
			return null;
		}
		
		return confList;
	}

}
