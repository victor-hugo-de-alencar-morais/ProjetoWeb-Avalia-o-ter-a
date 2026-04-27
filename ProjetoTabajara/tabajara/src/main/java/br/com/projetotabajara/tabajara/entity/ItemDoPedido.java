package br.com.projetotabajara.tabajara.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDoPedido {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer idItem;

     private Integer quantidade;

     private double preco;

     private double subTotal;

    @ManyToOne
    @JoinColumn(name = "idProduto_fk")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idPedido_fk")
    private Pedido pedido;

    public double calcularSubTotal(){
        return quantidade * preco;

        private void atualizarSubtotal(){
            this.subtotal = calcularSubtotal();
        }
    }
}
