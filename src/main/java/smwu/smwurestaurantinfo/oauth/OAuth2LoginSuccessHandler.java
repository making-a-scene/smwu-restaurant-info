package smwu.smwurestaurantinfo.oauth;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("access_token", request.getAttribute("access_token"));
        body.put("refresh_token", request.getAttribute("refresh_token"));

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.sendRedirect("/");

        new ObjectMapper().writeValue(response.getOutputStream(), body);

    }
}


