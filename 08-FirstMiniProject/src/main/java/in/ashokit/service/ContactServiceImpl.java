package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Contact;
import in.ashokit.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository repo;
	
	@Override
	public String upsert(Contact contact) {
		Contact save=repo.save(contact);
		return "SUCCESS";
	}

	@Override
	public List<Contact> getAllContacts() {
		List<Contact> list=repo.findAll();
		return list;
	}

	@Override
	public Contact getContact(int cid) {
		Optional<Contact> opt=repo.findById(cid);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;	
		}
		
	}

	@Override
	public String deleteContact(int cid) {
		 repo.deleteById(cid);
		return "With this cid:: "+cid+ " Contact deleted successfully";
	}
	

}
