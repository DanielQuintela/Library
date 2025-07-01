package dev.training.library.service;

import dev.training.library.model.UserModel;
import dev.training.library.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private final UserRepository repository;

    public UserModel getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Optional<UserModel> login(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
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
            throw new RuntimeException("Usuário já cadastrado");
        }

        return repository.save(userModel);
    }
}
