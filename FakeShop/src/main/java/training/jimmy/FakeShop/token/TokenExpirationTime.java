package training.jimmy.FakeShop.token;

import java.util.Calendar;
import java.util.Date;

public class TokenExpirationTime {

    private static final int EXPIRANTION_TIME = 10;

    public static Date getExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRANTION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
