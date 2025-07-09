package dev.training.library.controller;

import dev.training.library.model.RentModel;
import dev.training.library.service.RentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {

    private final RentService service;

    public RentController(RentService service) {
        this.service = service;
    }

    @GetMapping
    public List<RentModel> listAll() {
        return service.listAll();
    }

    @PostMapping
    public RentModel newRent(@RequestBody RentModel rentModel) {
        return service.newRent(rentModel);
    }

    @GetMapping("/user/{id}")
    public List<RentModel> getRentByUserId(@PathVariable Long id) {
        return service.getRentByUserId(id);
    }

    @GetMapping("/{id}")
    public RentModel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping
    public RentModel updateRent(@RequestBody RentModel rentModel) {
        return service.updateRent(rentModel);
    }

    @GetMapping("/owner/{id}")
    public List<RentModel> getRentByOwnerUserId(@PathVariable Long id) {
        return service.getRentByOwnerUserId(id);
    }
}
