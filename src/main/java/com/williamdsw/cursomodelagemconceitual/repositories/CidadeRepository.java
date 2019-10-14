package com.williamdsw.cursomodelagemconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.williamdsw.cursomodelagemconceitual.domain.Cidade;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>
{
    @Transactional (readOnly = true)
    public List<Cidade> findByEstadoIdOrderByNomeAsc (Integer estadoID);
}