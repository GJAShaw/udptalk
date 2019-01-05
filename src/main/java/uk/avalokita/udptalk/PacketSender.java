/**
 * 
 */
package uk.avalokita.udptalk;

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
	public PacketSender(DatagramSocket local, InetSocketAddress remote, DatagramPacket requestDatagramPacket) {
		this.local = local;
		this.remote = remote;
		this.requestDatagramPacket = requestDatagramPacket;
	}

	protected DatagramPacket response() {
		
		DatagramPacket responseDatagramPacket = null;
		int retriesLeft = 3; // need some cleverness to sanitise System property, deal w non-positive values
		int waitTime = 2; // need some cleverness to sanitise System property
		while (true) {
			
			//local.send(requestDatagramPacket);
			
			// Thread to deal with timer? Some other library class, maybe
			
			// get response
			// datagramPacketIn = new DatagramPacket(bufferIn, bufferIn.length);
        	
			// local.receive(responseDatagramPacket); // blocking
        	
			// ****TODO get rid of this stub
			try {
				String junkString = "junk";
				byte[] junkBuffer = junkString.getBytes("UTF-8");
				responseDatagramPacket = new DatagramPacket(junkBuffer, junkBuffer.length);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return responseDatagramPacket;
		}
		
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
