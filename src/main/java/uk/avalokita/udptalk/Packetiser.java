/**
 * 
 */
package uk.avalokita.udptalk;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

/**
 * @author greg
 *
 */
class Packetiser {
	
	/**
	 * @param remote
	 * @param inString
	 */
	public Packetiser(InetSocketAddress remote, String inString) {
		this.remote = remote;
		this.inString = inString;
	}
	
	/**
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	String response() throws UnsupportedEncodingException {

		// create buffer for outgoing data
		outBuf = inString.getBytes("UTF-8");

		// loop goes here - while outbuf contains unsent data:
		/*
		 * get next chunk of unsent data, enough to fill a datagram
		 * put it in a datagram
		 * try to send it (another internal loop will go here)
		 * if response arrives, put it in a byte[]
		 * append contents of byte[] to outString
		 * return
		 */
		
		// **** TODO
		// Eventually, this will just return OutString;
		// For now, while developing, here is a stub...
		if (! inString.isEmpty()) {
			return "Yeah, whatever";
		} else {
			return "Eh?";
		}
	}


	/**
	 * 
	 */
	private InetSocketAddress remote;

	/**
	 * 
	 */
	private String inString;
	
	/**
	 * 
	 */
	private byte[] outBuf;
	
	/**
	 * 
	 */
	private byte[] inBuf;
	
	/**
	 * 
	 */
	private String outString;

	/**
	 * @return the outString
	 */
	String getOutString() {
		return outString;
	}

}
