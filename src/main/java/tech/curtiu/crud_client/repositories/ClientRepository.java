package tech.curtiu.crud_client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.curtiu.crud_client.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
