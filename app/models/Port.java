package models;

import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.Collection;
import java.util.function.Function;

import com.sun.javafx.beans.IDProperty;


/**
 * Presentation object used for displaying data in a template.
 *
 * Note that it's a good practice to keep the presentation DTO,
 * which are used for reads, distinct from the form processing DTO,
 * which are used for writes.
 */

@Entity
@Table(name = "ports")
public class Port {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;
    public String name;
    public Double lon;
    public Double lat;
    public Double height;
    public String land;
    public String town;

    public Port(int id, String name, Double lon, Double lat, Double height, String land, String town){
        this.id = id;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.height = height;
        this.land = land;
        this.town = town;
    }
}
