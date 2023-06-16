package br.edu.ifba.provapweb.domain.entity;

import br.edu.ifba.provapweb.domain.dto.request.EnderecoRequest;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoRequest dto) {
        this(dto.logradouro(),dto.bairro(), dto.cep(), dto.numero(), dto.complemento(), dto.cidade(), dto.uf());
    }
}
