package training.jimmy.FakeShop.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import training.jimmy.FakeShop.model.User;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private String confirmationUrl;


    public RegistrationCompleteEvent(User user, String confirmationUrl) {
        super(user);
        this.user = user;
        this.confirmationUrl = confirmationUrl;
    }
}
