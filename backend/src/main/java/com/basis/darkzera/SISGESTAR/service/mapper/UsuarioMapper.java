package com.basis.darkzera.SISGESTAR.service.mapper;

import com.basis.darkzera.SISGESTAR.domain.Usuario;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioDTO;
import com.basis.darkzera.SISGESTAR.service.dto.UsuarioListDTO;
import com.basis.darkzera.SISGESTAR.web.rest.UsuarioResource;
import org.mapstruct.Mapper;

@Mapper
public interface UsuarioMapper extends EntityMapper<Usuario, UsuarioDTO> {

}
