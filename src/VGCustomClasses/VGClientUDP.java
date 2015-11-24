package VGCustomClasses;

import java.io.*; 
import java.net.*;
import java.util.Arrays; 
import VGSingletons.*;

public class VGClientUDP {   
	String data;
	String data1;
	String data2;
	String monsterCount;
	int[] monsterPerCount = new int[8];
	String playerLevel;
	String playerCoin;
	String playerName;
	String playerIPAddress;
	int hitCount;
	int troopInPosition[][] = new int[10][10];
	
//public void cRun(String pName, String ipAddress,int levelNo, int[] monsterCountReceived, int goldCount) 
	public void cRun() throws IOException{
		int y;
		monsterPerCount = VGPlayerSingleton.getInstance().getPlayerTroops();
		String countPerTroop;
		 System.out.println("FROM CLIENT");
		 DatagramSocket clientSocket = new DatagramSocket(9876);   
		 InetAddress IPAddress = InetAddress.getByName(VGPlayerSingleton.getInstance().getIPaddress());  
		 byte[] sendData = new byte[1024];       
		 byte[] receiveData = new byte[1024];   
	     data = getTheDataFromTheClient();
	     sendData = data.getBytes();       
		 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);       
		 clientSocket.send(sendPacket);       
		 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);     
		 clientSocket.receive(receivePacket);       
	     String modifiedSentence = new String(receivePacket.getData());      
	     System.out.println("FROM SERVER:" + modifiedSentence);              
	     clientSocket.close();    
	    
	}
	public void cAttack() throws IOException{
		int y;
		String countPerTroop;
		 System.out.println("FROM CLIENT:(Attack)");
		 DatagramSocket clientSocket = new DatagramSocket();   
		 InetAddress IPAddress = InetAddress.getByName(VGPlayerSingleton.getInstance().getIPaddress());  
		 byte[] sendData = new byte[1024];       
		 byte[] receiveData = new byte[1024];   
	     data1 = "attack"+"_"+VGPlayerSingleton.getInstance().getIPaddress();
	     sendData = data1.getBytes();       
		 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);       
		 clientSocket.send(sendPacket);       
		 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);     
		 clientSocket.receive(receivePacket);       
	     String modifiedSentence = new String(receivePacket.getData());      
	     System.out.println("FROM SERVER:(Attack)" + modifiedSentence);              
	     clientSocket.close(); 
	}
/*	public void sendHitCount() throws IOException{
		
		hitCount = VGPlayerSingleton.getInstance().getHitCount();
	//	String countPerTroop;
		 System.out.println("FROM CLIENT");
		 DatagramSocket clientSocket = new DatagramSocket();   
		 InetAddress IPAddress = InetAddress.getByName(VGPlayerSingleton.getInstance().getIPaddress());  
		 byte[] sendData = new byte[1024];       
		 byte[] receiveData = new byte[1024];   
	     data2 = Integer.toString(VGPlayerSingleton.getInstance().getHitCount());
	     sendData = data2.getBytes();       
		 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9878);       
		 clientSocket.send(sendPacket);       
		 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);     
		 clientSocket.receive(receivePacket);       
	     String modifiedSentence = new String(receivePacket.getData());      
	     System.out.println("FROM SERVER(hit count):" + modifiedSentence);              
	     clientSocket.close(); 
	}*/
	
	
	
	public String getTheDataFromTheClient()
	{
		String sentence = "";
		 monsterPerCount = VGPlayerSingleton.getInstance().getPlayerTroops();
	     playerName = VGPlayerSingleton.getInstance().getPlayerName();
	     playerIPAddress = VGPlayerSingleton.getInstance().getIPaddress();
		 playerLevel = Integer.toString(VGPlayerSingleton.getInstance().getLevel());
		// monsterCount = Arrays.toString(monsterPerCount);
		 playerCoin = Integer.toString(VGPlayerSingleton.getInstance().getGoldCoins());
		 troopInPosition = VGGameSingleton.getInstance().getTroopInPosition();
		 
		 String troopData="";
		 for(int m=0; m<8; m++)
		 {
			 troopData = troopData + monsterPerCount[m]+",";
		 }
		 
		//PASS THE DATA TO THE SERVER - THE SEQUENCE IS THIS - pName, levelNo, int monsterCount, int goldCount, int[][] defenderTroop
		 sentence = "position"+"_"+playerName+"_"+playerIPAddress+"_"+playerLevel+"_"+troopData+"_"+playerCoin;
		 
		 String sentence1 = "_";
		 String separator = "";
		 
		 for(int i=0; i<10; i++){
			// sentence1 = sentence1+"/";
			 for(int j=0; j<10; j++){
				   sentence1 = sentence1 +(Integer.toString(troopInPosition[i][j]))+",";
			 }
			 sentence1 = sentence1+"/";
		 }
		 
		 sentence = sentence + sentence1;
		 
		 return sentence;
		 
	}
 } 