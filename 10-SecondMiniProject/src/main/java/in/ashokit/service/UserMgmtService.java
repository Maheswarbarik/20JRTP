package in.ashokit.service;

import java.util.Map;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockAccessForm;
import in.ashokit.binding.UserRegForm;

public interface UserMgmtService {

	public String login(LoginForm loginform);

	public String emailCheck(String email);

	public Map<Integer, String> loadCountries();

	public Map<Integer, String> loadStates(int countryId);

	public Map<Integer, String> loadCities(int stateId);

	public String registerUser(UserRegForm user);

	public String forgotpwd(String email);

	public String unlockAccount(UnlockAccessForm unlockform);

}
