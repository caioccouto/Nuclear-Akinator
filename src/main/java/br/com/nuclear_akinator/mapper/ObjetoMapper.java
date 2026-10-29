package br.com.nuclear_akinator.mapper;

import br.com.nuclear_akinator.dto.ObjetoResponse;
import br.com.nuclear_akinator.model.Objeto;

public final class ObjetoMapper {

    private ObjetoMapper() {
    }

    public static ObjetoResponse toResponse(Objeto objeto) {
        return new ObjetoResponse(
                objeto.getId(),
                objeto.getNome(),
                objeto.getCategoria(),
                objeto.getDescricao(),
                objeto.getRelacaoRadiacao(),
                objeto.getExplicacaoCientifica()
        );
    }
}
