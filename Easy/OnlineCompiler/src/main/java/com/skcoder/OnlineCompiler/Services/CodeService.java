package com.skcoder.OnlineCompiler.Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import com.skcoder.OnlineCompiler.DTO.CodeDTo;
import com.skcoder.OnlineCompiler.FileServices.FileService;
import com.skcoder.OnlineCompiler.Interfaces.Operation;

public class CodeService {

	

	public String complete(CodeDTo codeDTo, int globalValue, Operation operation) throws InterruptedException {
	    FileService fileService = new FileService();
	    ArrayList<String> list = fileService.createFiles(codeDTo, globalValue);
	    CompletableFuture<String> compileFuture = new CompletableFuture<>();
	    
	    
	    //thread to avoid executing executable file before it is created
	    Thread compileThread = new Thread(() -> {  
	        String error = operation.Compile(list.get(0), list.get(1), list.get(2));
	        compileFuture.complete(error);
	    });

	    compileThread.start();
	    compileThread.join();

	    String compileError = compileFuture.join();
	    if (compileError!=null && !compileError.isEmpty()) {
	        System.out.println("Error: " + compileError);
	        //deleteing files after error
	        fileService.deleteFiles(list);
	        //returning error
	        return compileError;
	    }

	    //executing the executable file and making sure the reading dont start before the operation is completed
	    Thread executeThread = new Thread(() -> {
	        operation.Execute(list.get(2), list.get(3), list.get(1));
	    });
	    executeThread.start();
	    executeThread.join();

	


	//reading the output
	        String anString = fileService.readOutputFromFile(list.get(3));
	        
	        //deleting files after output
	        fileService.deleteFiles(list);
	        //returning output
	        return anString;
	 
	}
}
