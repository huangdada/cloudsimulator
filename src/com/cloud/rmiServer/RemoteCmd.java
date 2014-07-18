package com.cloud.rmiServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.cloud.control.Executor;


/**
 *This class involve the function that can receive the command from remote client.
 */
public class RemoteCmd extends UnicastRemoteObject implements RmiInterface{


	private static final long serialVersionUID = 1L;

	public RemoteCmd(String cmd) throws RemoteException {
	}


	@Override
	public ArrayList<String> executeCmd(String cmdLine) throws RemoteException {
		// TODO Auto-generated method stub
		Executor.execute(cmdLine);
		
		return null;
	}

	/**
	 * This function gets the command from the remote client.
	 */


}
