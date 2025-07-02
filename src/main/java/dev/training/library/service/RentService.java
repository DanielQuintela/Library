package dev.training.library.service;

import dev.training.library.model.BookModel;
import dev.training.library.model.RentModel;
import dev.training.library.model.UserModel;
import dev.training.library.repository.BookRepository;
import dev.training.library.repository.RentRepository;
import dev.training.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    private final RentRepository repository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public RentService(RentRepository repository, BookRepository bookRepository, UserRepository userRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<RentModel> listAll(){
        return repository.findAll();
    }

    public RentModel newRent(RentModel rentModel) {
        BookModel book = bookRepository.findById(rentModel.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        UserModel user = userRepository.findById(rentModel.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        rentModel.setBook(book);
        rentModel.setUser(user);

        return repository.save(rentModel);
    }

    public List<RentModel> getRentByUserId(long id) {
        return repository.findByUserId(id);
    }

    public RentModel getById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguel de livro não encontrado."));
    }

    public RentModel updateRent(RentModel rentModel) {
        if(!repository.existsById(rentModel.getId())){
            throw new RuntimeException("Aluguel de livro não encontrado.");
        }
        return repository.save(rentModel);
    }

}
