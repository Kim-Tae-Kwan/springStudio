package com.ktk.springStudio.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

public class JavaFileUtil {
	
	private Path root;
	
	public JavaFileUtil() {
		root = Paths.get("temp/java");
		try {
			Files.createDirectories(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	private void init() {
		root = Paths.get("temp/java");
		try {
			Files.createDirectories(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save(String fileName, String content) {
		Path filePath = this.root.resolve(fileName);
		
		try(BufferedWriter bout = new BufferedWriter(new FileWriter(filePath.toFile()));) {
			bout.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
