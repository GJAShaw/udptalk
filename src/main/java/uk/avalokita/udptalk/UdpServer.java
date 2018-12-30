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
public class UdpServer implements Runnable {

	/**
	 * @param port
	 */
	public UdpServer(int port) {
		this.port = port;
	}

	/**
	 * @param
	 */
	public UdpServer() {
		this(DEFAULT_PORT);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
        try {
	        datagramSocket = new DatagramSocket(port, Inet4Address.getLocalHost());
	        bufferIn = new byte[datagramSocket.getReceiveBufferSize()];
	        // bufferOut = new byte[datagramSocket.getSendBufferSize()];
	        
	        while (true) {
	        	datagramPacket = new DatagramPacket(bufferIn, bufferIn.length);
	        	System.out.println("Invoking receive()...");
	        	datagramSocket.receive(datagramPacket);
   		     	System.out.println("Returned from receive()");
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
		
		if (args.length != 1) {
			throw new IllegalArgumentException();
		}
		int port = Integer.parseInt(args[0]);
		UdpServer u = new UdpServer(port);
		u.run();

	}
	
	/**
	 * instance port number
	 */
	private final int port;

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * class default port number
	 */
	public static final int DEFAULT_PORT = 9876;

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
