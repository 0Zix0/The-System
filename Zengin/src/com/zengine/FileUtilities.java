package com.zengine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

public class FileUtilities {

	private FileUtilities() {}

	public static String loadAsString(String file) {
		try {
			return Files.readAllLines(Paths.get(file)).stream().collect(Collectors.joining("\n"));
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
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
		try {
			Files.write(Paths.get(file), bytes, StandardOpenOption.WRITE);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
