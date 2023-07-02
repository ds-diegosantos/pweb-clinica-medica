package br.edu.ifba.provapweb.domain.dto.response;

import br.edu.ifba.provapweb.domain.entity.Consulta;
import br.edu.ifba.provapweb.domain.entity.Endereco;

public record EnderecoResponse(String logradouro,
                               String bairro,
                               String cep,
                               String numero,
                               String complemento,
                               String cidade,
                               String uf) {

    public EnderecoResponse(Endereco endereco) {
        this(endereco.getLogradouro(),endereco.getBairro(), endereco.getCep(), endereco.getNumero(), endereco.getComplemento(), endereco.getCidade(), endereco.getUf());
    }
}
