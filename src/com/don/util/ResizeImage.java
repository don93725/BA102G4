package com.don.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ResizeImage {
	 
	 static public byte[] resizeImageAsJPG(byte[] pImageData, int pMaxWidth)
	   throws IOException {
	 
	  // Create an ImageIcon from the input image byte[]
	  ImageIcon imageIcon = new ImageIcon(pImageData);
	  int width = imageIcon.getIconWidth();
	  int height = imageIcon.getIconHeight();
	 
	  // If the image is larger than the max width, we need to resize it
	  if (pMaxWidth > 0 && width > pMaxWidth) {
	   // Determine the shrink ratio
	   double ratio = (double) pMaxWidth / imageIcon.getIconWidth();
	   height = (int) (imageIcon.getIconHeight() * ratio);
	   width = pMaxWidth;
	  }
	 
	  // Create a new empty image buffer to "draw" the resized image into
	  BufferedImage bufferedResizedImage = new BufferedImage(width, height,BufferedImage.SCALE_SMOOTH);
	  // Create a Graphics object to do the "drawing"
	  Graphics2D g2d = bufferedResizedImage.createGraphics();
	  g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	  // Draw the resized image
	  g2d.drawImage(imageIcon.getImage(), 0, 0, width, height, null);
	  g2d.dispose();
	  // Now our buffered image is ready
	  // Encode it as a JPEG
	  ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
	  JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(encoderOutputStream);
	  encoder.encode(bufferedResizedImage);
	  byte[] resizedImageByteArray = encoderOutputStream.toByteArray();
	  return resizedImageByteArray;
	 }
	}