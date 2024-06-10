package proyectos.model;

import javax.persistence.*;

@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigoProyecto;
}