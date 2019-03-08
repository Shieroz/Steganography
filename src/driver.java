import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class driver {

	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter source file path: ");
		String path = keyboard.nextLine();
		
		File f = null;
		try {
      f = new File(path);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		Steganography image = new Steganography(f);

		System.out.print("Enter 1 to encode, 2 to decode, 3 to exit: ");
		int code = keyboard.nextInt();
		
		while (code != 3) {
			if (code == 1) {
				System.out.print("Encode mode, enter number of bits: ");
				int bit = keyboard.nextInt();
				System.out.print("Enter string to encode: ");
				String message = keyboard.next();
				System.out.print("Enter destination path: ");
				String destination = keyboard.next();
				image.encodeMessage(bit, message, destination);
			
			} else if (code == 2) {
				System.out.print("Decode mode, enter number of bits: ");
				int bit = keyboard.nextInt();
				String message = image.decodeMessage(bit);
				System.out.print("The message is: " + message);
			}
			System.out.print("Enter 1 to encode, 2 to decode, 3 to exit: ");
			code = keyboard.nextInt();
		}
		keyboard.close();
	}
}
