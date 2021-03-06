package in.ashokit.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.UserRegForm;
import in.ashokit.service.UserMgmtService;

@RestController
public class RegistrationRestController {
	@Autowired
	private UserMgmtService service;

	@GetMapping("/email/{email}")
	public String emailCheck(@PathVariable("email") String email) {
		return service.emailCheck(email);
	}

	@GetMapping("/countries")
	public Map<Integer, String> getCountries() {
		return service.loadCountries();
	}

	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getStates(@PathVariable("countryId") Integer countryId) {
		return service.loadStates(countryId);
	}

	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable("stateId") Integer stateId) {
		return service.loadCities(stateId);
	}

	@PostMapping("/user")
	public String register(@RequestBody UserRegForm form) {
		return service.registerUser(form);
	}

}
