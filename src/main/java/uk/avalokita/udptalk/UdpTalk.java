/**
 * 
 */
package uk.avalokita.udptalk;

/**
 * @author greg
 *
 */
import java.io.Console;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UdpTalk {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Argument handler - very basic
		if (args.length != 2) {
			System.out.println("****TODO help text goes here");
			System.exit(0);
		}

		// Open local socket, 'connect' to remote (no actual connection in UDP)
		DatagramSocket local = null;
		try {
			local = new DatagramSocket();
			local.connect(
					(Inet4Address)InetAddress.getByName(args[0]),
					Integer.parseInt(args[1])
			);
		} catch (SocketException e) {
			System.out.println("Failed to create local socket");
			e.printStackTrace();
			// no local.close() - if we got here, no object was created
			System.exit(1);
		} catch (UnknownHostException e) {
			System.out.println("Invalid IP address or unknown hostname: " + args[0]);
			e.printStackTrace();
			local.close();
			System.exit(1);
		} catch (NumberFormatException e) {
			System.out.println("Invalid port: " + args[1]);
			e.printStackTrace();
			local.close();
			System.exit(1);
		} catch (IllegalArgumentException e) {
			System.out.println("IP address is null, or the port is out of range");
			e.printStackTrace();
			local.close();
			System.exit(1);
		} catch (SecurityException e) {
			System.out.println("Connection not permitted to remote socket");
			e.printStackTrace();
			local.close();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			local.close();
			System.exit(1);
		}
		
		// Strings to hold I/O
		String sendString;
		String receiveString;
		
		// Create a Scanner, to read from stdin (console or file)
		Scanner scanner = new Scanner(System.in);
		
		// See if stdin is from console or elsewhere - controls later echoing
		Console console = System.console();
		
		// Eternal loop, till user enters 'exit'
		System.out.println(""); // empty line, to mark the beginning of output
		boolean gotExit = false;
		while (! gotExit) {
			
			try {
				sendString = scanner.nextLine();
				if (! sendString.equalsIgnoreCase("exit")) {
					if (console == null) { // stdin is not the console
						System.out.println(sendString);
					}
					// send sendString will go here...
					/*
					 * ... wait for reply...
					 */
					// populate receiveString with reply. For now
					if (! sendString.isEmpty()) {
						receiveString = "Yeah, whatever";
					} else {
						receiveString = "Eh?";
					}
					System.out.println(receiveString + "\n");
				} else {
					gotExit = true;
				}
			} catch (NoSuchElementException e) {
				// stdin ended without an 'endl' - that's OK
				gotExit = true;
			} catch (Exception e) {
				e.printStackTrace();
				gotExit = true;
			}
		
		}

	// release resources
	scanner.close();
	local.close();

	}
	
}
