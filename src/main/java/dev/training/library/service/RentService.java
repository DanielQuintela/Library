package dev.training.library.service;

import dev.training.library.model.RentModel;
import dev.training.library.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    private final RentRepository repository;

    public RentService(RentRepository repository) {
        this.repository = repository;
    }

    public List<RentModel> listAll(){
        return repository.findAll();
    }

    public RentModel newRent(RentModel rentModel) {
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
