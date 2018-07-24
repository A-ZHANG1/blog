package com.winnieazhang.github.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wayne.A.Z on 2018/7/24.
 */
public class MyAuthProvider implements AuthenticationProvider {


    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }


    @SuppressWarnings("serial")
    private static Map<String, String> SIMPLE_USERS = new HashMap<String, String>(2) {{
        put("joe", "joe");
        put("bob", "bob");
    }};

    @SuppressWarnings("serial" )
    private static List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>(1) {{
        add(new SimpleGrantedAuthority("ROLE_USER"));
    }};

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException
    {
        // All your user authentication needs
        System.out.println("==Authenticate Me==");
        if (SIMPLE_USERS.containsKey(auth.getPrincipal())
                && SIMPLE_USERS.get(auth.getPrincipal()).equals(auth.getCredentials()))
        {
            return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), AUTHORITIES);
        }
        throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
    }

}
