package com.williamdsw.cursomodelagemconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.williamdsw.cursomodelagemconceitual.domain.Categoria;

// Indica podera fazer operacoes do banco usando "JpaRepository"
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>
{

}