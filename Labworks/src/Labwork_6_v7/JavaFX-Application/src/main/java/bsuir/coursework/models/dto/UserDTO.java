package bsuir.coursework.models.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

  private Integer userId;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String phoneNumber;
}
