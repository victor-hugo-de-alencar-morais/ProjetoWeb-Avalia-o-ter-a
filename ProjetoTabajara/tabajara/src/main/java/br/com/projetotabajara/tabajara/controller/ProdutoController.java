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
import br.com.projetotabajara.tabajara.entity.Produto;
import br.com.projetotabajara.tabajara.service.FornecedorService;
import br.com.projetotabajara.tabajara.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    
    // Injeção de dependência do serviço de produtos
    @Autowired
    private ProdutoService serviceProduto;

    @Autowired
    private FornecedorService serviceFornecedor;

    // Método para listar todos os produtos e exibir na página de lista de produtos         
    @GetMapping("/listar")
    public String listar(Model model) {
        // Busca todos os produtos
        List<Produto> produtos = serviceProduto.findAll();
        // Adiciona os produtos ao modelo
        model.addAttribute("produtos", produtos);
        // Retorna a página de lista de produtos
        return "produto/listarProduto";
    }

    // Método para abrir o formulário de criação de produtos    
     @GetMapping("/criar")
     public String criarForm(Model model) {
         // Adiciona um novo produto ao modelo
         model.addAttribute("produto", new Produto());
         // Retorna a página do formulário de produtos
         List<Fornecedor> fornecedores = serviceFornecedor.findAll();
         model.addAttribute("fornecedores", fornecedores);
         return "produto/formularioProduto";
     }
 
    // Método para salvar um produto com POST   
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto) {
        // Salva o produto
        serviceProduto.save(produto);
        // Redireciona para a lista de produtos
        return "redirect:/produtos/listar";
    }

    // Método para abrir o formulário de edição de produto     
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {
        // Busca o produto pelo id
        Produto produto = serviceProduto.findById(id);
        // Adiciona o produto ao modelo
        model.addAttribute("produto", produto);
        List<Fornecedor> fornecedores = serviceFornecedor.findAll();
        model.addAttribute("fornecedores", fornecedores);
        // Retorna a página do formulário de produto
        return "produto/formularioProduto";
    }

    // Método para atualizar um produto com PUT
    @PutMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Integer id, @ModelAttribute Produto produto) {
        // Define o id do produto
        produto.setIdProduto(id);
        // Salva o produto
        serviceProduto.save(produto);
        // Redireciona para a lista de produtos
        return "redirect:/produto/listar";
    }

    // Método para excluir um produto pelo id       
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        serviceProduto.deleteById(id); // Exclui o produto pelo id
        return "redirect:/produto/listar"; // Redireciona para a lista de produtos     
    }


    
}
