package br.com.projetotabajara.tabajara.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetotabajara.tabajara.entity.Fornecedor;
import br.com.projetotabajara.tabajara.repository.FornecedorRepository;

@Service
public class FornecedorService {

    // Injeção de dependência do repositório de fornecedores
    @Autowired
    private FornecedorRepository fornecedorRepository;

    // Método para salvar um fornecedor
    public Fornecedor save(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor); // Salva o fornecedor no repositório e retorna o fornecedor salvo
    }

    // Método para buscar todos os fornecedores
    public List<Fornecedor> findAll() {
        return fornecedorRepository.findAll(); // Retorna todos os fornecedores do repositório
    }

    // Método para buscar um fornecedor pelo id
    public Fornecedor findById(Integer id) {
        return fornecedorRepository.findById(id).orElse(null); // Retorna o fornecedor com o id especificado ou null se não existir
    }

    // Método para excluir um fornecedor pelo id
    public void deleteById(Integer id) {
        fornecedorRepository.deleteById(id); // Exclui o fornecedor com o id especificado do repositório
    }


    
}
