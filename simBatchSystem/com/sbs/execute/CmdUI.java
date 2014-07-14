package com.sbs.execute;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class CmdUI {

	String title = "SimBatchSystem >";
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
	  
	    /* execution here */
	    Executor.execute(userCmd);

	    return true;
	    
	}
	
	/**
	 *This is the main function, loops the promptGen function, keep staying at the "vcluster " prompt line, wait for inputing the command. 
	 * 
	 */
	public static void main(String[] args) throws Exception {
		CmdUI uimain = new CmdUI();
		
		boolean more = false;
		do {
			more = uimain.promptGen();
		}while (more == true);
	}

}
