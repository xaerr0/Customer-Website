package com.springboot.customerwebsite.security;

import com.springboot.customerwebsite.models.securitymodels.AuthorityEnum;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            System.out.println("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }




    private String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
        for (final GrantedAuthority auth : auths) {
            String authName = auth.getAuthority();
            if (authName.equals(AuthorityEnum.ROLE_ADMIN.toString())) {
                return "/admin-dashboard";
            }
        }
        return "/user-dashboard";
    }
}