/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener.
 */

package mosaic;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import images.ImageUtils;

public class TileFactory {
	public final int tileWidth;
	public final int tileHeight;
	// TODO: you will NOT be keeping this array in your final code; 
	// see assignment description for details
	private final Map <Integer,ArrayList<BufferedImage>> hues = new HashMap<>();
	
	/**
	 * 
	 * @param colors the palette of RGB colors for this TileFactory
	 * @param tileWidth width (in pixels) for each tile
	 * @param tileHeight height (in pixels) for each tile
	 */
	public TileFactory(int[] colors, int tileWidth, int tileHeight) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		// TODO: when you replace the int[] hues, be sure to replace this code, as well
		for (int i=0;i<colors.length;i++) {
			hues.put(ImageUtils.hue(colors[i]),new ArrayList<BufferedImage>());
		}
	}
	
	/**
	 * Returns the distance between two hues on the circle [0,256).
	 * 
	 * @param hue1 
	 * @param hue2
	 * @return the distance between two hues.
	 */
	static int hueDistance(int hue1, int hue2) {
		if (Math.abs(hue1-hue2)<=128) 			
			return Math.abs(hue1-hue2);
		else 
			return 256-Math.abs(hue1-hue2);
	}
	
	/**
	 * Returns the closest hue from the fixed palette this TileFactory contains.
	 * @param hue
	 * @return the closest hue from the palette
	 */
	Integer closestHue(int hue) {
		int output=0;
		int distance=hueDistance(hue,hues.keySet().iterator().next());
		for (Integer key:hues.keySet()) {
			if(hueDistance(hue,key)<distance) {
				distance=hueDistance(hue,key);
				output=key;
				}
			}
		
		return output;
	}
		
	
	/**
	 * Adds an image to this TileFactory for later use.
	 * @param image the image to add
	 * @return the hue from the fixed palette this image is now associated with
	 */
	public int addImage(BufferedImage image) {
		int result =0;
		image = ImageUtils.resize(image, tileWidth, tileHeight);
		int avgHue = ImageUtils.averageHue(image);
		int Closest=closestHue(avgHue);
		for(Integer i:hues.keySet()) {
			if(i==Closest)
				hues.get(i).add(image);
				result=i;
		}
		// TODO: add the image to the appropriate place in your map from hues to lists of images
		// TODO
		return result;
	}
	
	/**
	 * Removes all images from this TileFactory associated with a particular hue.
	 * @param hue the hue to remove
	 * @return true if there are tiles associated with this hue, false otherwise
	 * @throws IllegalArgumentException if hue is not a key
	 */
	public boolean removeTiles(int hue) {
		boolean check=false;
		if(!hues.containsKey(hue)) 
			throw new IllegalArgumentException();
		
		for(Integer a:hues.keySet()) { 
				if(a==hue) {
					if(hues.get(a).isEmpty()) {
						break;
					 }
				else {
				ArrayList <BufferedImage> theOne=hues.get(a);
				hues.get(a).removeAll(theOne);
					}
				}
			check=true;
		}
		// TODO: remove the images for the specified hue and return them
		// TODO
		return check;
	}
	
	/**
	 * Returns the number of tiles associated with a specific hue
	 * @param hue the hue
	 * @return the number of tiles associated with this hue
	 * @throws IllegalArgumentException if hue is not a key
	 */
	public int getTileCount(int hue) {
		if(!hues.containsKey(hue))
			throw new IllegalArgumentException();
		int count=0;
		for(Integer a:hues.keySet()) {
			if(a==hue) 
				count=hues.get(a).size();
		}
		return count;
	}
	
	/**
	 * Returns the next tile from the list associated with the hue most closely matching the input hue.
	 * 
	 * The returned values should cycle through the list. Each time this method is called, the next 
	 * tile in the list will be returned; when the end of the list is reached, the cycle starts over.
	 *  
	 * @param hue the color to match
	 * @return a tile matching hue
	 */
	public BufferedImage getTile(int hue) {
		BufferedImage newImage=hues.get(closestHue(hue)).get(0);
		// TODO:  return an appropriate image from your map of hues to lists of images; 
		// see assignment description for details
		Collections.rotate(hues.get(closestHue(hue)),-1);
		return newImage;
	}
}
