package com.basis.darkzera.SISGESTAR.repository;

import com.basis.darkzera.SISGESTAR.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
