package dev.training.library.service;

import dev.training.library.exception.CustomException;
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
        List<BookModel> list = repository.findAll();
        if(list.isEmpty()){
            throw new CustomException("Não há livros cadastrados", 500);
        }
        return list;
    }

    public BookModel newBook(BookModel book) {
        Optional<BookModel> existingBook = repository.findByisbn(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new CustomException("ISBN já cadastrado!",500);
        }
        return repository.save(book);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public BookModel getByID(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException("Livro não encontrado",404));
    }
}
