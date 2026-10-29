package br.com.nuclear_akinator.service;

import br.com.nuclear_akinator.exceptions.ResourceNotFoundException;
import br.com.nuclear_akinator.model.Categoria;
import br.com.nuclear_akinator.model.Objeto;
import br.com.nuclear_akinator.repository.ObjetoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class ObjetoServiceImpl implements ObjetoService {

    private final ObjetoRepository objetoRepository;

    public ObjetoServiceImpl(ObjetoRepository objetoRepository) {
        this.objetoRepository = objetoRepository;
    }

    @Override
    public List<Objeto> findAll() {
        return this.objetoRepository.findAll();
    }

    @Override
    public Page<Objeto> findAll(Integer page, Integer size) {
        return this.objetoRepository.findAll(
                Pageable.ofSize(size).withPage(page)
        );
    }

    @Override
    public List<Objeto> findByCategoria(Categoria categoria) {
        return this.objetoRepository.findByCategoria(categoria);
    }

    @Override
    public Page<Objeto> findByCategoria(Categoria categoria, Integer page, Integer size) {
        return this.objetoRepository.findByCategoria(
                categoria,
                Pageable.ofSize(size).withPage(page)
        );
    }

    @Override
    public Objeto findById(Long id) {
        Optional<Objeto> byId = this.objetoRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ResourceNotFoundException("objeto", id);
        }
        return byId.get();
    }

    @Override
    public List<Objeto> findByNomeContainingIgnoreCase(String nome) {
        return objetoRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public Objeto create(Objeto objeto) {
        objeto.setId(null);
        return this.objetoRepository.save(objeto);
    }

    @Override
    public Optional<Objeto> update(Long id, Objeto updated) {
        return this.objetoRepository.findById(id).map(existing -> {
            updated.setId(id);
            return objetoRepository.save(updated);
        });
    }

    @Override
    public void delete(Long id) {
        Objeto objeto = findById(id);
        this.objetoRepository.delete(objeto);
    }
}
