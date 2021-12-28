package com.basis.darkzera.SISGESTAR.repository;

import com.basis.darkzera.SISGESTAR.domain.Comentario;
import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.service.dto.ComentarioDTO;
import com.basis.darkzera.SISGESTAR.service.dto.ComentarioListDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {


    @Query(value =
            "SELECT * " +
                    " FROM " + " comentario" +
                    " where " + " comentario.id_usuario = :idUsuario", nativeQuery = true
    )
    Page<Comentario> findByIdUsuario(@Param("idUsuario") Long idUsuario, Pageable page);

}
