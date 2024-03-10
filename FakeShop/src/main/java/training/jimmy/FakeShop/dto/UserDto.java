package training.jimmy.FakeShop.dto;

import lombok.Data;
import training.jimmy.FakeShop.model.Role;

import java.util.List;

@Data
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    public String userName;
    private String avatar;
    private List<Role> roles;
}
