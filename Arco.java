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

public class Arco extends Lado
{
  private Vertice extremoInicial;
  private Vertice extremoFinal;
  
  /**
  *   - Constructor Arco: construye un arco.
  *   @param - Parametros de Entrada: string id (identificador), peso asociado al arco,
  *   vertices u y v (objetos tipo vertice).
  *   - Orden de ejecucion: constante O(1).
  */
  public Arco(String id, double peso, Vertice extremoInicial, Vertice extremoFinal) {
    // Para acceder a los atributos de la super clase
    super(id, peso);

    id = id;
    peso = peso;
    this.extremoInicial = extremoInicial;
    this.extremoFinal = extremoFinal;
  }

  /**
  *   - Metodo getExtremoInicial: retorna el vertice que es extremo incial del arco.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: objeto tipo vertice que representa el ver-
  * tice extremo inicial de un arco.
  *   - Orden de ejecucion: constante O(1).
  */
  public Vertice getExtremoInicial() {
    return extremoInicial;
  }

  /**
  *   - Metodo getExtremoFinal: retorna el vertice que es extremo final del arco.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: objeto tipo vertice que representa el ver-
  * tice que es extremo final de un arco.
  *   - Orden de ejecucion: constante O(1).
  */
  public Vertice getExtremoFinal() {
    return extremoFinal;
  }

  /**
  *   - Metodo toString: retorna la representacion de un arco en string.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: string que representa el arco de un grafo.
  *   - Orden de ejecucion: constante O(1)
  */
  public String toString() {
    return extremoInicial.getId() + "->" + extremoFinal.getId();

  }
}