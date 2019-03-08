import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Steganography {
	
	private BufferedImage sourceImage;

	public Steganography (File image) throws IOException {
		try {
			sourceImage = ImageIO.read(image);
		} catch(IOException e){
			System.out.println(e);
		}
	}

	public void encodeMessage (int bit, String message, String destination) {
		if (bit < 0 || bit > 8 || message == null || destination == null)
			throw new IllegalArgumentException("Invalid parameter(s)!");
		
		int width = sourceImage.getWidth();
		int height = sourceImage.getHeight();
		BufferedImage modified = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		cyclicIntList list = new cyclicIntList(message.length() * 8 / bit);
		
		for (int i = 0; i < message.length(); ++i) {
			for (int j = 8/bit-1; j >= 0; --j) {
				list.setC((message.charAt(i) >> (bit*j)) & (2^bit-1));
			}
		}

		
		int pixel, a, r, g, b;
		
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				//get pixel
				pixel = sourceImage.getRGB(j, i);
				
				//get alpha
		    a = (pixel>>24) & 0xff;
		    //get red
		    r = (pixel>>16) & 0xff;
		    //get green
		    g = (pixel>>8) & 0xff;
		    //get blue
		    b = pixel & 0xff;
		    
		    //modify pixel value
		    a = list.next() | (a & (0xff << bit));
		    r = list.next() | (r & (0xff << bit));
		    g = list.next() | (g & (0xff << bit));
		    b = list.next() | (b & (0xff << bit));
		    
		    //set the pixel value
		    pixel = (a<<24) | (r<<16) | (g<<8) | b;
		    modified.setRGB(j, i, pixel);
			}
		}
		
		//write image
    try {
      File f = new File(destination + "Output_" + bit + "bit.jpg");
      ImageIO.write(modified, "jpg", f);
    } catch(IOException e) {
      System.out.println(e);
    }
	}
	
	public String decodeMessage (int bit) {
		return null;
	}
}
