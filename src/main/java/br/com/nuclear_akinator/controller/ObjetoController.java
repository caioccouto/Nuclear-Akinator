package br.com.nuclear_akinator.controller;

import br.com.nuclear_akinator.dto.ObjetoRequest;
import br.com.nuclear_akinator.dto.ObjetoResponse;
import br.com.nuclear_akinator.mapper.ObjetoMapper;
import br.com.nuclear_akinator.mapper.PageResponse;
import br.com.nuclear_akinator.model.Categoria;
import br.com.nuclear_akinator.model.Objeto;
import br.com.nuclear_akinator.service.ObjetoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/objetos")
@Validated
public class ObjetoController {

    private final ObjetoService objetoService;

    public ObjetoController(ObjetoService objetoService) {
        this.objetoService = objetoService;
    }

    @GetMapping
    public PageResponse<ObjetoResponse> getAllObjetos(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                      @RequestParam(required = false, defaultValue = "10") Integer sizePage) {
        Page<Objeto> pageResult = this.objetoService.findAll(page, sizePage);

        return PageResponse.from(pageResult.map(ObjetoMapper::toResponse));
    }

    @GetMapping("/categoria/{categoria}")
    public PageResponse<ObjetoResponse> findByCategoria(@PathVariable Categoria categoria,
                                                        @RequestParam(required = false, defaultValue = "0") Integer page,
                                                        @RequestParam(required = false, defaultValue = "10") Integer sizePage) {
        Page<Objeto> pageResult = this.objetoService.findByCategoria(categoria, page, sizePage);

        return PageResponse.from(pageResult.map(ObjetoMapper::toResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetoResponse> findById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(ObjetoMapper.toResponse(objetoService.findById(id))
        );
    }

    @GetMapping("/busca")
    public List<ObjetoResponse> buscarPorNome(@RequestParam String nome) {

        return objetoService.findByNomeContainingIgnoreCase(nome).stream().map(ObjetoMapper::toResponse).toList();
    }

    @PostMapping
    public ResponseEntity<ObjetoResponse> createObjeto(@RequestBody @Valid ObjetoRequest request) {
        Objeto objeto = new Objeto();
        objeto.setNome(request.nome());
        objeto.setCategoria(request.categoria());
        objeto.setDescricao(request.descricao());
        objeto.setRelacaoRadiacao(request.relacaoRadiacao());
        objeto.setExplicacaoCientifica(request.explicacaoCientifica());

        Objeto salvo = objetoService.create(objeto);
        return ResponseEntity.created(URI.create("/api/objetos/" + salvo.getId())).body(ObjetoMapper.toResponse(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetoResponse> updateObjeto(@PathVariable @Positive Long id,
                                                       @RequestBody @Valid ObjetoRequest request) {
        Objeto objeto = objetoService.findById(id);
        objeto.setNome(request.nome());
        objeto.setCategoria(request.categoria());
        objeto.setDescricao(request.descricao());
        objeto.setRelacaoRadiacao(request.relacaoRadiacao());
        objeto.setExplicacaoCientifica(request.explicacaoCientifica());

        Objeto updated = objetoService.update(id, objeto).orElseThrow();
        return ResponseEntity.ok(ObjetoMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObjeto(@PathVariable @Positive Long id) {
        this.objetoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
