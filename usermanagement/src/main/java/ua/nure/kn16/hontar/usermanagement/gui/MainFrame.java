package ua.nure.kn16.hontar.usermanagement.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	private static final int FRAME_HEIGTH = 800;
	private static final int FRAME_WIDTH = 1000;
	
	private JPanel contentPanel;
	private JPanel browsePanel;


	public MainFrame()
	{
		super();
			    
			    initialize();
	}
	
	private void initialize(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGTH);
		this.setTitle("User Management");
		this.setContentPane(getContentPanel());
	}
	
	private JPanel getContentPanel() {
		if(contentPanel==null)
		{
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(),BorderLayout.CENTER);
		}
		return contentPanel;
	}
	
	private JPanel getBrowsePanel() {
		if(browsePanel==null)
		{
			browsePanel = new BrowsePanel(this);
			//browsePanel.setLayout(new BorderLayout());
			//browsePanel.add(getBrowsePanel(),BorderLayout.CENTER);
		}
		return browsePanel;
	}
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
        frame.setVisible(true);
	}

}
