package sb.s4.uam.factura.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
// Nota: Invoice es factura
// Observamos que la factura contiene un cliente, una descripcion y una lista de items
// Esto es un POJO es decir que solo contiene información y no tiene lógica/contratos
// Cuando se trata de POJOS no se necesita de una interfaz, simplemente es un componente.
// La lógica se implementa en los servicios
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component // Con esta etiqueta Spring podra inyectar esta clase en otras clases
@RequestScope // Se crea un nuevo objeto por cada petición, es decir por cada vez que se haga una petición se creara un nuevo objeto
@JsonIgnoreProperties({"targetSource", "advisors"}) // Ignora las propiedades que no se encuentran en el objeto
public class Invoice {
    @Autowired // Realiza la inyección de dependencias para que
    private Client client; // El Invoice contiee un Cliente
    @Value(("${invoice.description}")) // Tomamos los datos desde el archivo data.properties
    private String description;
    // Para poder inyectar la lista se necesitara un Bean de la clase Item
    @Autowired //
    private List<Item> items;//Contiene una lista de Items que tambien contiene un producto

    public int getTotal(){
        // Explicacion:
        // Se toma la lista de items y se mapea a un stream
        // Se convierte a un entero y se suma el importe de cada item
       return items.stream()
                .map(item -> item.getImporte())
                .reduce(0,(sum,item)->sum+item);
    }

    @PostConstruct // Se ejecuta despues de que se haya creado el objeto, es decir despues del constructor
    // Se ejecuta despues de que se haya inyectado las dependencias
    // Me sirve sí quiero usar el objeto justo despues de que se haya creado y se hayan inyectado las dependencias
    public void init() {
        // Como ejemplo manipulamos al objeto después de su creación.
        client.setLastname("Robles"); // Esto provoca que el apellido del cliente sea Robles
    }
    @PreDestroy // Se ejecuta justo antes de que se destruya el objeto
    public void destroy() {
        // Aquí, se puede hacer algun tipo de limpieza de recursos o liberar memoria antes de que el objeto sea destruido
        System.out.println("Destruyendo el componente o Bean Invoice");
    }
}
