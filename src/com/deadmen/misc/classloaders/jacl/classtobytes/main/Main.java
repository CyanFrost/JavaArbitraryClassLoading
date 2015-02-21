package com.deadmen.misc.classloaders.jacl.classtobytes.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Main {

	private JFrame frame;
	private JTextField textField;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 531, 114);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		
		textField.setColumns(10);
		
		JLabel lblInput = new JLabel("Input");
		
		JButton btnNewButton = new JButton("Choose");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Input Button*/
				String s =getPath();
				if(s != null){
					textField.setText(s);
				}
			}
		});
		
		JButton btnChoose = new JButton("Save");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Save Button*/
				 JFileChooser c = new JFileChooser();
			      // Demonstrate "Save" dialog:
			      int rVal = c.showSaveDialog(null);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			    	  try{
			    		  work(c.getSelectedFile().getAbsolutePath().toString());
			    	  }catch(Exception exception){}
			      }
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnChoose, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblInput)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInput)
						.addComponent(btnNewButton)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnChoose)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public void work(String s) throws Exception{
		  int _offset=0;
	        int _read=0;

	        File fileName = new File(textField.getText() /*Input*/); 
	        InputStream fileInputStream = new FileInputStream(fileName);
	        FileOutputStream fileOutputStream = new FileOutputStream(s/*Output*/);
	        PrintStream printStream = new PrintStream(fileOutputStream);
	        StringBuffer bytesStringBuffer = new StringBuffer();
	        
	        byte[] byteArray = new byte[(int)fileName.length()];
	        while (_offset < byteArray.length && (_read=fileInputStream.read(byteArray, _offset, byteArray.length-_offset)) >= 0)
	        {
	            _offset += _read;    
	        }
	        fileInputStream.close();
	        for (int index = 0; index < byteArray.length; index++)
	        {
	            bytesStringBuffer.append(byteArray[index]+",");
	        }

	        printStream.print(bytesStringBuffer.length()==0 ? "" :  bytesStringBuffer.substring(0, bytesStringBuffer.length()-1));
	        printStream.flush();
	        printStream.close();
	        JOptionPane.showMessageDialog(null, "Saved file to:\n" + s);
	}
	
	
	
	
	public String getPath(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnValue = chooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          return chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
	}
}
