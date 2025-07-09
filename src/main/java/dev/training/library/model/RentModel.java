package dev.training.library.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class RentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "owner_user_id")
    private UserModel ownerUser;

    private LocalDate rentDate;
    private LocalDate returnDate;

    @PrePersist
    public void prePersist() {
        this.rentDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UserModel getOwner_user() {
        return ownerUser;
    }

    public void setOwner_user(UserModel owner_user) {
        this.ownerUser = owner_user;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
