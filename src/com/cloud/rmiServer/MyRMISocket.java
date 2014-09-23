package com.cloud.rmiServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;


public class MyRMISocket extends RMISocketFactory 
{ 
	public Socket createSocket(String host, int port) throws IOException
	{ 
		return new Socket(host,port); 
	} 
	
	public ServerSocket createServerSocket(int port) throws IOException 
	{ 
		 if (port == 0) 
		    port = 3099;  
		return new ServerSocket(port); 
	}
	
} 