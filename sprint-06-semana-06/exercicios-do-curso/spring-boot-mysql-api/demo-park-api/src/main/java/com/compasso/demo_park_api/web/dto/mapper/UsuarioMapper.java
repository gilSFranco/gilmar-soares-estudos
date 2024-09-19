package com.compasso.demo_park_api.web.dto.mapper;

import com.compasso.demo_park_api.entity.Usuario;
import com.compasso.demo_park_api.web.dto.UsuarioCreateDTO;
import com.compasso.demo_park_api.web.dto.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.List;

public class UsuarioMapper {
    public static Usuario toUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        return new ModelMapper().map(usuarioCreateDTO, Usuario.class);
    }

    public static UsuarioResponseDTO toDto(Usuario usuario) {
        String role = usuario.getRole().name().substring("ROLE_".length());

        ModelMapper mapperMain = new ModelMapper();

        TypeMap<Usuario, UsuarioResponseDTO> propertyMapper =
                mapperMain.createTypeMap(Usuario.class, UsuarioResponseDTO.class);

        propertyMapper.addMappings(
                mapper -> mapper.map(src -> role, UsuarioResponseDTO::setRole)
        );

        return mapperMain.map(usuario, UsuarioResponseDTO.class);
    }

    public static List<UsuarioResponseDTO> toListDto(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::toDto).toList();
    }
}
