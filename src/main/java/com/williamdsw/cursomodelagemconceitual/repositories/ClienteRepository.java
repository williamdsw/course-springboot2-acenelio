package com.williamdsw.cursomodelagemconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.williamdsw.cursomodelagemconceitual.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>
{
    // Utilizando "findBy" com nome do campo a ser pesquisado, ja sera configurada a consulta automaticamente 
    @Transactional (readOnly = true)
    Cliente findByEmail (String email);
}