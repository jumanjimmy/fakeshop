package training.jimmy.FakeShop.utility;

import jakarta.servlet.http.HttpServletRequest;

public class UrlUtil {

    public static String getApplicactionUrl(HttpServletRequest request){
        String appUrl = request.getRequestURL().toString();
        return appUrl.replace(request.getServletPath(), "");
    }
}
