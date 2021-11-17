package com.basis.darkzera.SISGESTAR.service.mapper;

import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.service.dto.SelectDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioListDTO;
import com.basis.darkzera.SISGESTAR.web.rest.UsuarioResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UsuarioMapper extends EntityMapper<Usuario, UsuarioDTO> {

    Usuario toEntity(UsuarioDTO usuarioDTO);
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(source = "name", target = "label")
    @Mapping(source = "id", target = "value")
    SelectDTO toListSelectDTO(Usuario usuario);

    UsuarioListDTO toListDTO(Usuario usuario);
}
