package com.cloud.rmiServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.cloud.control.FrontEnd;
import com.cloud.elements.Cloud;
import com.cloud.elements.Host;
import com.cloud.elements.Vm;


/**
 *This class involve the function that can receive the command from remote client.
 */
public class RemoteCmd extends UnicastRemoteObject implements RmiInterface{


	private static final long serialVersionUID = 1L;

	public RemoteCmd(String cmd) throws RemoteException {
		
	}


	@Override
	public ArrayList<String> executeCmd(String string) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		String cloudName = string.split(":")[0].trim();
		String cmdLine = string.split(":")[1].trim();
		Cloud cloud = FrontEnd.clouds.get(cloudName);
		if(!cmdLine.contains("simvm"))return null;
		String[] cmds = cmdLine.split(" ");
		switch(cmds[1]){
		case "list":
			for(Host h :cloud.getHostList().values()){
				for(Vm vm :h.getVmList()){
					String str = vm.getId()+","+vm.getHostname()+","+vm.getPrivateIP()+vm.getPubicIP()+","+vm.getState()+","+vm.getTime();
					result.add(str);					
				}
			}
			break;
		case "create":
			int n = 1;
			if(cmds.length>=3) n =Integer.parseInt(cmds[2].trim());
			if(cmds.length<4){
				while(n>0){
					Vm vm = cloud.create();
					String str = vm.getId()+","+vm.getHostname()+","+vm.getPrivateIP()+vm.getPubicIP()+","+vm.getState()+","+vm.getTime();
					result.add(str);
					n--;
				}
			}else{
				Host h = cloud.getHostList().get(cmds[3].trim());
				while(n>0){
					Vm vm = cloud.create(h);
					String str = vm.getId()+","+vm.getHostname()+","+vm.getPrivateIP()+vm.getPubicIP()+","+vm.getState()+","+vm.getTime();
					result.add(str);
					n--;
				}
			}
			break;
		case "suspend" :
			String str = cloud.suspend(cmds[2].trim())+"";
			result.add(str);
			break;
		case "start":
			String str1 = cloud.start(cmds[2].trim())+"";
			result.add(str1);
			break;
		case "delete" :
			String str2 = cloud.delete(cmds[2].trim())+"";
			result.add(str2);
			break;
		}
				
		
		return result;
	}

	/**
	 * This function gets the command from the remote client.
	 */

	}
