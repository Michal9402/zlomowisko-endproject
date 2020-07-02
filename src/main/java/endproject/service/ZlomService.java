package endproject.service;


import endproject.model.Zlom;
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

    public List<Zlom> getAll() {
        return repo.findAll();
    }

    public void save(Zlom zlom) {
        repo.save(zlom);
    }

    public Zlom getById(Long id) {
        return repo.findById(id).get();
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
