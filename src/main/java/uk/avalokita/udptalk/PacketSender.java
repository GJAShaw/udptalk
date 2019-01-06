/**
 * 
 */
package uk.avalokita.udptalk;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

/**
 * @author greg
 *
 */
class PacketSender {

	protected static byte[] response(DatagramSocket local, DatagramPacket requestDatagramPacket) {
		
		byte[] responseBuffer = new byte[0]; // accumulated response, from all received packets
		
		try {
			
			byte[] responseBytes = new byte[local.getReceiveBufferSize()];
			DatagramPacket responseDatagramPacket = new DatagramPacket(responseBytes, responseBytes.length);
			byte[] tempBytes = null;
			int timeout = 100; // milliseconds ****TODO get System property
			
			// Send the request
			local.send(requestDatagramPacket);
			
			// Wait for reply/replies
			local.setSoTimeout(timeout);
			while (true /* allow timeout exception to break from loop */) {

				local.receive(responseDatagramPacket); // blocking, for timeout or till get data
				
				responseBytes = responseDatagramPacket.getData();
				tempBytes = new byte[responseBuffer.length + responseBytes.length];
				System.arraycopy(responseBuffer, 0, tempBytes, 0, responseBuffer.length);
				System.arraycopy(responseBytes, 0, tempBytes, responseBuffer.length, responseBytes.length);
				responseBuffer = tempBytes;

			}
			
		} catch (SocketTimeoutException e) {
			if (responseBuffer.length == 0) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block - needs much more granularity
			// If timeout, check for any received data - if got some, then assume OK
			e.printStackTrace();
		}
				
		return responseBuffer;

	}

}
