package in.ashokit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.UnlockAccessForm;
import in.ashokit.binding.UserRegForm;
import in.ashokit.entity.CityMasterEntity;
import in.ashokit.entity.CountryMasterEntity;
import in.ashokit.entity.StateMasterEntity;
import in.ashokit.entity.UserDtlsEntity;
import in.ashokit.repository.CityMasterRepo;
import in.ashokit.repository.CountryMasterRepo;
import in.ashokit.repository.StateMasterRepo;
import in.ashokit.repository.UserDtlsRepo;
import in.ashokit.utility.EmailUtils;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {
	@Autowired
	private CityMasterRepo cityRepo;
	@Autowired
	private StateMasterRepo stateRepo;
	@Autowired
	private CountryMasterRepo countryRepo;
	@Autowired
	private UserDtlsRepo userRepo;
    @Autowired
	private EmailUtils emailUtils;
	@Override
	public String login(LoginForm loginform) {
		UserDtlsEntity entity = userRepo.findByEmailAndPwd(loginform.getEmail(), loginform.getPwd());
		if (entity == null)
			return "Invalid Credentials";
		if (entity != null && entity.getAcc_status().equals("LOCKED"))
			return "Account is Locked";
		return null;
	}

	@Override
	public String emailCheck(String email) {
		UserDtlsEntity entity = userRepo.findByEmail(email);
		if (entity == null)
			return "UNIQUE";
		return "DUPLICATE";
	}

	@Override
	public Map<Integer, String> loadCountries() {
		List<CountryMasterEntity> countries=countryRepo.findAll();
		
		Map<Integer,String> countryMap=new HashMap();
		
		for(CountryMasterEntity country :countries) {
			countryMap.put(country.getCountryId(), country.getCountryName());
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStates(int countryId) {
		List<StateMasterEntity> states=stateRepo.findByCountryId(countryId);
		
		Map<Integer,String> statesMap=new HashMap();
		for(StateMasterEntity state:states) {
			statesMap.put(state.getStateId(), state.getStateName());
		}
		return statesMap;
	}

	@Override
	public Map<Integer, String> loadCities(int stateId) {
		List<CityMasterEntity> cities=cityRepo.findByStateId(stateId);
		Map<Integer,String> cityMap=new HashMap();
		for(CityMasterEntity city:cities) {
			cityMap.put(city.getStateId(), city.getCityName());
		}
		return cityMap;
	}

	@Override
	public String registerUser(UserRegForm user) {
     
		UserDtlsEntity entity=new UserDtlsEntity();
		
		BeanUtils.copyProperties(user, entity);
		entity.setAcc_status("LOCKED");
		
		entity.setPwd(generateRandomPwd());
		
		UserDtlsEntity savedEntity=userRepo.save(entity);
		
		
		String email=user.getEmail();
		String subject="User-Registration - Ashok IT";
		
		String fileName="UNLOCK_ACC_EMAIL_TEMPLATE.txt";
		String body=readMailBodyContent(fileName, entity);
		
		boolean isSent=emailUtils.sendEmail(email, subject, body);
		if(savedEntity.getUserId()!=null && isSent) {
			return "SUCCESS";
		}
		else {
			return "FAIL";
		}
			
	}

	@Override
	public String forgotpwd(String email) {

		UserDtlsEntity entity=userRepo.findByEmail(email);
		if(entity==null) {
			return "Invalid email Id";
		}
		
		String fileName="RECOVER_PASSWORD_EMAIL_BODY_TEMPLATE.txt";
		String body=readMailBodyContent(fileName, entity);
		String subject="Recover-Password -Ashok IT";
		
		boolean isSent=emailUtils.sendEmail(email, subject, body);
		if(isSent) {
			return "Password sent to Registered Email";
		}
		return "ERROR";
	}

	@Override
	public String unlockAccount(UnlockAccessForm unlockform) {
		
		if(!unlockform.getNewPwd().equals(unlockform.getCfgnewPwd())) {
			return "Password And Confirm Password Should be same";
		}
		
		UserDtlsEntity entity=userRepo.findByEmailAndPwd(unlockform.getEmail(),unlockform.getTempPwd());
		
		if(entity==null) {
			return "Incorrect Temp Password";
		}
		
		entity.setPwd(unlockform.getNewPwd());
		entity.setAcc_status("UNLOCKED");
		
		userRepo.save(entity);
		return "Account Unlocked";
	}
	
	private String generateRandomPwd() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 6;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}
	
	private String readMailBodyContent(String fileName,UserDtlsEntity entity) {
		
		String mailBody=null;
		try {
			StringBuffer sb=new StringBuffer();
			FileReader fr=new FileReader(fileName);
			BufferedReader br=new BufferedReader(fr);
			String line=br.readLine();
			
			while(line!=null){
			   sb.append(line);
			   line=br.readLine();
			}
			
			mailBody=sb.toString();
			
			mailBody=mailBody.replace("{FNAME}", entity.getFname());
			mailBody=mailBody.replace("{LNAME}", entity.getLname());
			mailBody=mailBody.replace("{TEMP-PWD}",entity.getPwd());
			mailBody=mailBody.replace("{EMAIL}", entity.getEmail());
			
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

}
