package com.zengine;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtilities {

	private FileUtilities() {}
	
	public static String loadAsString(String file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String buffer = "";
			while((buffer = reader.readLine()) != null) {
				result.append(buffer + "\n");
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("Error while opening file: " + file);
		}
		return result.toString();
	}
	
	public static byte[] readFile(String file) {
		try {
			return Files.readAllBytes(Paths.get(file));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void writeBytesToFile(String file, byte[] bytes) {
		FileOutputStream os;
		try {
			os = new FileOutputStream(file);
			os.write(bytes);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
