package training.jimmy.FakeShop.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import training.jimmy.FakeShop.config.UserDetail;
import training.jimmy.FakeShop.dto.UserDto;
import training.jimmy.FakeShop.exceptions.EmailAlreadyExistsException;
import training.jimmy.FakeShop.model.Role;
import training.jimmy.FakeShop.model.User;
import training.jimmy.FakeShop.repository.UserRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Użytkownik o podanym adresie e-mail już istnieje.");
        }
        var user =  new User(userDto.getFirstName(), userDto.getLastName(),
                userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getUserName(),
                List.of(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

//    public void uploadAvatar(String username, MultipartFile file) {
//        Optional<User> user = userRepository.findByUserName(username);
//        if (user.isPresent() && !file.isEmpty()) {
//            try {
//                byte[] avatarBytes = file.getBytes();
//                user.setAvatar(avatarBytes);
//                userRepository.save(user);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }



}
