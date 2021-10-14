import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Starter {

	private static Map<Character, String> murrayCode = new HashMap<>();
	private static final int WORD_LENGTH = 5;

	public static void main(String[] args) {
		initMurrayMap();

		String plaintext = "ABCDE";
		String key = "0101010010010101000101010";
		String pEncoded = encodePlaintext(plaintext);
		String xored = xor(pEncoded, key); 
		
		System.out.println("Plaintext: " + plaintext);
		System.out.println("pEn: " + pEncoded);
		System.out.println("key: " + key);
		System.out.println("----------------------");
		System.out.println("xor: " + xored);
		System.out.println("xor(decoded): " + decodeCiphertext(xored));

	}

	private static String encodePlaintext(String p) {
		Objects.requireNonNull(p, "Plantext must not be null at this point.");
		StringBuilder sb = new StringBuilder();
		p = p.toUpperCase();
		for (int i = 0; i < p.length(); ++i) {
			sb.append(encode(p.charAt(i)));
		}

		return sb.toString();

	}

	private static String decodeCiphertext(String c) {
		Objects.requireNonNull(c, "Ciphertext must not be null at this point.");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < c.length(); i += WORD_LENGTH) {
			sb.append(decode(c.substring(i, i + WORD_LENGTH)));
		}
		return sb.toString();
	}

	private static String xor(String a, String b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length(); ++i) {
			sb.append(xorBitwise(a.charAt(i), b.charAt(i)));
		}
		return sb.toString();
	}

	private static String encode(char key) {
		String result = murrayCode.get(key);
		if (result == null) {
			System.out.println(String.format("Key '%s' is not mapped results in null", key));
		}

		return result;
	}

	private static char decode(String value) {
		for (Map.Entry<Character, String> e : murrayCode.entrySet()) {
			if (e.getValue().equals(value)) {
				return e.getKey();
			}
		}
		throw new IllegalArgumentException(String.format("Value '%s' is not mapped", value));
	}

	private static char xorBitwise(char a, char b) {
		if (a == ' ' || b == ' ') {
			return ' ';
		}
		return a == b ? '0' : '1';
	}

	private static void initMurrayMap() {
		// Letters
		murrayCode.putIfAbsent('A', "00011");
		murrayCode.putIfAbsent('B', "11001");
		murrayCode.putIfAbsent('C', "01110");
		murrayCode.putIfAbsent('D', "01001");
		murrayCode.putIfAbsent('E', "00001");
		murrayCode.putIfAbsent('F', "01101");
		murrayCode.putIfAbsent('G', "11010");
		murrayCode.putIfAbsent('H', "10100");
		murrayCode.putIfAbsent('I', "00110");
		murrayCode.putIfAbsent('J', "01011");
		murrayCode.putIfAbsent('K', "01111");
		murrayCode.putIfAbsent('L', "10010");
		murrayCode.putIfAbsent('M', "11100");
		murrayCode.putIfAbsent('N', "01100");
		murrayCode.putIfAbsent('O', "11000");
		murrayCode.putIfAbsent('P', "10110");
		murrayCode.putIfAbsent('Q', "10111");
		murrayCode.putIfAbsent('R', "01010");
		murrayCode.putIfAbsent('S', "00101");
		murrayCode.putIfAbsent('T', "10000");
		murrayCode.putIfAbsent('U', "00111");
		murrayCode.putIfAbsent('V', "11110");
		murrayCode.putIfAbsent('W', "10011");
		murrayCode.putIfAbsent('X', "11101");
		murrayCode.putIfAbsent('Y', "10101");
		murrayCode.putIfAbsent('Z', "10001");
		
		// Operations
		murrayCode.putIfAbsent(' ', "00100");
		
	}

}
