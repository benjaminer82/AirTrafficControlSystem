import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

public class Jack extends Agent
{
 // Declare variable here so they will be visible in sub-classes.
 String mesgToBeSent;
 protected void setup()
 {
  // Process your arguements here!!!!!
  Object[] args = getArguments();
  if (args != null)
  {
   mesgToBeSent = (String) args[0];
  }
  // Add *all* your behaviours here!
  addBehaviour(new SendMyMessage());
 }
 class SendMyMessage extends SimpleBehaviour
 {
  private boolean finished = false;
  public void action()
  {
   ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
   msg.setContent("HAHA");
   msg.setLanguage("HOHO");
   msg.setOntology("HIHI");
   msg.addReceiver(new AID("Server", AID.ISLOCALNAME));
   send(msg);
   finished = true;
  }
  public boolean done()
  {
   return finished;
  }
 }
}