package com.williamdsw.cursomodelagemconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.williamdsw.cursomodelagemconceitual.domain.Estado;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>
{
    @Transactional (readOnly = true)
    public List<Estado> findAllByOrderByNome ();
}