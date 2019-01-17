/**
 * 
 */
package uk.avalokita.udptalk;

/**
 * @author greg
 *
 */
public class Crap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// byte[] crap = new byte[-9];

	}

}

/**
 * @throws IllegalArgumentException
 * @throws NumberFormatException
 * @throws NegativeArraySizeException
 */
/*
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
*/
