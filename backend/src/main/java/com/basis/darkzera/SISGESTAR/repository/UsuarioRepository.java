package com.basis.darkzera.SISGESTAR.repository;

import com.basis.darkzera.SISGESTAR.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    @Query(value =
            "SELECT * " +
                    "FROM " + "usuario " +
                    "where " + "usuario.hash = :hash", nativeQuery = true
    )
    Optional<Usuario> findByHash(@Param("hash") String hash);

    @Query(value =
            "SELECT * " +
                    "FROM " + "usuario " +
                    "where " + "usuario.email= :email", nativeQuery = true
    )
    Optional<Usuario> findByEmail(@Param("email")String emailAddress);

}
