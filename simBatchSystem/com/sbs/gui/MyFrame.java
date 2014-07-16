package com.sbs.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyFrame extends JFrame {

	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	
	
	
	public MyFrame(){
		ImageIcon icon = new ImageIcon("icon.jpg"); 
		this.setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocation(800, 400);
		final JScrollPane scrollPane = new JScrollPane();
	    getContentPane().add(scrollPane, BorderLayout.CENTER);
	    DefaultTableModel tmd=new DefaultTableModel(20,10);
        table = new JTable(tmd);
        scrollPane.setViewportView(table);
		
        table.setValueAt("ID", 0, 0);
        table.setValueAt("Stat", 0, 1);
        
   	    add(scrollPane);
	    setVisible(true);
	}
	



	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}
	

	private JTable table;

}
