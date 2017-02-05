import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.*;
import java.lang.Math;
import java.lang.String;


public class Aircraft extends Agent
{
 // Declare variable here so they will be visible in sub-classes.
 long startX = 80;
 long startZ = 0;
 long startHeight = 40;
 long speed = 2;
 long k = 0;
 int angle = 90;
 double i;
 String high = "high";
 String low = "low";
 long currentX,currentZ,currentHeight,nextX,nextZ,nextHeight,gap,time;
 Date original = new Date();

 protected void setup()
 {
  // Add *all* your behaviours here!
  addBehaviour(new ReceiveMyMessage());
 }

 class ReceiveMyMessage extends CyclicBehaviour
 {
  public void action()
  {    
	//Check the Queue Size to see how many messges we've got.
	Date current = new Date();
	int quesize = getCurQueueSize();

	for( ;quesize!= 0; quesize--){

    ACLMessage msgr = receive();
	ACLMessage msgs = new ACLMessage(ACLMessage.INFORM);
    AID sender = msgr.getSender();
    String name = sender.getName();
	gap = current.getTime()- original.getTime();
	time = gap/1000;
	i = angle * Math.PI/180;

	if( msgr.getPerformative() == ACLMessage.REQUEST )
		{
		if(msgr.getContent().compareTo(high) == 0)
		{
			startHeight += 10;
		}
		else if(msgr.getContent().compareTo(low) == 0)
		{
			startHeight -= 10;
		}
	}

	else if( msgr.getPerformative() == ACLMessage.QUERY_REF)
	{		
		currentX = time * speed * (long) (Math.cos(i)) + startX * (long) (Math.sin(i));
		currentZ = time * speed * (long) (Math.sin(i)) + startZ * (long) (Math.cos(i));
		currentHeight = startHeight;
		nextX = (time + 5) * speed * (long) (Math.cos(i)) + startX * (long) (Math.sin(i));
		nextZ = (time + 5) * speed * (long) (Math.sin(i)) + startZ * (long) (Math.cos(i));
		nextHeight = startHeight;
		msgs.setContent(currentX + "," + currentHeight + "," + currentZ + "?" + nextX + "," + nextHeight + "," + nextZ);
		msgs.addReceiver(new AID( name, AID.ISLOCALNAME));
		send(msgs);
		System.out.println(currentX + "," + currentHeight + "," + currentZ + "?" + nextX + "," + nextHeight + "," + nextZ);
	}

    }
  block();  
  }
 }
}