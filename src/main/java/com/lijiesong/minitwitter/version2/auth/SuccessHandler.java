package com.lijiesong.minitwitter.version2.auth;

import com.lijiesong.minitwitter.version2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TweetUserDetailsServices tweetUserDetailsServices;
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        TweetUserPrincipal authUser = (TweetUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = authUser.getUserId();
        String userName = authUser.getName();
        session.setAttribute("userId", userId);
        session.setAttribute("userName", userName);
        httpServletResponse.sendRedirect("/");
    }
}
