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

public class Arista extends Lado
{
  private Vertice u;
  private Vertice v;
  
  /**
  *   - Constructor Arista: construye una arista.
  *   @param - Parametros de Entrada: string id (identificador) y peso del ver-
  * tice, u y v (objetos tipo vertice).
  *   - Orden de ejecucion: constante O(1).
  */
  public Arista(String id, double peso, Vertice u, Vertice v) {
    // Para acceder a los atributos de la super clase
    super(id, peso);

    id = id;
    peso = peso;
    this.u = u;
    this.v = v;
  }

  /**
  *   - Metodo getExtremo1: retorna un vertice extremo de una arista.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: objeto de tipo vertice, que es el vertice 
  * extremo de una arista.
  *   - Orden de ejecucion: constante O(1).
  */
  public Vertice getExtremo1() {
      return u;
  }

  /**
  *   - Metodo getExtremo2: retorna un vertice extremo de una arista.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: objeto de tipo vertice, que es el extremo 
  * de una arista.
  *   - Orden de ejecucion: constante O(1).
  */
  public Vertice getExtremo2() {
    return v;
  }

  /**
  *   - Metodo toString: devuelve la representacion de una arista como un string.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: string que representa una arista.
  *   - Orden de ejecucion: constante O(1).
  */
  public String toString() {
    return u.getId() + "--" + v.getId();
  }
}