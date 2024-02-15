package com.skcoder.OnlineCompiler.Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.skcoder.OnlineCompiler.Interfaces.Operation;

public class CppServices implements Operation  {

	@Override
    public String Compile(String filename, String inputFile, String outputExe) {
         
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "g++ " + filename + " -o " + outputExe);
        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            InputStream errorStream = process.getErrorStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
            StringBuilder errors = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                errors.append(line).append("\n");
            }

            if (exitCode == 0) {
                System.out.println("Success");
                return null; 
            } else {
                System.out.println("Failure at compilation");
                return errors.toString(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Process error");
            return "Process error";
        }
    }

	@Override
	 public void Execute(String outputExe, String outputTxt, String inputFile) {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", outputExe + " < " + inputFile + " > " + outputTxt);
        String line = null;
        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Success");
            } else {
                System.out.println("Failure at execution");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Process error");
        }
    }

 
    



    }


