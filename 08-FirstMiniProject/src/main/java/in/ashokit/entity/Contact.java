package in.ashokit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name="CONTACT_DTLS")
@Data
public class Contact implements Serializable {
    
	@Id
    @GeneratedValue
	private Integer cid;
	private String name;
	private String email;
	private long phno;
	private String activeSw;
	
	
	
	
}
