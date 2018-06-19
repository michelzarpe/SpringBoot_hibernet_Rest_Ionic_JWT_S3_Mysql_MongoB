package com.michelzarpelon.cursomcmz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.michelzarpelon.cursomcmz.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {/*integer é o tipo do atributo identificador*/

}
