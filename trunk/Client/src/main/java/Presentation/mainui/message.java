package Presentation.mainui;
/*
 * Author:jin
 * last-modify:12-16 by jin
 * description: thread for ther message box to refresh data
 * 
 * */
public class message extends Thread{
	MainFrame frame;
	boolean stop;
	MessageWindow w;
	public message(MessageWindow tip){
		stop=false;
		w=tip;	
	}
	
	
	public void run(){
		while(!stop){
			w.getNewMessage();
			try {
				this.sleep(5000);
		 	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 	}
		}	
	}
	
	public void startThread(){
		this.start();		
	}
	public void stopThead(){
		this.stop=true;
	}

}

