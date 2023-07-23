package salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Nutricionista;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import salus.well.projetosaluswell.domain.nutricionista.Nutricionista;

import java.util.Collection;

public class NutricionistaDetalhesDto implements UserDetails {

    private final String email;

    private final String senha;

    public NutricionistaDetalhesDto(Nutricionista u) {
        this.email = u.getEmail();
        this.senha = u.getSenha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
