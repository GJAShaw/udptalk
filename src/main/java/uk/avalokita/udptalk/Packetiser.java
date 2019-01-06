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
class Packetiser {
	
	/**
	 * @param remote
	 * @param requestString
	 */
	public Packetiser(DatagramSocket local, InetSocketAddress remote, String requestString) {
		this.local = local;
		this.remote = remote;
		this.requestString = requestString;
	}
	
	/**
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	String response() throws UnsupportedEncodingException {

		byte[] requestBuffer = requestString.getBytes("UTF-8");
		int countBytesRemaining = requestBuffer.length;

		int countBytesToPacketise = 0;
		int offset = 0;
		DatagramPacket requestDatagramPacket = null;
		byte[] responseBuffer = new byte[0]; // not null - mut
		byte[] responseBytes = null;
		DatagramPacket responseDatagramPacket = null;
		String responseString = null;
		byte[] tempBytes = null;
		
		// ****TODO fix this hardcoding blag later
		// Have some cleverness to get/reset System Property
		final int DGRAM_DATALENGTH = 512; // bytes
		
		do {
			// Make a DatagramPacket for the request
			countBytesToPacketise = (
				countBytesRemaining >= DGRAM_DATALENGTH ?
				DGRAM_DATALENGTH :
				countBytesRemaining
			);
			requestDatagramPacket = new DatagramPacket(
				requestBuffer,
				offset,
				countBytesToPacketise,
				remote
			);
			offset += countBytesToPacketise;
			countBytesRemaining -= countBytesToPacketise;
			
			// Hand requestDatagramPacket over to a PacketSender, wait for response...
			responseDatagramPacket = new PacketSender(local, remote, requestDatagramPacket).response();
			
			// Update responseBuffer
			responseBytes = responseDatagramPacket.getData();
			tempBytes = new byte[responseBuffer.length + responseBytes.length];
			System.arraycopy(responseBuffer, 0, tempBytes, 0, responseBuffer.length);
			System.arraycopy(responseBytes, 0, tempBytes, responseBuffer.length, responseBytes.length);
			responseBuffer = tempBytes;
			
		} while (countBytesRemaining > 0);

		responseString = new String(responseBuffer, 0 /*offset*/, responseBuffer.length, "UTF-8");	
		return responseString;

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
	private String requestString;

}
