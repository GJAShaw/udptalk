/**
 * 
 */
package uk.avalokita.udptalk;

import java.net.*;
// import java.util.concurrent.TimeUnit;

/**
 * @author greg
 *
 */
public class UdpServer implements Runnable {

	/**
	 * @param port
	 */
	public UdpServer(int lport) {
		this.lport = lport;
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
	        datagramSocket = new DatagramSocket(lport, Inet4Address.getLocalHost());
	        // no connect() - this is a server
	        bufferIn = new byte[datagramSocket.getReceiveBufferSize()];
	        bufferOut = new byte[datagramSocket.getSendBufferSize()];
        	
	        while (true) {
	        	// wait for an incoming message    	
	        	datagramPacketIn = new DatagramPacket(bufferIn, bufferIn.length);
	        	// System.out.println("Awaiting incoming packet...");
	        	datagramSocket.receive(datagramPacketIn); // blocking
	        	
	        	// get the remote address and port
	        	setRaddr((Inet4Address)datagramPacketIn.getAddress());
	        	setRport(datagramPacketIn.getPort());
	        	
	        	// do other things with the message data... (would go here)
	        	
	        	
	        	// reply
	        	textOut = "Go away.";
		        bufferOut = textOut.getBytes("UTF-8");
	        	datagramPacketOut = new DatagramPacket(
	        			bufferOut, bufferOut.length, getRaddr(), getRport()
	        	);
	        	// System.out.println("Replying...");
	        	datagramSocket.send(datagramPacketOut);
	        	
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
	 * class default lport number
	 */
	public static final int DEFAULT_PORT = 9876;

	/**
	 * instance local port
	 */
	private final int lport;

	/**
	 * @return the local port
	 */
	public int getPort() {
		return lport;
	}
	
	/**
	 * instance remote port
	 */
	private int rport;
	
	/**
	 * @return the rport
	 */
	public int getRport() {
		return rport;
	}

	/**
	 * @param rport the rport to set
	 */
	private void setRport(int rport) {
		this.rport = rport;
	}

	/**
	 * instance remote address
	 */
	private Inet4Address raddr;
	
	/**
	 * @return the raddr
	 */
	public Inet4Address getRaddr() {
		return raddr;
	}

	/**
	 * @param raddr the raddr to set
	 */
	private void setRaddr(Inet4Address raddr) {
		this.raddr = raddr;
	}

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
	 * instance String for receive
	 */
	private String textIn;
	
	/**
	 * instance DatagramPacket for send
	 */
	private DatagramPacket datagramPacketOut;
	
	/**
	 * instance buffer for send
	 */
	private byte[] bufferOut;
	
	/**
	 * instance String for send
	 */
	private String textOut;
	
}
