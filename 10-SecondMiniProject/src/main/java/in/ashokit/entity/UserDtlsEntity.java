package in.ashokit.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "USER_ACCOUNT")
public class UserDtlsEntity implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer UserId;
	
	@Column(name = "FIRST_NAME")
	private String fname;
	
	@Column(name = "LAST_NAME")
	private String lname;
	
	@Column(name = "USER_EMAIL", unique = true)
	private String email;
	
	@Column(name = "USER_PWD")
	private String pwd;
	
	@Column(name = "USER_MOBILE")
	private Long phno;
	
	@Column(name = "DOB")
	private LocalDate dob;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "STATE_ID")
	private Integer stateId;
	
	@Column(name = "CITY_ID")
	private Integer cityId;
	
    @Column(name = "ACC_STATUS")
	private String acc_status;
    
    @Column(name="CREATE_DATE" ,updatable = false)
    @CreationTimestamp
    private LocalDate createDate;
    
    @Column(name="UPDATE_DATE" ,insertable = false)
    @UpdateTimestamp
    private LocalDate updateDate;

}
