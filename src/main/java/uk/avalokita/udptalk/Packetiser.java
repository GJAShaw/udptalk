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
	 * @param requestString
	 */
	public Packetiser(InetSocketAddress remote, String requestString) {
		this.remote = remote;
		this.requestString = requestString;
	}
	
	/**
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	String response() throws UnsupportedEncodingException {

		// create buffer for outgoing data
		requestBuffer = requestString.getBytes("UTF-8");

		// loop goes here - while outbuf contains unsent data:
		/*
		 * get next chunk of unsent data, enough to fill a datagram
		 * put it in a datagram
		 * try to send it (another internal loop will go here)
		 * if response arrives, put it in a byte[]
		 * append contents of byte[] to responseString
		 * return
		 */
		
		// **** TODO
		// Eventually, this will just return OutString;
		// For now, while developing, here is a stub...
		if (! requestString.isEmpty()) {
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
	private String requestString;
	
	/**
	 * 
	 */
	private byte[] requestBuffer;
	
	/**
	 * 
	 */
	private byte[] responseBuffer;
	
	/**
	 * 
	 */
	private String responseString;

	/**
	 * @return the responseString
	 */
	String getOutString() {
		return responseString;
	}

}
