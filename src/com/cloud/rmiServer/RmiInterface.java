package com.cloud.rmiServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
/**
 *This is the interface that the server program need to be implemented
 */
public interface RmiInterface extends Remote{
	/**
	 *The remote client invokes this function. 
	 */	
	public ArrayList<String> executeCmd(String cmdLine) throws RemoteException;
}
