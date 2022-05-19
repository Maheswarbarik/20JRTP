package in.ashokit.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.UnlockAccessForm;
import in.ashokit.service.UserMgmtService;

@RestController
public class UnlockAccessController {

	@Autowired
	private UserMgmtService service;
	
	@PostMapping("/unlock")
	public String unlockAccount(@RequestBody UnlockAccessForm form) {
		return service.unlockAccount(form) ;
	}
	
	
	
	
}
