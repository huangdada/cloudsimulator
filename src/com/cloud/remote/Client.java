package com.cloud.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.sbs.remote.RemoteInterface;

public class Client {

	/**
	 * @param args
	 */
	public static synchronized boolean addSlot(String args) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			RemoteInterface ri = (RemoteInterface) Naming.lookup("rmi://localhost:1099/command");
			return ri.addSlot(args);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public static synchronized boolean rmSlot(String args) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			RemoteInterface ri = (RemoteInterface) Naming.lookup("rmi://localhost:1099/command");
			return ri.rmSlot(args);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
