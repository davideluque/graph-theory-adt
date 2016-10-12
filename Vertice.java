/******************************************************************************
* Universidad Simon Bolivar
* Laboratorio de Algoritmos y Estructuras III - CI2693
* Proyecto 1:
*   Implementacion de TADs.
* Autores: 
*   - David Cabeza 13-10191
*   - Fabiola Martinez 13-10838
* Profesor:
*   - Ivette Carolina Martinez
******************************************************************************/

/**
* @author David Cabeza
* @author Fabiola Martinez 
*/

public class Vertice
{
  private String id;
  private double peso;
  
  /**
  *   - Constructor Vertice: crea un vertice en un grafo.
  *   @param - Parametros de Entrada: String id (identificador) y peso del ver-
  * tice.
  *   - Orden de ejecucion: constante O(1).
  */
  public Vertice(String id, double peso) {
    this.id = id;
    this.peso = peso;
  }

  /**
  *   - Metodo getPeso: retorna el peso de un vertice.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: real que indica el peso de un vertice.
  *   - Orden de ejecucion: constante O(1).
  */
  public double getPeso() {
    return this.peso;
  }

  /**
  *   - Metodo getId: retorna el id (identificador) de un vertice.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: string que indica el id (identificador) de 
  * un vertice.
  *   - Orden de ejecucion: constante O(1).
  */
  public String getId() {
    return this.id;
  }

  /**
  *   - Metodo toString: devuelve la representacion de un vertice como un string.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: string que representa un vertice.
  *   - Orden de ejecucion: constante O(1).
  */
  public String toString() {
    return "id: " + id + "," + " peso: " + peso; 
  }
}