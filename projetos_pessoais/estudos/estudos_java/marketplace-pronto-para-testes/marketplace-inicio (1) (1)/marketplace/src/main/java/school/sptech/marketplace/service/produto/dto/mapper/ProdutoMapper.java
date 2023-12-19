package school.sptech.marketplace.service.produto.dto.mapper;

import school.sptech.marketplace.domain.produto.Produto;
import school.sptech.marketplace.service.produto.dto.ProdutoAtualizacaoDto;
import school.sptech.marketplace.service.produto.dto.ProdutoConsultaDto;
import school.sptech.marketplace.service.produto.dto.ProdutoCriacaoDto;

import java.util.Objects;

public class ProdutoMapper {

    private ProdutoMapper() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static ProdutoConsultaDto mapToConsultaDto(Produto domain) {

        if (Objects.isNull(domain)) {
            return null;
        }

        ProdutoConsultaDto dto = new ProdutoConsultaDto();

        dto.setId(domain.getId());
        dto.setNome(domain.getNome());
        dto.setDescricao(domain.getDescricao());
        dto.setPreco(domain.getPreco());
        dto.setQuantidade(domain.getQuantidade());

        return dto;
    }

    public static Produto mapProdutoCriacaoDtoToDomain(ProdutoCriacaoDto dto) {

        if (Objects.isNull(dto)) {
            return null;
        }

        Produto domain = new Produto();

        domain.setNome(dto.getNome());
        domain.setDescricao(dto.getDescricao());
        domain.setPreco(dto.getPreco());
        domain.setQuantidade(dto.getQuantidade());

        return domain;
    }

    public static Produto mapProdutoAtualizacaoDtoToDomain(
            long id,
            ProdutoAtualizacaoDto dto
    ) {

        if (Objects.isNull(dto)) {
            return null;
        }

        Produto domain = new Produto();

        domain.setId(id);
        domain.setNome(dto.getNome());
        domain.setDescricao(dto.getDescricao());
        domain.setPreco(dto.getPreco());
        domain.setQuantidade(dto.getQuantidade());

        return domain;
    }
}
