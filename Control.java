import jade.lang.acl.ACLMessage;
import jade.core.behaviours.*;
import jade.core.AID;
import jade.core.Agent;

import jade.lang.acl.MessageTemplate;


public class Control extends Agent {

protected void setup() {


addBehaviour(new TickerBehaviour(this, 5000) {
protected void onTick() {


ACLMessage msg = new ACLMessage(ACLMessage.QUERY_REF);
AID plane = new AID("A001", AID.ISLOCALNAME);
msg.addReceiver(plane);
send(msg);

}
});
addBehaviour(new TickerBehaviour(this, 30000) {
protected void onTick() {


ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
AID plane = new AID("a001", AID.ISLOCALNAME);
msg.addReceiver(plane);
msg.setContent("high");
send(msg);

}
});

}
}







