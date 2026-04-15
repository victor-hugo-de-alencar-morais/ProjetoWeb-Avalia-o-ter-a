package br.com.projetotabajara.tabajara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projetotabajara.tabajara.entity.Fornecedor;
import br.com.projetotabajara.tabajara.service.FornecedorService;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

   
    // Injeção de dependência do serviço de fornecedores
    @Autowired
    private FornecedorService serviceFornecedor;

    // Método para listar todos os fornecedores e exibir na página de lista de fornecedores         
    @GetMapping("/listar")
    public String listar(Model model) {
        // Busca todos os fornecedores
        List<Fornecedor> fornecedores = serviceFornecedor.findAll();
        // Adiciona os fornecedores ao modelo
        model.addAttribute("fornecedores", fornecedores);
        // Retorna a página de lista de fornecedores
        return "fornecedor/listarFornecedor";
    }


    // Método para abrir o formulário de criação de fornecedores    
     @GetMapping("/criar")
     public String criarForm(Model model) {
         // Adiciona um novo fornecedor ao modelo
         model.addAttribute("fornecedor", new Fornecedor());
         // Retorna a página do formulário de fornecedores
         return "fornecedor/formularioFornecedor";
     }
 
    // Método para salvar um fornecedor com POST   
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Fornecedor fornecedor) {
        // Salva o fornecedor
        serviceFornecedor.save(fornecedor);
        // Redireciona para a lista de fornecedores
        return "redirect:/fornecedores/listar";
    }

    // Método para abrir o formulário de edição de fornecedor     
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        // Busca o fornecedor pelo id
        Fornecedor fornecedor = serviceFornecedor.findById(id);
        // Adiciona o fornecedor ao modelo
        model.addAttribute("fornecedor", fornecedor);
        // Retorna a página do formulário de fornecedor
        return "fornecedor/formularioFornecedor";
    }

    // Método para atualizar um fornecedor com PUT
    @PutMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Integer id, @ModelAttribute Fornecedor fornecedor) {
        // Define o id do fornecedor
        fornecedor.setIdFornecedor(id);
        // Salva o fornecedor
        serviceFornecedor.save(fornecedor);
        // Redireciona para a lista de fornecedores
        return "redirect:/fornecedores/listar";
    }

    // Método para excluir um fornecedor pelo id       
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        serviceFornecedor.deleteById(id); // Exclui o fornecedor pelo id
        return "redirect:/fornecedores/listar"; // Redireciona para a lista de fornecedores     
    }
    
}
