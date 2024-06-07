package sb.s4.uam.factura.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@NoArgsConstructor
@Data
@Component
@SessionScope // Se crea un nuevo objeto por cada sesión, es decir por cada vez que se inicia una sesión se creara un nuevo objeto
public class Client {
    @Value("${client.name}") // Tomamos los datos desde el archivo data.properties
    private String name;

    @Value("${client.lastname}")
    private String lastname;

}
