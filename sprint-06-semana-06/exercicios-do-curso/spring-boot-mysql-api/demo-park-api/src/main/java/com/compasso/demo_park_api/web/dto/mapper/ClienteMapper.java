package com.compasso.demo_park_api.web.dto.mapper;

import com.compasso.demo_park_api.entity.Cliente;
import com.compasso.demo_park_api.web.dto.ClienteCreateDTO;
import com.compasso.demo_park_api.web.dto.ClienteResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {
    public static Cliente toCliente(ClienteCreateDTO dto) {
        return new ModelMapper().map(dto, Cliente.class);
    }

    public static ClienteResponseDTO toDto(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteResponseDTO.class);
    }
}
