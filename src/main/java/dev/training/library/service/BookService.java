package dev.training.library.service;

import dev.training.library.model.BookModel;
import dev.training.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    private final BookRepository repository;

    public List<BookModel> listAll(){
        return  repository.findAll();
    }

    public BookModel newBook(BookModel book) {
        Optional<BookModel> existingBook = repository.findByisbn(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new RuntimeException("ISBN já cadastrado!");
        }
        return repository.save(book);
    }


    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public BookModel getByID(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }
}
