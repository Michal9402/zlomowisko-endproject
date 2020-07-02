package endproject.service;


import endproject.model.ZlomModel;
import endproject.repository.ZlomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZlomService {
    private ZlomRepository repo;

    public ZlomService(ZlomRepository repo) {
        this.repo = repo;
    }

    public List<ZlomModel> getAll() {
        return repo.findAll();
    }

    public void save(ZlomModel zlomModel) {
        repo.save(zlomModel);
    }

    public Optional<ZlomModel> getById(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
