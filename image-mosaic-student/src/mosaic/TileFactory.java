/*
 * Copyright 2017 Marc Liberatore.
 */

package mosaic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import images.ImageUtils;

public class TileFactory {
	public final int tileWidth;
	public final int tileHeight;
	// TODO: you will NOT be keeping this array in your final code; 
	// see assignment description for details
	//private final int[] hues;
	Map<Integer, List<BufferedImage>> map = new HashMap<>(); 
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
		for (int i=0; i<colors.length; i++) {
			map.put(ImageUtils.hue(colors[i]), new ArrayList<BufferedImage>());
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
		// TODO
	if (Math.abs(hue1-hue2)<=128) { //if shortest distance
			return Math.abs(hue1-hue2);
		}
	else {
		return 256-Math.abs(hue1-hue2);
	}
	}
		
		
	/**
	 * Returns the closest hue from the fixed palette this TileFactory contains.
	 * @param hue
	 * @return the closest hue from the palette
	 */
	Integer closestHue(int hue) {
		// TODO
		int closesthue=-1;
		int distance=-1;
		for(Integer k:map.keySet()) {
			if(distance==-1) {
				closesthue=k;
				distance=hueDistance(k,hue);
				continue;
			}
			if(hueDistance(k,hue)<distance){
				closesthue=k;
				distance=hueDistance(k,hue);
			}
		}
		return closesthue;
	}
	
	/**
	 * Adds an image to this TileFactory for later use.
	 * @param image the image to add
	 */
	public void addImage(BufferedImage image) {
		image = ImageUtils.resize(image, tileWidth, tileHeight);
				
		int avgHue = ImageUtils.averageHue(image);
		int key=closestHue(avgHue);
		for (Integer k:map.keySet()) {
		if(key==k) {
			map.get(k).add(image);
		}
		}
	}
		// TODO: add the image to the appropriate place in your map from hues to lists of images
	
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
		BufferedImage image = map.get(closestHue(hue)).get(0);
		// TODO:  return an appropriate image from your map of hues to lists of images; 
		// see assignment description for details

		Collections.rotate(map.get(closestHue(hue)),-1);
		return image;
	}
	
}
