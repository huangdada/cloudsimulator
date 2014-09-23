package com.cloud.rmiServer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;

/**
 *This class starts the vcluster as the server mode
 */
public class CmdServer {
	/**
	 *This function initiates vcluster server
	 */
	public static void initiate(){
		try {
			System.setProperty("java.rmi.server.hostname","localhost");
			RMISocketFactory.setSocketFactory (new MyRMISocket()); 
			LocateRegistry.createRegistry(7099); 
			RemoteCmd rcmd = new RemoteCmd("simCloud");
			Naming.rebind("rmi://localhost:7099/simCloud", rcmd);
			System.out.println("Rmi server is running... ...");
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
