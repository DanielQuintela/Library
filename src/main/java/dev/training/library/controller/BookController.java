package dev.training.library.controller;



import dev.training.library.model.BookModel;
import dev.training.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookModel> getAll(){
        return service.listAll();
    }

    @PostMapping
    public BookModel newBook(@RequestBody BookModel book){
        return service.newBook(book);
    }

    @GetMapping("/{id}")
    public BookModel getByID(@PathVariable Long id) {
        return service.getByID(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable long id) {
        service.deletar(id);
    }
}
