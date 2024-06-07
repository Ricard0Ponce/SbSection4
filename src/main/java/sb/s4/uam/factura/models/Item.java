package sb.s4.uam.factura.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Nota: Observamos que el Item se relaciona con el producto
// 1 Item contiene un producto y una cantidad
// 1 producto podría estar asocidado a muchos items
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

    private Product product;
    private Integer quantity;

    public int getImporte() {
        return product.getPrice() * quantity;
    }
}
