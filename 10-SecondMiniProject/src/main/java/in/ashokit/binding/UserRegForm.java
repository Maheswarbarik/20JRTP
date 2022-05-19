package in.ashokit.binding;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import ch.qos.logback.classic.pattern.DateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserRegForm  {
	private String fname;
	private String lname;
	private String email;
	private Long phno;
	//@DateTimeFormat(pattern="dd-mm-yyyy")
	private LocalDate dob;
	private String gender;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;

}
