package com.michelzarpelon.cursomcmz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.michelzarpelon.cursomcmz.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {/*integer é o tipo do atributo identificador*/

}
