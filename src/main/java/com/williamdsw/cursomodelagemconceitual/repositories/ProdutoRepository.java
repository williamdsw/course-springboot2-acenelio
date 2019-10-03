package com.williamdsw.cursomodelagemconceitual.repositories;

import com.williamdsw.cursomodelagemconceitual.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.williamdsw.cursomodelagemconceitual.domain.Produto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>
{
    // @Query = Permite realizar a query utilizando JPA que e nivelado para objeto
    @Transactional (readOnly = true)
    @Query (" SELECT DISTINCT objProduto FROM Produto objProduto INNER JOIN objProduto.categorias objCategoria WHERE objProduto.nome LIKE %:nome% AND objCategoria IN :categorias ")
    Page<Produto> search (@Param ("nome") String nome, @Param ("categorias") List<Categoria> categorias, Pageable pageRequest);
    
    // Mesmo resultado da @Query
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn (String nome, List<Categoria> categorias, Pageable pageRequest);
}