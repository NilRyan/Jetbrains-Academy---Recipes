package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.dto.RegistrationFormDto;
import recipes.model.UserEntity;
import recipes.repository.UserRepository;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/register")
public class UserController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void registerNewUserAccount(@RequestBody @Valid RegistrationFormDto registrationFormDto) {
        if (userRepository.existsByEmail(registrationFormDto.getEmail())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        String hashedPassword = passwordEncoder.encode(registrationFormDto.getPassword());
        UserEntity user = new UserEntity(registrationFormDto.getEmail(), hashedPassword);

        userRepository.save(user);
    }
}
