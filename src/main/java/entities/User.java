package entities;

import java.security.SecureRandom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;


@Getter
@Setter

//Constructor sin parámetros y constructor con todos los parámetros. Son usados por hybernate para mapear entidades correctamente
@AllArgsConstructor
@NoArgsConstructor

@Table(uniqueConstraints = @UniqueConstraint(columnNames = "userId", name = "userIdUniqueConstraint"))
public class User {

    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String nombre;
    private String password;

    //constructor con un solo parámetro. Puede ser útil para que hibernate cree instancias de esta clase
    public User (String nombre) {
        this.nombre = nombre;
       
    }

    //constructor con 2 parámetros. Se usa para crear usuarios nuevos desde el register                                                                                                                                                                          
    public User(String nombre, String password) {
        this.userId = generateRandomUserId();
        this. nombre = nombre;
        this.password = password;
    }

    //genera una UserId randomizada. Estaría bien crear una función que revise si ya existe ese número en la base de datos, para que no sean iguales (porque si son iguales no los vamos a poder guardar)
    private String generateRandomUserId() {
        int userIdLength = 8; // Longitud deseada para la userId
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[userIdLength];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }


}
