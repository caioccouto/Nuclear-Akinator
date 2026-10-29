package br.com.nuclear_akinator.dto;

import br.com.nuclear_akinator.model.Categoria;
import br.com.nuclear_akinator.model.RelacaoRadiacao;

public record ObjetoResponse(Long id, String nome, Categoria categoria, String descricao,
                             RelacaoRadiacao relacaoRadiacao, String explicacaoCientifica) {
}
