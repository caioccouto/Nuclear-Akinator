package br.com.nuclear_akinator.dto;

import br.com.nuclear_akinator.model.Categoria;
import br.com.nuclear_akinator.model.RelacaoRadiacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ObjetoRequest(

        @NotBlank(message = "nome não pode ser vazio")
        @Size(max = 150, message = "nome deve ter no máximo 150 caracteres")
        String nome,

        @NotNull(message = "categoria é obrigatória")
        Categoria categoria,

        @NotBlank(message = "descrição não pode ser vazia")
        @Size(max = 1000, message = "descrição deve ter no máximo 1000 caracteres")
        String descricao,

        @NotNull(message = "relação com a radiação é obrigatória")
        RelacaoRadiacao relacaoRadiacao,

        @NotBlank(message = "explicação científica não pode ser vazia")
        @Size(max = 2000, message = "explicação científica deve ter no máximo 2000 caracteres")
        String explicacaoCientifica) {
}
