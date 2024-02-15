package com.skcoder.OnlineCompiler.Controllers;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skcoder.OnlineCompiler.DTO.CodeDTo;
import com.skcoder.OnlineCompiler.Interfaces.Operation;
import com.skcoder.OnlineCompiler.Services.CodeService;
import com.skcoder.OnlineCompiler.Services.CppServices;
import com.skcoder.OnlineCompiler.Services.Cservices;




@RestController
public class RunController {
	private int globalValue = 0;
	
 @PostMapping("/run")
String runmethodString( @RequestBody CodeDTo codeDTo) throws InterruptedException {
CodeService services=new CodeService();
Operation operation;
if(codeDTo.getType().equals("cpp")) {
	operation=new CppServices();
}
else {
	operation=new Cservices();
}
globalValue++;
return services.complete(codeDTo,globalValue,operation);


}
	
	
}
