package training.jimmy.FakeShop.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import training.jimmy.FakeShop.config.UserDetail;
import training.jimmy.FakeShop.dto.UserDto;
import training.jimmy.FakeShop.event.RegistrationCompleteEvent;
import training.jimmy.FakeShop.exceptions.EmailAlreadyExistsException;
import training.jimmy.FakeShop.model.User;
import training.jimmy.FakeShop.service.IUserService;
import training.jimmy.FakeShop.token.VerificationToken;
import training.jimmy.FakeShop.token.VerificationTokenRepository;
import training.jimmy.FakeShop.token.VerificationTokenService;
import training.jimmy.FakeShop.utility.UrlUtil;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final IUserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenService tokenService;



    @GetMapping("/register-form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDto userDto, HttpServletRequest request, Model model) {
        try {
            User user = userService.registerUser(userDto);
            publisher.publishEvent(new RegistrationCompleteEvent(user, UrlUtil.getApplicactionUrl(request)));
            return "redirect:/registration/register-form?success";

        } catch (EmailAlreadyExistsException e) {
            return "redirect:/registration/register-form?error";
        }
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token) {
        Optional<VerificationToken> theToken = tokenService.findByToken(token);
        if (theToken.isPresent() && theToken.get().getUser().isEnabled()) {
            return "redirect:/login?verified";
        }
        String verificationResult = tokenService.validateToken(token); {
            switch (verificationResult.toLowerCase()) {
                case "expired":
                    return "redirect:/error?expired";
                case "valid":
                    return "redirect:/login?valid";
                default:
                    return "redirect:/error?invalid";
            }
        }
    }
}
