package br.com.nuclear_akinator.repository;

import br.com.nuclear_akinator.model.Categoria;
import br.com.nuclear_akinator.model.Objeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjetoRepository extends JpaRepository<Objeto, Long> {
    List<Objeto> findByCategoria(Categoria categoria);

    Page<Objeto> findByCategoria(Categoria categoria, Pageable pageable);

    List<Objeto> findByNomeContainingIgnoreCase(String nome);
}
