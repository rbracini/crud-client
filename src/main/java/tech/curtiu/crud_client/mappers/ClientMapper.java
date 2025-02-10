package tech.curtiu.crud_client.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import tech.curtiu.crud_client.dtos.ClientDTO;
import tech.curtiu.crud_client.entities.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO toDTO(Client client);

    Client toEntity(ClientDTO clientDTO);

}
