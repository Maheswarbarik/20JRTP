package in.ashokit.binding;

import java.io.Serializable;

import lombok.Data;

@Data
public class UnlockAccessForm implements Serializable {
   private String email;
   private String tempPwd;
   private String newPwd;
   private String cfgnewPwd;
   
}
