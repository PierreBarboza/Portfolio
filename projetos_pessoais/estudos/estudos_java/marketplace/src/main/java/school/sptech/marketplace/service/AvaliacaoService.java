package school.sptech.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.marketplace.dominio.AvaliacaoCompradorProduto;
import school.sptech.marketplace.dominio.exception.EntidadeNaoEncontradaException;
import school.sptech.marketplace.repositorio.AvaliacaoCompradorProdutoRepository;

import java.util.List;

@Service
public class AvaliacaoService {


    @Autowired
    private AvaliacaoCompradorProdutoRepository repository;

    public List<AvaliacaoCompradorProduto> listar(){

        List<AvaliacaoCompradorProduto> avaliacoes = this.repository.findAll();

        return avaliacoes;
    }

    public AvaliacaoCompradorProduto buscarPorId(long id){

        return this.repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Avaliação não encontrada!")
        );

    }
}
