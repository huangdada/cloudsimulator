package com.sbs.execute;

import com.sbs.batch.Job;
import com.sbs.batch.Sbs;

public class Executor {

	/**
	 * @param args
	 */
	public static void execute(String userCmd) {
		// TODO Auto-generated method stub
		switch(userCmd.split(" ")[0].trim()){
			case "check_pool":
				Sbs.listPool();
				break;
			case "check_q":
				Sbs.listQ();
				break;
			case "submit":
				String jobconf=userCmd.split(" ")[1].trim();
				int n = Integer.parseInt(jobconf.split(",")[0].trim());
				int t = Integer.parseInt(jobconf.split(",")[1].trim());
				for(int i = 0;i<n;i++){
					Sbs.submit(new Job(t));
				}
				break;
			case "start" :
				Sbs.start();
				break;
			case "addslot" :
				String vm = userCmd.split(" ")[1].trim();
				Sbs.addSlot(vm);
		}
	}

}
