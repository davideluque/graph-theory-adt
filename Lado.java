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

public abstract class Lado{
  private String id;
  private double peso;

  /**
  *   - Constructor Lado: crea un nuevo lado.
  *   @param - Parametros de Entrada: string id (identificador) y peso del ver-
  * tice.
  *   - Orden de ejecucion: constante O(1).
  */
  public Lado(String id, double peso) {
  	this.id = id;
  	this.peso = peso;
  }

  /**
  *   - Metodo getId: obtiene id (identificador) de un lado.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: string que indica el id (identificador) de 
  * un lado.
  *   - Orden de ejecucion: constante O(1).
  */
  public String getId() {
  	return this.id;
  }

  /**
  *   - Metodo getPeso: obtiene peso de un lado.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: entero que indica el peso de un lado.
  *   - Orden de ejecucion: constante O(1).
  */
  public double getPeso() {
  	return this.peso;
  }

  /**
  *   - Metodo toString: devuelve la representacion de un lado como un string.
  *   - Parametros de Entrada: no posee.
  *   @throws - Parametro de Salida: string que representa un lado.
  *   - Orden de ejecucion: constante O(1).
  */
  public abstract String toString();

}