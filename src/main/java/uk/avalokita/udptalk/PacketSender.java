/**
 * 
 */
package uk.avalokita.udptalk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author greg
 *
 */
class PacketSender {

	
	/**
	 * @param local
	 * @param remote
	 * @param requestDatagramPacket
	 */
	public PacketSender(
			DatagramSocket local,
			InetSocketAddress remote,
			DatagramPacket requestDatagramPacket
		){
		this.local = local;
		this.remote = remote;
		this.requestDatagramPacket = requestDatagramPacket;
	}

	protected byte[] response() {
		
		byte[] responseBytes = new byte[0];
		int waitTime = 2; // need some cleverness to sanitise System property
		
		try {
			// Send the request
			local.send(requestDatagramPacket);
			
			// Wait for reply/replies
			boolean timedOut = false;
			while (!timedOut) {

				
				// get response
				// datagramPacketIn = new DatagramPacket(bufferIn, bufferIn.length);
				
				// local.receive(responseDatagramPacket); // blocking
				
				

					
				
				// ****TODO - remove backstop
				timedOut = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ****TODO get rid of this stub
		try {
			String junkString = "The cake is a lie";
			byte[] junkBuffer = junkString.getBytes("UTF-8");
			responseBytes = junkBuffer;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		return responseBytes;

	}
	
	
	/**
	 * 
	 */
	private DatagramSocket local;
	
	/**
	 * 
	 */
	private InetSocketAddress remote;

	/**
	 * 
	 */
	private DatagramPacket requestDatagramPacket;

}
