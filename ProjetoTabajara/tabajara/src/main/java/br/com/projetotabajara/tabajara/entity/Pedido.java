package br.com.projetotabajara.tabajara.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPedido;

    private LocalDate dataPedido;

    private Double totalPedido;

    @ManyToOne
    @JoinColumn(name = "idUsuario_fk")   // anotação corrigida
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)  // cascade em maiúsculo
    private List<ItemDoPedido> itens;  // nome do campo agora é "itens" (plural)

    public double calcularTotal() {
        double total = 0.0;
        if (itens != null) {
            for (ItemDoPedido item : itens) {
                total += item.getSubtotal();   // getSubtotal() será gerado pelo Lombok
            }
        }
        return total;
    }

    public void atualizarTotal() {
        this.totalPedido = calcularTotal();
    }
}