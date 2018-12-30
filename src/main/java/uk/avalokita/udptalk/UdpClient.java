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
	public UdpClient(String raddrString, int rport) throws UnknownHostException {
		this.raddr = (Inet4Address)InetAddress.getByName(raddrString);
		this.rport = rport;
	}

	/**
	 * @param port
	 */
	public UdpClient(int rport) throws UnknownHostException {
		this.raddr = (Inet4Address)InetAddress.getLocalHost();
		this.rport = rport;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
        try {
	        datagramSocket = new DatagramSocket();
	        datagramSocket.connect(raddr, rport);
	        bufferIn = new byte[datagramSocket.getReceiveBufferSize()];
	        datagramPacketIn = new DatagramPacket(bufferIn, bufferIn.length);
	        bufferOut = new byte[datagramSocket.getSendBufferSize()];

	        while (true) {
	        	// send message
	        	bufferOut = "Hello!".getBytes(/*StandardCharsets.UTF_8*/);
		        datagramPacketOut = new DatagramPacket(bufferOut, bufferOut.length);
	        	System.out.println("Invoking send()...");
	        	datagramSocket.send(datagramPacketOut);
       		 	
   		     	// await response
	        	datagramPacketIn = new DatagramPacket(bufferIn, bufferIn.length);
	        	System.out.println("Awaiting incoming packet...");
	        	datagramSocket.receive(datagramPacketIn); // blocking
	        	
	        	// do things with the response
	        	
	        	// Wait 10 seconds before chelping again
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
	private final int rport;
	
	/**
	 * instance server address
	 */
	private final Inet4Address raddr;
	
	/**
	 * instance DatagramSocket
	 */
	private DatagramSocket datagramSocket;
	
	/**
	 * instance DatagramPacket for receive
	 */
	private DatagramPacket datagramPacketIn;
	
	/**
	 * instance buffer for receive
	 */
	private byte[] bufferIn;
	
	/**
	 * instance DatagramPacket for send
	 */
	private DatagramPacket datagramPacketOut;
	
	/**
	 * instance buffer for send
	 */
	private byte[] bufferOut;
	
}
