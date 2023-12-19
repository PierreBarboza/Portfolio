package school.sptech.marketplace.service.avaliacao.dto.avaliacao.mapper;

import school.sptech.marketplace.domain.cliente.Cliente;
import school.sptech.marketplace.domain.produto.Produto;
import school.sptech.marketplace.domain.produto.avaliacao.AvaliacaoClienteProduto;
import school.sptech.marketplace.service.avaliacao.dto.avaliacao.AvaliacaoClienteProdutoAlteracaoDto;
import school.sptech.marketplace.service.avaliacao.dto.avaliacao.AvaliacaoClienteProdutoConsultaDetalhadaDto;
import school.sptech.marketplace.service.avaliacao.dto.avaliacao.AvaliacaoClienteProdutoCriacaoDto;

import java.util.Objects;

public class AvaliacaoMapper {

    private AvaliacaoMapper() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static AvaliacaoClienteProdutoConsultaDetalhadaDto mapToConsultaDto(AvaliacaoClienteProduto domain) {

        if (Objects.isNull(domain)) {
            return null;
        }

        AvaliacaoClienteProdutoConsultaDetalhadaDto dto = new AvaliacaoClienteProdutoConsultaDetalhadaDto();

        dto.setId(domain.getId());
        dto.setNota(domain.getNota());
        dto.setComentario(domain.getComentario());

        return dto;
    }

    public static AvaliacaoClienteProduto mapAlteracaoDtoToDomain(
            long id,
            AvaliacaoClienteProdutoAlteracaoDto dto,
            Produto produto,
            Cliente cliente
    ) {

        if (Objects.isNull(dto)) {
            return null;
        }

        AvaliacaoClienteProduto domain = new AvaliacaoClienteProduto();

        domain.setId(id);
        domain.setNota(dto.getNota());
        domain.setComentario(dto.getComentario());
        domain.setComprador(cliente);
        domain.setProduto(produto);

        return domain;
    }

    public static AvaliacaoClienteProduto mapCriacaoDtoToDomain(
            AvaliacaoClienteProdutoCriacaoDto dto,
            Produto produto,
            Cliente cliente
    ) {

        if (Objects.isNull(dto)) {
            return null;
        }

        AvaliacaoClienteProduto domain = new AvaliacaoClienteProduto();

        domain.setNota(dto.getNota());
        domain.setComentario(dto.getComentario());
        domain.setComprador(cliente);
        domain.setProduto(produto);

        return domain;
    }
}
