package fr.lequipedechoc.hackathon_api.entity;

import fr.lequipedechoc.hackathon_api.cross_cutting.enums.ContractTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SavedSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ContractTypeEnum Contract;
    private Integer weeklyDuration;
    private Double salary;
    private String title;
}
