/**
 * 
 */
package uk.avalokita.udptalk;

import java.net.*;
import java.util.concurrent.TimeUnit;

/**
 * @author greg
 *
 */
public class UdpClient implements Runnable {

	/**
	 * @param String
	 * @param port
	 */
	public UdpClient(String raddrString, int port) throws UnknownHostException {
		this.raddr = (Inet4Address)InetAddress.getByName(raddrString);
		this.port = port;
	}

	/**
	 * @param port
	 */
	public UdpClient(int port) throws UnknownHostException {
		this.raddr = (Inet4Address)InetAddress.getLocalHost();
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
        try {
	        datagramSocket = new DatagramSocket();
	        datagramSocket.connect(raddr, port);
	        // bufferIn = new byte[datagramSocket.getReceiveBufferSize()];
	        bufferOut = new byte[datagramSocket.getSendBufferSize()];
	        
	        // WIP
	        bufferOut = "Hello!".getBytes(/*StandardCharsets.UTF_8*/);
	        
	        while (true) {
	        	datagramPacket = new DatagramPacket(bufferOut, bufferOut.length);
	        	System.out.println("Invoking send()...");
	        	datagramSocket.send(datagramPacket);
   		     	System.out.println("Returned from send()");
       		 	TimeUnit.SECONDS.sleep(10);
        	}
	    } catch(Exception x) {
	    	System.out.println(x.getMessage());
	    	System.exit(0);
	    } finally {
	    	this.datagramSocket.close();
	    }  

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IllegalArgumentException{
		
		if (args.length != 2) {
			throw new IllegalArgumentException();
		}
		String raddrString = args[0];
		int port = Integer.parseInt(args[1]);
		try {
			UdpClient u = new UdpClient(raddrString, port);
			u.run();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * instance server port
	 */
	private final int port;

	
	/**
	 * instance server address
	 */
	private final Inet4Address raddr;
	
	/**
	 * instance DatagramSocket
	 */
	private DatagramSocket datagramSocket;
	
	/**
	 * instance DatagramPacket
	 */
	private DatagramPacket datagramPacket;
	
	/**
	 * instance buffer for receive
	 */
	private byte[] bufferIn;
	
	/**
	 * instance buffer for send
	 */
	private byte[] bufferOut;
	
}
