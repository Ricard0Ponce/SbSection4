package sb.s4.uam.factura;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import sb.s4.uam.factura.models.Item;
import sb.s4.uam.factura.models.Product;
import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:data.properties")
public class AppConfig {
    @Bean // Lo agregamos como un Bean porque se inyectara en otras clases
    // Esto sera un componente de Spring
    List<Item> itemsInvoice(){
        Product p1 = new Product("Camara Sony", 1000);
        Product p2 = new Product("Pantalla 32 Pulgadas", 500);
        Product p3 = new Product("Bicicleta Bianchi 26", 1200);
        return  Arrays.asList(
                new Item(p1, 2), //Se agrega el objeto y la cantidad
                new Item(p2, 4),
                new Item(p3, 1)
        );
    }

    @Bean //("default") // Se le asigna un nombre al Bean, si se usa @Qualifier en la respectiva inyeccion se debe usar este nombre
    @Primary // Hace que se tome esta información como la principal
    List<Item> itemsInvoiceOficina(){
        Product p1 = new Product("Monitor Asus 24", 700);
        Product p2 = new Product("Notebook Rzer", 2400);
        Product p3 = new Product("Impresora HP", 800);
        Product p4 = new Product("Silla Herman Miller", 10000);
        return  Arrays.asList(
                new Item(p1, 4), //Se agrega el objeto y la cantidad
                new Item(p2, 3),
                new Item(p3, 1),
                new Item(p4, 5)
        );
    }
}
