/**
 * 
 */
package uk.avalokita.udptalk;

import java.net.InetSocketAddress;

/**
 * @author greg
 *
 */
class Bufferiser {
	
	/**
	 * @param remote
	 * @param inString
	 */
	public Bufferiser(InetSocketAddress remote, String inString) {
		super();
		this.remote = remote;
		this.inString = inString;
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
	 * @return the inString
	 */
	String getInString() {
		return inString;
	}

	/**
	 * @return the outBuf
	 */
	byte[] getOutBuf() {
		return outBuf;
	}

	/**
	 * @return the inBuf
	 */
	byte[] getInBuf() {
		return inBuf;
	}

	/**
	 * @return the outString
	 */
	String getOutString() {
		return outString;
	}

	/**
	 * @return
	 */
	String response() {
		// **** TODO
		// Eventually, this will just return getOutString();
		// For now, while developing, here is a stub...
		if (! inString.isEmpty()) {
			return "Yeah, whatever";
		} else {
			return "Eh?";
		}
	}

	
}
