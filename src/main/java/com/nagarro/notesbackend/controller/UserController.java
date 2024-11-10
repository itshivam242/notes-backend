package com.nagarro.notesbackend.controller;

import com.nagarro.notesbackend.service.impl.UserDetailsServiceImpl;
import com.nagarro.notesbackend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.notesbackend.helper.JwtHelper;
import com.nagarro.notesbackend.entity.User;
import com.nagarro.notesbackend.dto.UserDetailsImpl;
import com.nagarro.notesbackend.dto.UserRequestDto;
import com.nagarro.notesbackend.dto.UserResponseDto;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtTokenUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserServiceImpl service;

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody UserRequestDto authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmailId(), authenticationRequest.getPassword());
        final UserDetailsImpl userDetailsImpl = userDetailsService.loadUserByUsername(authenticationRequest.getEmailId());
        final String token = jwtTokenUtil.generateToken(userDetailsImpl);
        return ResponseEntity.ok(new UserResponseDto(token, userDetailsImpl.firstName(), userDetailsImpl.userName(),userDetailsImpl.id()));
    }

    /*
     * This API is used to store the user details in database with encode password
     * @Param User
     * @Return User
     */
    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody User user) throws Exception {
        String tempEmailId = user.getEmailId();
        if (tempEmailId != null && !"".equals(tempEmailId)) {
            User userObj = service.fetchUserByEmailId(tempEmailId);
            if (userObj != null) {
                throw new Exception("User with" + tempEmailId + " is already exist");
            }
        }
        String password = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        User userObj = service.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

