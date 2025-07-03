package dev.training.library.service;

import dev.training.library.config.SecurityConfig;
import dev.training.library.exception.CustomException;
import dev.training.library.model.UserModel;
import dev.training.library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException("Usuário não encontrado",404));
    }

    public String login(String email, String password) {

        Optional<UserModel> User = repository.findByEmail(email);
        if(User.isEmpty()){
            throw new CustomException("Email não encontrado", 401);
        }

        boolean passwordMatches = passwordEncoder.matches(password ,User.get().getPassword());
        if(!passwordMatches){
            throw new CustomException("Senha Incorreta", 401);
        }

        return SecurityConfig.JwtUtil.generateToken(User.get().getEmail());
    }

    public void deleteUserById(long id) {
        repository.deleteById(id);
    }

    public Optional<UserModel> findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public UserModel saveUser(UserModel userModel) {
        Optional<UserModel> existingUser = repository.findByEmail(userModel.getEmail());
        if(existingUser.isPresent()){
            throw new CustomException("Usuário já cadastrado com este e-mail", 400);
        }

        String encryptedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encryptedPassword);

        return repository.save(userModel);
    }
}
