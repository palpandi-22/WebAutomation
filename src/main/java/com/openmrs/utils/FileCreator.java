package com.openmrs.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class FileCreator {
	
	
	private static final Logger logger = Logger.getLogger(FileCreator.class.getName());

	
	
	/**
	 * @author    - Palpandi
	 * @param     - fileExtension
	 * @return    - new file
	 * @throws    - IOException
	 * @impleNote - For to upload this temp file use it like "file.getAbsolutePath();"
	 */
	public static File create_New_Tempt_File(String fileExtension) throws IOException {

		File file = File.createTempFile("dummy", "." + fileExtension);
		return file;
	}
	 
	/**
	 * Creates a temporary image file with the specified image format.
	 * 
	 * @author   -  Palpandi
	 * @implNote -  To upload this temporary image file, use it like  :  "imageFile.getAbsolutePath();"
	 * @param    -  imageExtension the format of the image file to be created (e.g., "png", "jpg")
	 * @return   -  the created temporary image file, or null if an error occurs
	 */
	public static File Create_Temporary_Image_File(String imageExtension) {
		File tempFile = null;
		try {
			tempFile = File.createTempFile("temp_image", "." + imageExtension);

			BufferedImage image = createSmileyImage(457, 250);

			ImageIO.write(image, imageExtension, tempFile);
			logger.info("Temporary image file created at: " + tempFile.getAbsolutePath());
			// Print the path to the created temporary image file
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempFile;
	}
       
	 
	/**
	 * @author Palpandi
	 * @implNote Creates a smiley image with the specified width and height.
	 * @param width the width of the smiley image
	 * @param height the height of the smiley image
	 * @return a BufferedImage representing the smiley
	 */
	public static BufferedImage createSmileyImage(int width, int height) {
	    // Create a BufferedImage with the specified dimensions and type
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	    // Create a Graphics2D object from the BufferedImage
	    Graphics2D g2d = image.createGraphics();

	    try {
	        // Set background color
	        g2d.setColor(Color.WHITE);
	        g2d.fillRect(0, 0, width, height);

	        // Set face color
	        g2d.setColor(Color.YELLOW);
	        g2d.fillOval(50, 50, width - 100, height - 100);

	        // Draw eyes
	        g2d.setColor(Color.BLACK);
	        g2d.fillOval(100, 100, 40, 40);
	        g2d.fillOval(width - 140, 100, 40, 40);

	        // Draw mouth (smile)
	        g2d.drawArc(100, 120, width - 200, height - 200, 0, -180);
	    } finally {
	        // Dispose of the Graphics2D object to free up resources
	        g2d.dispose();
	    }

	    return image;
	}

	    
	    
}


