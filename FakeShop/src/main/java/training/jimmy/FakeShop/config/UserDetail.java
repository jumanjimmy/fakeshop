package training.jimmy.FakeShop.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import training.jimmy.FakeShop.model.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDetail implements UserDetails {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String userName;
    private byte[] avatar;
    private boolean isEnabled;
    private List<GrantedAuthority> authorities;

    @Builder
    public UserDetail (User user){
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.userName = user.getUserName();
        this.avatar = user.getAvatar();
        this.isEnabled = user.isEnabled();
        this.authorities =
                Arrays.stream(user.getRoles().toString().split(","))
                        .map(SimpleGrantedAuthority:: new)
                        .collect(Collectors.toList());
    }

    public static UserDetailBuilder builder() {
        return new UserDetailBuilder();
    }


    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return firstName;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
