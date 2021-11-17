import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.*;

public class AverageAgent extends Agent {
    // Agent value used for average calculation
    private int value;

    // Number of agents in network
    private int numberOfAgents;

    // Neighbors agents nicknames
    private HashSet<String> neighbors;

    // Table to store values of all agents
    private HashMap<String, Integer> agentsValues;

    @Override
    protected void setup() {
        System.out.println(getAID().getLocalName() + " agent creating");

        neighbors = new HashSet<>();
        agentsValues = new HashMap<>();

        parseArguments(getArguments());

        agentsValues.put(getAID().getLocalName(), value);

        addBehaviour(new SendValuesOneShotBehaviour());
        addBehaviour(new ReceiveNeighborsValuesBehaviour());

        System.out.println(getAID().getLocalName() + " agent created");
    }

    private void parseArguments(Object[] arguments) {
        value = (int) arguments[0];
        System.out.println(getAID().getLocalName() + " got value = " + value);

        numberOfAgents = (int) arguments[1];
        System.out.println(getAID().getLocalName() + " got number of agents = " + numberOfAgents);

        for (int i = 2; i < arguments.length; i++) {
            neighbors.add((String) arguments[i]);
        }
        System.out.println(getAID().getLocalName() + " got neighbors = " + neighbors);
    }

    @Override
    protected void takeDown() {
        System.out.println(getAID().getLocalName() + " agent terminating");
        System.out.println(getAID().getLocalName() + " agent terminated");
    }

    public class ReceiveNeighborsValuesBehaviour extends Behaviour {

        @Override
        public void action() {
            ACLMessage message = myAgent.receive();

            if (message != null) {
                String content = message.getContent();

                HashMap<String, Integer> newAgentsValues = parseContent(content);
                enrichAgentsValues(newAgentsValues);

                System.out.println(getAID().getLocalName() + " agent received message with content = " + content);

                if (agentsValues.size() == numberOfAgents) {
                    myAgent.addBehaviour(new CalculateAverageOneShotBehaviour());
                } else {
                    myAgent.addBehaviour(new SendValuesOneShotBehaviour());
                }
            } else {
                block();
            }
        }

        private HashMap<String, Integer> parseContent(String content) {
            HashMap<String, Integer> agentsValues = new HashMap<>();

            String[] contentSplit = content.split(" ");
            for (int i = 0; i < contentSplit.length; i += 2) {
                String neighborNickname = contentSplit[i];
                Integer neighborValue = Integer.parseInt(contentSplit[i + 1]);

                agentsValues.put(neighborNickname, neighborValue);
            }

            return agentsValues;
        }

        private void enrichAgentsValues(HashMap<String, Integer> newAgentsValues) {
            agentsValues.putAll(newAgentsValues);
        }

        @Override
        public boolean done() {
            return agentsValues.size() == numberOfAgents;
        }
    }

    public class SendValuesOneShotBehaviour extends OneShotBehaviour {

        @Override
        public void action() {
            ACLMessage message = new ACLMessage(ACLMessage.INFORM);

            for (String neighborNickname : neighbors) {
                message.addReceiver(new AID(neighborNickname, AID.ISLOCALNAME));
            }

            StringBuilder messageContentBuilder = new StringBuilder();

            for (Map.Entry<String, Integer> entry : agentsValues.entrySet()) {
                messageContentBuilder.append(entry.getKey());
                messageContentBuilder.append(" ");
                messageContentBuilder.append(entry.getValue());
                messageContentBuilder.append(" ");
            }

            String messageContent = messageContentBuilder.toString();
            message.setContent(messageContent);

            myAgent.send(message);

            System.out.println(getAID().getLocalName() + " agent sent inform message with content = " + messageContent);
        }
    }

    public class CalculateAverageOneShotBehaviour extends OneShotBehaviour {

        @Override
        public void action() {
            int sumValues = 0;
            for (Integer value : agentsValues.values()) {
                sumValues += value;
            }

            double average = 1.0 * sumValues / numberOfAgents;

            System.out.println(getAID().getLocalName() + " agent calculated average = " + average);
        }
    }
}
