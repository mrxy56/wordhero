package com.example.my_first_game;

class MyThread extends Thread implements Runnable{
	  MySQLiteHelper myHelper;
	  public MyThread(){
	  }
	  public MyThread(MySQLiteHelper myHelper){
		  myHelper=this.myHelper;
	  }
	  public void run(){
		   String [] Array=new String[20];
	       for(int i=0;i<20;i++)
	    	   Array[i]=MainActivity.strArray[i];
	       try {
			   Thread.sleep(100);
		   } catch (InterruptedException e1){
			 e1.printStackTrace();
		   }
	       for(int i=0;i<20;i++){
	            GameLayer.label.setString(Array[i]);
	    	    try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		   }
	   }
}