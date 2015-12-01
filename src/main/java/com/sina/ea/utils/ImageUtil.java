package com.sina.ea.utils;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;
import com.sun.jimi.core.JimiWriter;
import com.sun.jimi.core.options.JPGOptions;

public class ImageUtil {
	
	public static void toJPG(File source, String dest, int quality) {

//		if (dest == null || dest.trim().equals(""))
//			dest = source;

		if (!dest.toLowerCase().trim().endsWith("png")) {
			dest += ".png";
			System.out.println("Overriding to JPG, output file: " + dest);
		}
		if (quality < 0 || quality > 100 || (quality + "") == null
				|| (quality + "").equals("")) {
			System.out.println("quality must between ’0’ and ’100’");
			System.out.println("set to DEFAULT value:’75’");
			quality = 75;

		}
		try {
			JPGOptions options = new JPGOptions();
			options.setQuality(quality);
			ImageProducer image = Jimi.getImageProducer(source.getAbsolutePath());
			JimiWriter writer = Jimi.createJimiWriter(dest);
			writer.setSource(image);
			// 加入属性设置，非必要
			// /*
			writer.setOptions(options);
			// */
			writer.putImage(dest);
		} catch (JimiException je) {
			System.err.println("Error: " + je);
		}
	}


	public static Image[] splitImage(Component component, Image img, int rows,
			int cols) {

		if (img == null)
			return null;
		Image[] result = new Image[rows * cols];
		int w = img.getWidth(component) / cols;
		int h = img.getHeight(component) / rows;
		System.out.println(w + " " + h);
		for (int i = 0; i < result.length; i++) {
			result[i] = component.createImage(w, h);
			Graphics g = result[i].getGraphics();
			g.translate((-i % cols) * w, (-i / cols) * h);
			g.drawImage(img, 0, 0, component);
		}
		return result;
	}

	public static void toTIF(Image img, String dest) throws JimiException {
		if (!dest.toLowerCase().trim().endsWith("tif")) {
			dest += ".tif";
			System.out.println("Overriding to TIF, output file: " + dest);
		}
		dest = dest.substring(0, dest.lastIndexOf(".")) + ".png";
		JimiWriter writer = Jimi.createJimiWriter(dest);
		writer.setSource(img);
		dest = dest.substring(0, dest.lastIndexOf(".")) + ".tif";
		writer.putImage(dest);
	}



	public static BufferedImage imageToBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			if (hasAlpha) {
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bimage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}

	private static boolean hasAlpha(Image image) {
		// If buffered image, the color model is readily available
		if (image instanceof BufferedImage) {
			BufferedImage bimage = (BufferedImage) image;
			return bimage.getColorModel().hasAlpha();
		}

		// Use a pixel grabber to retrieve the image's color model;
		// grabbing a single pixel is usually sufficient
		PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
		}

		// Get the image's color model
		ColorModel cm = pg.getColorModel();
		return cm.hasAlpha();
	}


	public static void toJPG(String source, String dest, int quality) {

		if (dest == null || dest.trim().equals(""))
			dest = source;

		if (!dest.toLowerCase().trim().endsWith("png")) {
			dest += ".png";
			System.out.println("Overriding to JPG, output file: " + dest);
		}
		if (quality < 0 || quality > 100 || (quality + "") == null
				|| (quality + "").equals("")) {
			System.out.println("quality must between ’0’ and ’100’");
			System.out.println("set to DEFAULT value:’75’");
			quality = 75;

		}
		try {
			JPGOptions options = new JPGOptions();
			options.setQuality(quality);
			ImageProducer image = Jimi.getImageProducer(source);
			JimiWriter writer = Jimi.createJimiWriter(dest);
			writer.setSource(image);
			// 加入属性设置，非必要
			// /*
			writer.setOptions(options);
			// */
			writer.putImage(dest);
		} catch (JimiException je) {
			System.err.println("Error: " + je);
		}
	}

	public static void main(String[] args) {
		// JFrame frame = new JFrame();
		// Container container = frame.getContentPane();
		// ImageIcon icon = new ImageIcon("/root/Desktop/bmsxml/bsmpic/1.jpg");
		// container.add(new JLabel(icon),BorderLayout.CENTER);
		// frame.setSize(300,300);
		// frame.validate();
		// frame.setVisible(true);
		//
		// Image[] result = splitImage(container,icon.getImage(),3,3);
		// for(int i = 0 ; i < result.length; i++){
		// File file = new File("/home/dx/images/" + i + ".jpg");
		// try {
		// file.createNewFile();
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		// try {
		// ImageIO.write(imageToBufferedImage(result[i]), "jpg", file);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// }
		//
	}

}
