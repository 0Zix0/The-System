package com.system.image;

import java.io.IOException;
import java.util.HashMap;
import java.util.zip.DataFormatException;

import com.system.xpreader.REXReader;
import com.system.xpreader.XPFile;

public class ImageCache {

	private static HashMap<String, XPFile> cache = new HashMap<>();
	
	public static XPFile get(String name) {
		if(cache.containsKey(name)) {
			System.out.println("GOT FROM CACHE");
			return cache.get(name);
		}
		try {
			XPFile file = REXReader.loadXP("res/sprites/xp/" + name + ".xp");
			cache.put(name, file);
			return file;
		} catch (IOException | DataFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
}
