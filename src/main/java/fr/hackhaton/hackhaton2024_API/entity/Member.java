package fr.hackhaton.hackhaton2024_API.entity;

import jakarta.persistence.*;
import lombok.*;

// Nota: Une entité correspondra à une table dans la base de données

// Les quatres annotations Lombok ci-dessous permettent de se passer d'écrire 
// les getter/setters, constructeurs, etc....
@Builder    
@EqualsAndHashCode
@Data
@AllArgsConstructor

@Entity // Annotation obligatoire qui indique à Spring que la classe est une entité
@Table(name = "member") // Dans la base de données s'appelera "member"
public class Member {

    @Id // Annotation obligatoire qui indique que c'est la clé primaire dans la table
    @GeneratedValue(strategy = GenerationType.AUTO) // L'id sera attribué automatiquement
    private Integer id;

    private String firstName;
}
