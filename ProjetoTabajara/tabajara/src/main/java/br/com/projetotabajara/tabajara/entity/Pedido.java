package br.com.projetotabajara.tabajara.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Pedido{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPedido;

    private LocalDate dataPedido;

    private Double totalPedido;

    @ManyToOne
    @joinColomn(name = "idUsuario_fk")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.all)
    private List<ItemDoPedido> item;

    public double calcularTotal() {
        double total = 0.0;
        if(itens != null) {
            for(ItemDoPedido item : itens){
                total += item.getSubtotal();
            }
        }
        return total;
    }

    public void atualizarTotal(){
        this.totalPedido = calcularTotal();
    }
}