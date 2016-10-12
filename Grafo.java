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

import java.util.*;

public interface Grafo
{
    
    public boolean cargarGrafo(String dirArchivo);
    
    public int numeroDeVertices();

    public int numeroDeLados();
    
    public boolean agregarVertice(Vertice v);

    public boolean agregarVertice(String id, double peso);
    
    public Vertice obtenerVertice(String id);

    public boolean estaVertice(String id);

    public boolean estaLado(String u, String v);

    public boolean eliminarVertice(String id);

    public List<Vertice> vertices();

    public List<Lado> lados();

    public int grado(String id);

    public List<Vertice> adyacentes(String id);
 
    public List<Lado> incidentes(String id);

    public Object clone();

    public String toString();
}