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
	 * 
	 */
	public static final int BUFFER_LENGTH_DEFAULT = 2048; // bytes
	
	
	/**
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	String response() throws UnsupportedEncodingException {

		// create buffer for outgoing data
		outBuf = inString.getBytes("UTF-8");
		// call a Packetiser
		// outString = new Packetiser(remote, outBuf).response();

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

	
	// createInBuf is redundant. 
	/**
	 * @throws IllegalArgumentException
	 * @throws NumberFormatException
	 * @throws NegativeArraySizeException
	 */
	private void createInBuf()
			throws IllegalArgumentException,
			NumberFormatException,
			NegativeArraySizeException {
		
		String s = System.getProperty(
			"buffer.length", Integer.toString(BUFFER_LENGTH_DEFAULT)
		);
		if (s.length() == 0) {
			s = Integer.toString(BUFFER_LENGTH_DEFAULT);
		}
		try {
			inBuf = new byte[Integer.parseInt(s)];
			// outBuf = new byte[Integer.parseInt(s)];
			if (inBuf.length == 0) {
				throw new IllegalArgumentException("Zero buffer.length");
			}
		} catch (NumberFormatException e) {
			System.out.println("Non-numeric buffer.length " + s);
			throw e;
		} catch (NegativeArraySizeException e) {
			System.out.println("Negative buffer.length " + s);
			throw e;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			throw e;
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
	 * @return the inString
	 */
	String getInString() {
		return inString;
	}

	/**
	 * @return the outString
	 */
	String getOutString() {
		return outString;
	}

}
