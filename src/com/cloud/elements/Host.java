package com.cloud.elements;

import java.util.ArrayList;

/**
 * A class that presents a host of cloud. 
 */
public class Host extends Element {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Vm> vmList;
	private String cloudName;
	private int maxVmNum;
	private String id;
	private String name;
	private String ipmiID;
	private int powerStat = 1;
	public Host(){
		vmList = new ArrayList<Vm>();
	}
	
	/**
	 * Constructor that specify the id,cloud name and the max capability of virtual machine creation. 
	 */
	
	public Host(int maxVmNum, String id,String name, String cloudName) {
		this.maxVmNum = maxVmNum;
		this.id = id;
		this.cloudName = cloudName;
		vmList = new ArrayList<Vm>();
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Get the status of power,on or off
	 * @return power Status
	 */
	public int getPowerStat() {
		return powerStat;
	}


	/**
	 * Set the status of the power,on or off.
	 * @param the power status.
	 */
	public void setPowerStat(int powerStat) {
		this.powerStat = powerStat;
	}


	/**
	 * Get the ipmiID
	 * @return the string of ipmiID
	 */
	public String getIpmiID() {
		return ipmiID;
	}


	/**
	 * Set the ipmi ID
	 * @param ipmiID.
	 */
	public void setIpmiID(String ipmiID) {
		this.ipmiID = ipmiID;
	}


	/**
	 * Get the remaining capability of creating virtual machines.
	 * @return The number of how many virtual machines can be created on this host.
	 */
	public int getRemainingVmNum() {
		return maxVmNum-this.getVmList().size();
	}


	/**
	 * Set the  remaining capability of creating virtual machines.
	 * @param  The number of how many virtual machines can be created on this host.
	 */
	public void setRemainingVmNum() {
	}

	/**
	 * Get the cloud name that this host belongs to.
	 * @return cloud name 
	 */
	public String getCloudName() {
		return cloudName;
	}

	/**
	 *Set the cloud name that this host belongs to.
	 *@param cloud name 
	 */
	public void setCloudName(String cloudName) {
		this.cloudName = cloudName;
	}
	
	/**
	 * Get the virtual machines' list that running on this host.
	 * @return A TreeMap of the instances of virtual machines. as the key set is vitual machine's id. 
	 */
	public ArrayList<Vm> getVmList() {
		
		return vmList;
	}
	
	/**
	 * Set the virtual machines list that running on the host.
	 * @param vmList, a treeMap of virtual machines' instances
	 */
	public void setVmList(ArrayList<Vm> vmList) {
		this.vmList = vmList;
	}
	
	/**
	 * Get the max capability of virtual machine creation.
	 * @return maxVmNum, the max number of virtual machines that can be launched on this host. 
	 */
	public int getMaxVmNum() {
		return maxVmNum;
	}
	
	/**
	 * Set the max capability of virtual machine creation.
	 * @param maxVmNum, the max number of virtual machines that can be launched on this host. 
	 */
	public void setMaxVmNum(int maxVmNum) {
		this.maxVmNum = maxVmNum;
	}
	/**
	 * Get the number of running virtual machines.
	 * @return the number of virtual machines that already be launched on this host. 
	 */
	public int getCurrVmNum() {
		return getVmList().size();
	}
	/**
	 * Set the number of running virtual machines.
	 * @param the number of virtual machines that already be launched on this host. 
	 */
	public void setCurrVmNum(int currVmNum) {
	}
	
	/**
	 * Get the host id
	 * @return the id of the host.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Set the host id.
	 * @param the id of the host.
	 */
	public void setId(String id) {
		this.id = id;
	}


	public boolean isFull() {
		if(this.getRemainingVmNum()==0){
			return true;
		}else{
			return false;
		}
		
	}
	public Vm getVm(String id){
		
		for(Vm vm : vmList){
			if(vm.getId().equals(id)){
				return vm;
			}
		}
		return null;
	}
	public boolean addVm(Vm vm){
		return vmList.add(vm);
	}

	public void setFull(boolean isFull) {
	}
	
	public boolean delete(String id2) {
		// TODO Auto-generated method stub		
		Vm vm = this.getVm(id2);
		vm.shutdown();
		 return vmList.remove(vm);
	}

}
