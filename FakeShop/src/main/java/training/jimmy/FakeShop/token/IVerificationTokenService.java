package training.jimmy.FakeShop.token;

import com.fasterxml.jackson.annotation.OptBoolean;
import training.jimmy.FakeShop.model.User;

import java.util.Optional;

public interface IVerificationTokenService {

    String validateToken(String token);
    void saveVerificationTokenFromUser(User user, String token);
    Optional<VerificationToken> findByToken(String token);
}

