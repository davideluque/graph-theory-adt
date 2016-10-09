/**
 * 
 */

public class Vertice
{
  private String id;
  private double peso;
  
  public Vertice(String id, double peso) {
    this.id = id;
    this.peso = peso;
  }

  public double getPeso() {
    return this.peso;
  }

  public String getId() {
    return this.id;
  }

  public String toString() {
    return "Este vértice tiene por id: " + id + "y su peso es: " + peso; 
  }
}