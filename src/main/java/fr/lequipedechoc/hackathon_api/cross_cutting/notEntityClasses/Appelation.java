package fr.lequipedechoc.hackathon_api.cross_cutting.notEntityClasses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Appelation {
    private String intitule; // Free entered text
    private final String identifiant="123456"; // always "12345600"
    private String contexte;
}
