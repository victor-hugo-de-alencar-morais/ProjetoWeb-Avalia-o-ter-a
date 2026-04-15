package br.com.projetotabajara.tabajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetotabajara.tabajara.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    
}
