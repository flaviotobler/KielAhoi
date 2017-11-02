package models;

/**
 * Presentation object used for displaying data in a template.
 *
 * Note that it's a good practice to keep the presentation DTO,
 * which are used for reads, distinct from the form processing DTO,
 * which are used for writes.
 */
public class Port {
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
