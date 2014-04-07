/**
 * @(#)RFIDAuth.java
 *
 *
 * @author 
 * @version 1.00 2014/4/6
 */
 
import com.phidgets.*;
import com.phidgets.event.*;

public class RFIDAuth extends PhidgetsClass {
	
	boolean authNeeded = true;
	final ApplicationComponents appComps = new ApplicationComponents();

	public void authenticateStart() {
    	boolean authStatus = false;
    	System.out.print("The authentication status is currently: " + authStatus + ".\n");
    	if(authStatus){
    		System.out.println("The applications is unlocked. Please wait...");
    		//TODO add comps
    		appComps.applicationStart();
    	} else {
    		//System.out.print("Please authenticate now by swiping one of the RFID tags allowed to unlock the program.\n");
    		try{
    		authenticateContinue();
    		}catch(Exception e){
    			System.out.println(e);
    		}
    	}

    }
	// Attaches phidget module and checks rfid tags for match in switch case array
    public void authenticateContinue() throws Exception {
    	RFIDPhidget rfid;
    	

		//System.out.println(Phidget.getLibraryVersion());

		rfid = new RFIDPhidget();
		rfid.addAttachListener(new AttachListener() {
			public void attached(AttachEvent ae)
			{
				try
				{
					((RFIDPhidget)ae.getSource()).setAntennaOn(true);
					((RFIDPhidget)ae.getSource()).setLEDOn(true);
				}
				catch (PhidgetException ex) { }
				//System.out.println("attachment of " + ae);
			}
		});
		rfid.addDetachListener(new DetachListener() {
			public void detached(DetachEvent ae) {
				//System.out.println("detachment of " + ae);
			}
		});
		rfid.addErrorListener(new ErrorListener() {
			public void error(ErrorEvent ee) {
				//System.out.println("error event for " + ee);
			}
		});
		rfid.addTagGainListener(new TagGainListener()
		{
			public void tagGained(TagGainEvent oe)
			{
				//System.out.println("\nTag Gained: " +oe.getValue());
				String tagValue = oe.getValue();
				switch(tagValue){
					case "1f00bdf5fa": System.out.println("\nHey, Alexis! Just a few moments.");
										authNeeded = false;
										appComps.applicationStart();
					break;
					case "1f00be2b67": System.out.println("\nWell, hello there, Aaron! Just a few moments.");
										authNeeded = false;
										appComps.applicationStart();
					break;
					default: 		   System.out.println("\nMmm... That tag does not seem to be authorized, please try again.");
										authNeeded = true;
					break;
				}
			}
		});
		rfid.addTagLossListener(new TagLossListener()
		{
			public void tagLost(TagLossEvent oe)
			{
				//System.out.println(oe);
			}
		});
		rfid.addOutputChangeListener(new OutputChangeListener()
		{
			public void outputChanged(OutputChangeEvent oe)
			{
				//System.out.println(oe);
			}
		});

		rfid.openAny();
		System.out.print("Waiting for RFID attachment... ");
		rfid.waitForAttachment(100000);
		System.out.print(" Found!\n");
		System.out.print("Please authenticate now by swiping one of the RFID tags allowed to unlock the program.\n");


		//Temporarily disabled for testing, may not need it anymore, though
		//System.out.println("Serial: " + rfid.getSerialNumber());
		//System.out.println("Outputs: " + rfid.getOutputCount());

		//System.out.print(" Press any key and hit enter to continue.");
		//System.in.read();
		while(authNeeded){
			Thread.sleep(1000);
			//System.out.print(authNeeded);
			//System.in.read();
		}
		//System.out.print(authNeeded);
		//System.out.print(" Closing...");
		//System.out.println(" Ok");
		rfid.setLEDOn(false);
		rfid.close();
		rfid = null;
		if (false) {
			System.out.println("wait for finalization...");
			System.gc();
		}
		return;
    }

}