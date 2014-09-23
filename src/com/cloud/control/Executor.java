package com.cloud.control;



public class Executor {

	public static void execute(String userCmd) {
		// TODO Auto-generated method stub
		String cmd = userCmd.split(" ")[0].trim();
		if(cmd.equalsIgnoreCase("simvm")){
			String cmd2 =  userCmd.split(" ")[1].trim();
			switch(cmd2){
				case "list" :
					FrontEnd.currCloud.list();
					break;
				case "create" :
					int n =1;
					try {
						n = Integer.parseInt(userCmd.split(" ")[2].trim());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						n =1;
					}
					for(int i = 0 ; i < n ; i++){
						
						FrontEnd.currCloud.create();
					}
					break;
				case "suspend" :
					String id = userCmd.split(" ")[2].trim();	
					FrontEnd.currCloud.suspend(id);
					break;
				case "start" :
					String id1 = userCmd.split(" ")[2].trim();
					FrontEnd.currCloud.start(id1);
					break;
				case "delete" :
					String id2 = userCmd.split(" ")[2].trim();
					FrontEnd.currCloud.delete(id2);
					//FrontEnd.currCloud.delete(id2);
					break;
				
			}
		}else if (cmd.equalsIgnoreCase("simhost")){
			FrontEnd.currCloud.hostlist();
		}
	}

}
