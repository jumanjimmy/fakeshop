package training.jimmy.FakeShop.service;

import com.fasterxml.jackson.annotation.OptBoolean;
import training.jimmy.FakeShop.dto.UserDto;
import training.jimmy.FakeShop.model.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getAllUsers();
    User registerUser(UserDto userDto);
    Optional<User> findByEmail(String email);

}
