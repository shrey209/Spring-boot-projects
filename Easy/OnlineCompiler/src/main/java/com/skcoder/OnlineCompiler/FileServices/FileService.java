package com.skcoder.OnlineCompiler.FileServices;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.javapoet.CodeBlock;

import com.skcoder.OnlineCompiler.DTO.CodeDTo;

public class FileService {

	
	 public ArrayList<String> createFiles(CodeDTo codeDto,int cnt) {
	        String filename=null;
	        if(codeDto.getType().equals("cpp"))
	         filename = "File_" + cnt + ".cpp";
	        else if (codeDto.getType().equals("c")) {
				filename= "File_" + cnt + ".c";
			}
	        else {
	        	filename= "File_" + cnt + ".py";
			}
	        
	        
	        String inputFile = "input_" + cnt + ".txt";
	        String outputexe = "output_exe" + cnt + ".exe";
	        String outputTxt = "output_txt" + cnt + ".txt";

	        try {
	           
	            File file1 = new File(  filename);
	            File file2 = new File( inputFile);
	            File file3 = new File(outputexe);
	            File file4 = new File( outputTxt);

	            if (file1.createNewFile() && file2.createNewFile() && file3.createNewFile() && file4.createNewFile()) {
	                System.out.println("Files created successfully :-"+file1);
	                try (FileWriter writer = new FileWriter(file1)) {
	                    writer.write(codeDto.getCode());
	                }

	          
	                try (FileWriter writer = new FileWriter(file2)) {
	                    writer.write(codeDto.getInput());
	                }
	            } else {
	                System.out.println("Some files already exist");
	            }

	           
	            ArrayList<String> fileList = new ArrayList<>();
	            fileList.add(filename);
	            fileList.add(inputFile);
	            fileList.add(outputexe);
	            fileList.add(outputTxt);

	            return fileList;
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("Error creating files: " + e.getMessage());
	            return null;
	        }
	    }
	
	 public void deleteFiles(ArrayList<String> arr) {
	        ArrayList<File> files = new ArrayList<>();

	        for (String filePath : arr) {
	            File file = new File(filePath);
	            files.add(file);
	        }

	        for (File file : files) {
	            if (file.exists()) {
	                if (file.delete()) {
	                    System.out.println("File deleted successfully: " + file);
	                } else {
	                    System.err.println("Failed to delete the file: " + file);
	                }
	            } else {
	                System.out.println("File not found: " + file);
	            }
	        }
	 }
	 
	 public String readOutputFromFile(String outputTxt) {
		    System.out.println("Reached the output file method");
		    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(outputTxt))) {
		        StringBuilder output = new StringBuilder();

		        String line;
		        while ((line = bufferedReader.readLine()) != null) {
		            output.append(line).append("\n");
		        }

		        return output.toString();
		    } catch (IOException e) {
		        System.out.println("Error reading output file");
		        e.printStackTrace();  
		        return "Error reading output file";
		    }
		    }
	 }
