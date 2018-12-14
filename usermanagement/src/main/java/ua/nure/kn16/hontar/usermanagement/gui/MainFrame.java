package ua.nure.kn16.hontar.usermanagement.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.nure.kn16.hontar.usermanagement.User;
import ua.nure.kn16.hontar.usermanagement.db.DaoFactory;
import ua.nure.kn16.hontar.usermanagement.db.UserDAO;
import ua.nure.kn16.hontar.usermanagement.util.Messages;

public class MainFrame extends JFrame {
	
	private static final int FRAME_HEIGTH = 600;
	private static final int FRAME_WIDTH = 800;
	
	private JPanel contentPanel;
	private JPanel browsePanel;
	
	private AddPanel addPanel;
    private EditPanel editPanel;
	private DetailsPanel detailsPanel;
	private UserDAO dao;


	public MainFrame()
	{
		super();
		dao = DaoFactory.getInstance().getUserDao();
			    
			    initialize();
	}
	
	public UserDAO getDao() {
		return dao;
	}
	
	private void initialize(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGTH);
		this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
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
		((BrowsePanel)browsePanel).initTable();
		return browsePanel;
	}
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
        frame.setVisible(true);
	}

	public void showAddPanel() {
		showPanel(getAddPanel());
		
	}
	private void showPanel(AddPanel panel) {
	getContentPane().add(panel, BorderLayout.CENTER);
	panel.setVisible(true);
	panel.repaint();
		
	}

	private AddPanel  getAddPanel() {
		if (addPanel == null) {
			addPanel =  new AddPanel(this);
		}
		return addPanel;
		
	}
	private void showPanel(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
    }
	public void showBrowsePanel() {
        showPanel(getBrowsePanel());
    }
	
	private EditPanel getEditPanel() {
        if (editPanel == null) {
            editPanel = new EditPanel(this);
        }
        return editPanel;
    }


    public User getSelectedUser() {
        return ((BrowsePanel) browsePanel).getSelectedUser();
    }

    public void showEditPanel() {
        showPanel(getEditPanel());
    }

    private DetailsPanel getDetailsPanel() {
        if (detailsPanel == null) {
            detailsPanel = new DetailsPanel(this);
        }
        return detailsPanel;
    }

    public void showDetailsPanel() {
        showPanel(getDetailsPanel());
    }
    
}
