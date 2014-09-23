package com.cloud.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.cloud.elements.Cloud;
import com.cloud.rmiServer.CmdServer;



/**
 * This is the main class of vcluster, the main function is involved in this class.
 * @author S.Y.Noh
 * @version 1.0
 *
 */
public class FrontEnd {


	public static HashMap<String,Cloud> clouds;
	public static Cloud currCloud;
	String title = "simCloud >";
	/**
	 * Judge if the input command line is empty or not.Space character is regarded as empty. 
	 * @return boolean type.	 
	 */
	private boolean isEmpty(String aCmd)
	{
		if (aCmd == null) return true;
		
		String cmd = aCmd.replaceAll(" ", "");
		return cmd.isEmpty();
	}

	/**
	 * Generate "vcluster" prompt, get the input command line and send it to the executor.
	 * 
	 * @return true if it has to be continue; otherwise false
	 */
	private  boolean promptGen() {

	    String userCmd = "";

	    /* prompt */
	    System.out.print(title);
		
	    InputStreamReader input = new InputStreamReader(System.in);
	    BufferedReader reader = new BufferedReader(input);
	    
	    try {
		    /* get a command string */
	    	userCmd = reader.readLine(); 
	    	//userCmd+=" --cmdLine";
	    }
	    catch(Exception e){return true;}

	    /* check emptiness, then just no more action */
	    if (isEmpty(userCmd))
	    	return true;

	    /* if quit, then forcedly quit */
	    if (userCmd.equalsIgnoreCase("quit")) {
	    	
	    	/* forcedly exit */
	    	System.exit(0);
	    }
	    if(userCmd.contains("connect"))	{
	    	String cloudName = userCmd.split(" ")[1].trim();
	    	String confFile = cloudName+".conf";
	    	if(clouds.containsKey(cloudName)){currCloud = clouds.get(cloudName);}
	    	else{ 
	    			
	    			currCloud = new Cloud(getConf(confFile));
	    			clouds.put(currCloud.getCloudName(), currCloud);
	    		}
	    	title= cloudName+"@simCloud >";
	    }    
	    /* execution here */
	    Executor.execute(userCmd);

	    return true;
	    
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
	
	/**
	 *This is the main function, loops the promptGen function, keep staying at the "vcluster " prompt line, wait for inputing the command. 
	 * 
	 */
	public static void main(String[] args) throws Exception {
		CmdServer.initiate();
		FrontEnd uimain = new FrontEnd();
		uimain.clouds = new HashMap<String,Cloud> ();
		boolean more = false;
		do {
			more = uimain.promptGen();
		}while (more == true);
	}

}
