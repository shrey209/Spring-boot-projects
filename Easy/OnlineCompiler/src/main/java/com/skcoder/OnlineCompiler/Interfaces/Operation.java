package com.skcoder.OnlineCompiler.Interfaces;

import com.skcoder.OnlineCompiler.DTO.CodeDTo;

public interface Operation {

	String Compile(String Filename,String InputFile,String OutputExe);
	void Execute(String OutputExe,String OutputTxt,String inputFile);
	
}
