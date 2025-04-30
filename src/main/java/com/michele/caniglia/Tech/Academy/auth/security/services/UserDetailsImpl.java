package com.michele.caniglia.Tech.Academy.auth.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.michele.caniglia.Tech.Academy.auth.model.AuthUtente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(AuthUtente utente) {
        List<GrantedAuthority> authorities = utente.getRuoli().stream()
                .map(ruolo -> (GrantedAuthority) () -> ruolo.getNome().name())
                .toList();

        return new UserDetailsImpl(
                utente.getId(),
                utente.getUsername(),
                utente.getPassword(),
                authorities
        );
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailsImpl that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
