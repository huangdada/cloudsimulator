package com.cloud.elements;

import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.cloud.elements.Vm.VMState;


/**
 *A class representing a cloud 
 * 
 */
public class Cloud{	
	public Cloud(List<String> conf) {
		for(String aLine : conf){
			
			StringTokenizer st = new StringTokenizer(aLine, "=");
			
			if (!st.hasMoreTokens()) break;
			
			/* get a keyword */
			String aKey = st.nextToken().trim();
		
			/* get a value */
			if (!st.hasMoreTokens()) break;

			String aValue = st.nextToken().trim();
			
			if (aKey.equalsIgnoreCase("type")){
				setCloudType(aValue);					
			}else if((aKey.equalsIgnoreCase("name"))){				
				setCloudName(aValue);
			}else if((aKey.equalsIgnoreCase("responseTime"))){				
				setResponseTime(Integer.parseInt(aValue));
			}else if((aKey.equalsIgnoreCase("vm_booting_time"))){				
				setVm_booting_time(Integer.parseInt(aValue));
			}else if((aKey.equalsIgnoreCase("vm_create_time"))){				
				setVm_create_time(Integer.parseInt(aValue));
			}else if(aKey.equalsIgnoreCase("host")){
				hostList = new TreeMap<String,Host>();
				String[] list = aValue.split(",");
				int host_num = Integer.parseInt(list[0].trim());
				int vm_num = Integer.parseInt(list[1].trim());
				for(int i = 1; i<host_num+1; i++)
				{
					Host h = new Host();
					h.setName("host"+i);
					h.setId(i+"");
					h.setMaxVmNum(vm_num);
					hostList.put(h.getName(), h);
				}		
			}
		}
		
	}
	public static void main(String[] args){
		
		
	}
	
	
	private String generateID(){
		String str = "v-"+String.format("%06d", intID++);
		return str;
	}
	public boolean create(){
		for(Host h : hostList.values()){
			if(!h.isFull()){
				String id = generateID();
				final Vm vm = new Vm(id);
				h.getVmList().put(vm.getId(), vm);
				vm.setHostname(h.getName());
				vm.setPrivateIP("");
				vm.setPubicIP("");
				vm.setCreateTime(vm_create_time);
				vm.setBootingTime(vm_booting_time);
				vm.setState(VMState.PROLOG);
				Thread t = new Thread(new Runnable(){  
					public void run(){  
						vm.booting();
					}});  
					t.start();  				
				return true;
			}			
		}
		
		return false;
	}
	/**
	 *Get the type of the cloud :private or public.
	 *@return cloudType.
	 */
	public CloudType getCloudType() {
		
		return cloudType;
	}
	
	/**
	 * Set the type of a cloud
	 * @see class CloudType.
	 * @param cloudType,private or public.
	 */
	public void setCloudType(CloudType type) {
		cloudType = type;
	}
	
	public void setCloudType(String type) {
		if (type.equalsIgnoreCase("private")) 
			cloudType = CloudType.PRIVATE;
		else if (type.equalsIgnoreCase("public")) 
			cloudType = CloudType.PUBLIC;
		else {
			System.out.println("undefined type, "+type+", found");
			cloudType = CloudType.NOT_DEFINED;
		}
		
	}
	
	public String stringCloudType() {
		
		switch(cloudType) {
		case PRIVATE: return "PRIVATE";
		case PUBLIC: return "PUBLIC";
		case NOT_DEFINED: return "NOT_DEFINED";
		}
		return "NOT_DEFINED";
	}

	/**
	 *Get the cloud name .
	 *@return cloudName. 
	 */
	public String getCloudName() {
		return cloudName;
	}

	/**
	 *Set the cloud name.
	 *@param cloudName , the name of the cloud. 
	 */
	public void setCloudName(String cloudName) {
		this.cloudName = cloudName;
	}

	
	/**
	 * Get the host list of the cloud
	 * @return a TreeMap of host instance list, as key set is the host's id.
	 */
	public TreeMap<String, Host> getHostList() {
		return hostList;
	}
	
	/**
	 * Set the host list
	 * @param hostList. a TreeMap of host instances' list
	 */
	public void setHostList(TreeMap<String, Host> hostList) {
		this.hostList = hostList;
	}
	
	public int getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}
	public int getVm_booting_time() {
		return vm_booting_time;
	}
	public void setVm_booting_time(int vm_booting_time) {
		this.vm_booting_time = vm_booting_time;
	}
	public int getVm_create_time() {
		return vm_create_time;
	}
	public void setVm_create_time(int vm_create_time) {
		this.vm_create_time = vm_create_time;
	}
	public enum CloudType {PRIVATE, PUBLIC, NOT_DEFINED}
	private String cloudName;
	private CloudType cloudType;
	private TreeMap<String,Host> hostList;
	private int intID =1;
	private int responseTime;
	private int vm_booting_time;
	private int vm_create_time;
	
}
