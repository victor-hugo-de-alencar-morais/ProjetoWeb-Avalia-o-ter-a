package br.com.projetotabajara.tabajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetotabajara.tabajara.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
