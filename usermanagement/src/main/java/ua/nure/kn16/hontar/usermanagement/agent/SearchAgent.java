package ua.nure.kn16.hontar.usermanagement.agent;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import ua.nure.kn16.hontar.usermanagement.User;
import ua.nure.kn16.hontar.usermanagement.db.DaoFactory;
import ua.nure.kn16.hontar.usermanagement.db.DatabaseException;

public class SearchAgent extends Agent {
	
	
	private static final long MILLISECONDS = 60000;
	  
	private AID[] aids;
	SearchGui searchGui = null;
	
	 protected void setup() {
		 super.setup();
	        System.out.println(getAID().getName() + " started.");

	        searchGui = new SearchGui(this);
	        searchGui.setVisible(true);

	        DFAgentDescription dfAgentDescription = new DFAgentDescription();
	        dfAgentDescription.setName(getAID());

	        ServiceDescription serviceDescription = new ServiceDescription();
	        serviceDescription.setName("JADE-searching");
	        serviceDescription.setType("searching");
	        dfAgentDescription.addServices(serviceDescription);

	        try {
	            DFService.register(this, dfAgentDescription);
	        } catch (FIPAException e) {
	            e.printStackTrace();
	        }

	        addBehaviour(new TickerBehaviour(this, MILLISECONDS) {
	          

				
	            protected void onTick() {
	                DFAgentDescription agentDescription = new DFAgentDescription();
	                ServiceDescription serviceDescription = new ServiceDescription();

	                serviceDescription.setType("searching");
	                agentDescription.addServices(serviceDescription);

	                AID myID = getAID();

	                try {
	                    DFAgentDescription[] descriptions =
	                            DFService.search(myAgent, agentDescription);
	                    aids = new AID[descriptions.length - 1];

	                    System.out.println("Count of AID's: " + descriptions.length);
	                    List<AID> aidList = new LinkedList<>();

	                    for (int i = 0; i < descriptions.length; i++) {
	                        DFAgentDescription description = descriptions[i];
	                        if( !description.getName().equals(myID)) {
	                            aidList.add(description.getName());
	                        }
	                    }
	                    aids = aidList.toArray(new AID[0]);

	                    System.out.println("Actually of AID's: " + aids.length);

	                } catch (FIPAException e) {
	                    e.printStackTrace();
	                }
	            }
	        });

	        addBehaviour(new RequestServer());

	        }
	 
	 
	 protected void takeDown() {
		  System.out.println(getAID().getName() + " terminated.");
	        try {
	            DFService.deregister(this);
	        } catch (FIPAException e) {
	            e.printStackTrace();
	        }
	        searchGui.setVisible(false);
	        searchGui.dispose();
	        super.takeDown();
	    }
	
	 
	 public void search(String firstName, String lastName) throws SearchException {
	        try {
	            Collection<User> users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
	            if (users.size() > 0) {
	                showUsers(users);
	            } else {
	            	addBehaviour(new SearchRequestBehaviour(aids, firstName, lastName));
	            }
	        } catch (DatabaseException e) {
	            throw new SearchException();
	        }
	    }
	 public void showUsers(Collection<User> users) {
		 searchGui.addUsers(users);
		
	}
	public void setSender(String name) {
		searchGui.setSender(name);
		
	}


}
