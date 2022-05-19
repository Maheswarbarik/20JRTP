package in.ashokit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Contact;
import in.ashokit.service.ContactService;

@RestController
public class ContactRestController {
	@Autowired
	private ContactService service;
	
	@PostMapping("/contact")
	public ResponseEntity<String> savecontact(@RequestBody Contact contact) {
		return new ResponseEntity<String>(service.upsert(contact), HttpStatus.CREATED);
	}


	@GetMapping("/contacts")
	public ResponseEntity<List<Contact>> getAllContacts( ) {
		return new ResponseEntity<List<Contact>>(service.getAllContacts(),HttpStatus.OK);
	}

	@GetMapping("/contact/{cid}")
	public ResponseEntity<Contact> getcontact(@PathVariable int cid) {
		return new ResponseEntity<Contact>(service.getContact(cid), HttpStatus.OK);
		
	}

	@DeleteMapping("/contact/{cid}")
	public ResponseEntity<String> deletecontact(@PathVariable int cid) {
		return new ResponseEntity<String>(service.deleteContact(cid), HttpStatus.OK);
	}

}
