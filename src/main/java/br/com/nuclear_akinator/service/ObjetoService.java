package br.com.nuclear_akinator.service;

import br.com.nuclear_akinator.model.Categoria;
import br.com.nuclear_akinator.model.Objeto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ObjetoService {
    List<Objeto> findAll();

    Page<Objeto> findAll(Integer page, Integer size);

    List<Objeto> findByCategoria(Categoria categoria);

    Page<Objeto> findByCategoria(Categoria categoria, Integer page, Integer size);

    Objeto findById(Long id);

    List<Objeto> findByNomeContainingIgnoreCase(String nome);

    Objeto create(Objeto objeto);

    Optional<Objeto> update(Long id, Objeto objeto);

    void delete(Long id);
}
