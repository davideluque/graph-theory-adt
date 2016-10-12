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
import java.io.*;

public class GrafoNoDirigido implements Grafo{

    private int nVertices;
    private int nLados;
    private ArrayList<Arista> aristas;
    private ArrayList<Vertice> lVertices;
    private Hashtable<String,LinkedList<Vertice>> adyacencias;
    
    /**
    *   - Constructor GrafoNoDirigido: Construye un nuevo grafo no dirigido.
    *   - Orden de ejecución: Constante O(1)
    */

    public GrafoNoDirigido() {
        // Inicializa los valores del grafo no dirigido.
        nVertices = 0;
        nLados = 0;
        aristas = new ArrayList<Arista>();
        lVertices = new ArrayList<Vertice>();
        adyacencias = new Hashtable<String,LinkedList<Vertice>>();
    }

    /**
    *   - Metodo cargarGrafo: Carga la informacion de un grafo almacenada en un
    *   archivo de texto en el grafo actual.
    *   @param - Parametros de Entrada: nombre del archivo de texto a leer.
    *   @throws - Parametros de Salida: booleano true en caso de haberse cargado 
    * correctamente, false en caso contrario.
    *   - Orden de ejecución:
    */
    public boolean cargarGrafo(String dirArchivo) {
        FileReader archivo;
        Scanner sc;
        int nVx, nLx;

        // Verifica que el archivo sea de formato de texto
        if (!dirArchivo.contains(".txt")){
            System.out.println("El archivo debe ser de formato .txt, intentelo de nuevo");
            return false;
        }

        // Intenta abrir el archivo
        try {
            archivo = new FileReader(dirArchivo);              
            sc = new Scanner(archivo);
        }
        catch (FileNotFoundException e){
            System.out.println("No se pudo encontrar el archivo");
            return false;
        }

        // La lectura fue exitosa, no se produjeron errores.

        // Numero de vertices del grafo a cargar y numero de lados que serán utilizados en el ciclo.        
        nVx = Integer.parseInt(sc.next());
        nLx = Integer.parseInt(sc.next());

        // Iteramos sobre los vertices y los anadimos
        String vId;
        double vP;
        for (int i = 0; i < nVx; i++){
            vId = sc.next();
            vP = Double.parseDouble(sc.next());
            agregarVertice(vId, vP);
        }
        // Itera sobre los aristas y las anade
        String aId;
        String vIni;
        String vFin;
        double aP;
        for (int j = 0; j < nLx; j++){
            aId = sc.next();
            vIni = sc.next();
            vFin = sc.next();
            aP = Double.parseDouble(sc.next());

            agregarArista(aId, aP, vIni, vFin);
        }

        return true;    
    }
    
    /**
    *   - Metodo numeroDeVertices: indica el numero de vertices que posee el 
    * grafo.
    *   - Parametros de Entrada: no posee.
    *  @throws - Parametro de Salida: entero que indica el numero de vertices 
    * del grafo.
    *   - Orden de ejecucion: constante O(1).
    */
    public int numeroDeVertices() {

        return nVertices;
    }

    /**
    *   - Metodo numeroDeLados: indica el numero de lados que posee el grafo.
    *   - Parametros de Entrada: no posee.
    *   @throws - Parametro de Salida: entero que indica el numero de lados 
    *   del grafo.
    *   - Orden de ejecucion: constante O(1).
    */
    public int numeroDeLados() {

        return nLados;
    }
   
    /**
    *   - Metodo agregarVertice: agrega un vertice si este no existe en el gra-
    * fo.
    *   @param - Parametros de Entrada: objeto vertice a agregar.
    *   @throws - Parametro de Salida: booleano true de ser agregado el vertice, 
    * retorna false en caso contrario.
    *   - Orden de ejecucion: constante O(1).
    */
    public boolean agregarVertice(Vertice v) {
        // Verificamos si el vertice ya existia
        if (!estaVertice(v.getId())){

            // Inicializamos una lista vacia que contendra las adyacencias del vertice
            LinkedList emptyList = new LinkedList();

            // Anadimos la lista vacia
            adyacencias.put(v.getId(), emptyList);

            // Anadimos el vertice a la lista de vertice y aumentamos el contador
            lVertices.add(v);
            nVertices++;
           
            System.out.println("El vertice " + v.getId() + " se anadio al grafo.");
            return true;
        }
        // El vertice ya existia, se informa y retorna falso
        else {
            System.out.println("El vertice " + v.getId() + " ya existe en el grafo.");
            return false;
        }
    }
    
    /**
    *   - Metodo agregarVertice: agrega un vertice si este no existe en el gra-
    * fo. 
    *   @param - Parametros de Entrada: String id y peso del vertice a agregar.
    *   @throws - Parametro de Salida: booleano true de ser agregado el vertice, 
    * retorna falso en caso contrario.
    *   - Orden de ejecucion: constante O(1).
    */
    public boolean agregarVertice(String id, double peso) {
        // Almacenaremos el resultado de anadir el vertice en la variable result
        if (!estaVertice(id)){
            // Inicializamos una lista vacia que contendra las adyacencias del vertice
            LinkedList emptyList = new LinkedList();

            // Anadimos la lista vacia
            adyacencias.put(id, emptyList);

            // Se crea un objeto con los parametros dados
             Vertice v = new Vertice(id, peso);

            // Anadimos el vertice a la lista de vertice y aumentamos el contador
            lVertices.add(v);
            nVertices++;
           
            System.out.println("El vertice " + id + " se anadio al grafo.");
            return true;
        }
        // El vertice ya existia, se informa y retorna falso
        else {
            System.out.println("El vertice " + id + " ya existe en el grafo.");
            return false;
        } 
    }

    /**
    *   - Metodo obtenerVertice: devuelve el vertice contenido en el grafo que 
    * posee el identificador id. 
    *   @param - Parametros de Entrada: string id, identificador del vertice.
    *   @throws - Parametro de Salida: retorna objeto tipo vertice que posee el 
    * identificador id en caso de existir, de lo contrario retorna la 
    * excepcion NoSuchElementException.
    *   - Orden de ejecucion: Peor caso recorre toda la matriz de vertices, O(|V|).
    */
    public Vertice obtenerVertice(String id) {
        // Iteramos sobre el arreglo de vertices y verificamos si hay match.
        for (Vertice v : lVertices){
            if (v.getId().equals(id)){
                return v;
            }
        }
        // En caso de que se finalice el ciclo y no se encuentre se lanza la
        // excepcion
        throw new NoSuchElementException();
    }

    /**
    *   - Metodo estaVertice: indica si se encuentra o no un vertice con identi-
    * ficador id en el grafo.
    *   @param - Parametros de Entrada: string id del vertice a verificar si 
    * esta.
    *   @throws- Parametro de Salida: booleano true en caso de estar el vertice 
    * en el grafo, retorna false en caso contrario.
    *   - Orden de ejecucion: Peor caso, recorre toda la lista de vertices, O(|V|).
    */
    public boolean estaVertice(String id) {
        // Iteramos sobre el arreglo de vertices y devolvemos true en caso de
        // ser encontrado.
        for (Vertice v : lVertices){
            if(v.getId().equals(id)){
                return true;
            }
        }
        // Se finalizo el ciclo y no se encontro, se devuelve falso.
        return false;
    }
    /**
    *   - Metodo estaLado: indica si un lado pertenece a un grafo.
    *   @param - Parametros de Entrada: Dos strings u, v que son identificadores
    *  de vertices.
    *   @throws - Parametro de Salida: Retorna booleano true si encuentra el lado
    *   en el grafo.
    *   - Orden de ejecucion: Peor caso, chequea toda la lista de arcos O(|E|).
    */
    public boolean estaLado(String u, String v) {
        /* Iteramos sobre el arreglo de aristas para verificar si los extremos 1 y 2
        * concuerdan ya sea con u o con v.
        */
        for (int i = 0; i < nLados; i++){
            if(aristas.get(i).getExtremo1().getId().equals(u) && aristas.get(i).getExtremo2().getId().equals(v)) {
                return true;
            }
            else if (aristas.get(i).getExtremo1().getId().equals(v) && aristas.get(i).getExtremo2().getId().equals(u)) {
                return true;
            }
       }
        // Fin del ciclo y no concuerdan, se retorna falso, 
        return false;     
    }

    /**
    *   - Metodo eliminarVertice: elimina un vertice con identificador id del 
    * grafo.
    *   @param - Parametros de Entrada: string id, identificador del vertice.
    *   @throws - Parametro de Salida: booleano true en caso de eliminar el ver-
    * tice con identificador id, de lo contrario retorna false.
    *   - Orden de ejecucion: 
    */
    public boolean eliminarVertice(String id) {

        // Verificamos si el vertice esta
        if (!estaVertice(id)){
            return false;
        }

        // Si esta entonces

        // Veamos si tiene aristas con otros vertices
        if (adyacencias.get(id).size() == 0){
            // No tiene aristas, solo eliminamos su slot en la tabla
            adyacencias.remove(id);
            return true;

        }
        // Tiene aristas, utilicemos el id de cada una para eliminar a este vertice
        // de sus listas de adyacencias.
        String[] claves;
        claves = adyacencias.get(id).toArray(new String[adyacencias.get(id).size()]);

        // Accedemos a cada slot de la clave y eliminamos de su lista el vertice
        for (String clave : claves) {
            adyacencias.get(clave).remove(obtenerVertice(id));
        }

        // Eliminamos las aristas que conecten con el vertice
        for (int i = 0; i < nLados; i++){
            if(aristas.get(i).getExtremo1().getId().equals(id) || aristas.get(i).getExtremo2().getId().equals(id)){
                aristas.remove(i);
            }
        }

        // Eliminamos la propia lista del vertice
        adyacencias.remove(id);

        // Eliminamos el vertice de la lista de vertices
        for (int i = 0; i < nVertices; i++){
            if(lVertices.get(i).getId().equals(id)){
                lVertices.remove(i);
                break;
            }
        }

        nVertices--;
        return true;
    }

    /**
    *   - Metodo vertices: retorna una lista con los vertices del grafo.
    *   - Parametros de Entrada: no posee.
    *   @throws - Parametro de Salida: lista con los vertices del grafo.
    *   - Orden de ejecucion: Lineal O(|V|).
    */
    public List<Vertice> vertices() {
        // Inicializamos la lista que contendra los vertices.
        List<Vertice> listaDeVertices = new LinkedList<Vertice>();

        // Iteramos sobre cada vertice y lo anadimos a la lista
        for (Vertice v : lVertices) {
            listaDeVertices.add(v);
        }
        return listaDeVertices;
    }

    /**
    *   - Metodo lados: retorna lista con los lados del grafo.
    *   - Parametros de Entrada: no posee.
    *   @throws - Parametro de Salida: lista con los lados del grafo.
    *   - Orden de ejecucion: constante O(n).
    */
    public List<Lado> lados() {
        // Inicializamos la lista que contendra los lados.
        List<Lado> listaDeLados = new LinkedList<Lado>();

        // Iteramos sobre cada lado y lo anadimos a la lista.
        for (Arista a : aristas){
            listaDeLados.add(a);
        }
        return listaDeLados;
    }

    /**
    *   - Metodo grado: calcula el grado del vertice con identificador id del 
    * grafo.
    *   @param - Parametros de Entrada: string id, identificador del vertice.
    *   @throws - Parametro de Salida: de contener un vertice retorna entero que 
    * representa el grado del vertice de un grafo, de lo contrario retorna la 
    * excepcion NoSuchElementException.
    *   - Orden de ejecucion: Lineal O(|E|).
    */
    public int grado(String id) {

        if(!estaVertice(id)){
            throw new NoSuchElementException();
        }

        return adyacencias.get(id).size();
    }

    /**
    *   - Metodo adyacentes: Retorna una lista con los vertices adyacentes al 
    *   identificador del vertice dado como parametro.
    *   @param - Parametros de Entrada: string id, identificador del vertice.
    *   @throws - Parametro de Salida: en caso de tener vertice retorna una lis-
    * ta que contiene los vertices ayacentes a dicho vertice con identicador id 
    * del grafo, de lo contrario retorna la excepcion NoSuchElementException.
    *   - Orden de ejecucion: Lineal.
    */
    public List<Vertice> adyacentes(String id) {

        // Si no esta el vertice lanzamos la excepcion
        if(!estaVertice(id)){
            throw new NoSuchElementException();
        }

        // Aqui guardaremos los adyacentes que es simplemente la lista
        // de adyacencias del vertice
        List<Vertice> listaAdyacentes = new LinkedList<Vertice>();

        // Esta lista contiene todos los elementos adyacentes al vertice con id dado
        LinkedList<Vertice> lista = adyacencias.get(id);

        // Anadimos a la lista de adyacentes la lista del id dado.
        for(int i = 0; i < lista.size(); i++){
            listaAdyacentes.add(lista.get(i));
        }

        return listaAdyacentes;
    }
 
    /**
    *   - Metodo incidentes: Retorna los lados incidentes al vertice con identi-
    *   ficador id del grafo.
    *   @param - Parametros de Entrada: string id, identificador del vertice.
    *   @throws - Parametro de Salida: en caso de tener vertice retorna una lis-
    * ta que contiene los lados incidentes a dicho vertice del grafo, de lo con-
    * trario retorna la excepcion NoSuchElementException.
    *   - Orden de ejecucion: constante O(n).   
    */
    public List<Lado> incidentes(String id) {

        // Creamos una lista que contendra los incidentes
        List<Lado> listaDeIncidentes = new LinkedList<Lado>();
        
        // Iteramos sobre todas las aristas para determinar cuales aristas
        // inciden sobre el vertice
        for (Arista a : aristas){
            if(a.getExtremo1().getId().equals(id) || a.getExtremo2().getId().equals(id)){
                // Si incides la anadimos
                listaDeIncidentes.add(a);
            }
        }
        return listaDeIncidentes;

    }

    /**
    *   - Metodo clone: Crea un grafo con la misma composicion que el grafo 
    * de entrada.
    *   @param - Parametros de Entrada: no posee.
    *   @throws - Parametro de Salida: copia del grafo.
    *   - Orden de ejecucion: Lineal
    */
    public Object clone() {
        Object k;
        k = new Object();
        return k;
    }

    /**
    *   - Metodo toString: retornar la representacion del grafo en una cadena de 
    * caracteres.
    *   @param - Parametros de Entrada: no posee.
    *   @throws - Parametro de Salida: cadena de carateres de la representacion 
    * del grafo.
    *   - Orden de ejecucion:
    */
    public String toString() {

        String stringRepresentation = "";
        stringRepresentation += "--- REPRESENTACION GRAFO NO DIRIGIDO --- \n";

        if (lVertices.size() > 0){
            stringRepresentation += "-- VERTICES --\n";
            for (Vertice v : lVertices){
                stringRepresentation += "Vertice: " + v.toString() + ", ";
            }
            stringRepresentation += "\n -- ARISTAS -- \n";
            for (Arista a : aristas){
                stringRepresentation += a.toString() + ", ";
            }
            return stringRepresentation;    
        }
        else{
            return "No hay vertices en este grafo, pruebe cargando un grafo o introduciendo vertices en el";
        }
            
    }

    /**
    *   - Metodo agregarArista: agrega una arista con identificador id si nin-
    * guna arista del grafo posee el mismo identificador.
    *   @param - Parametros de Entrada: objeto de tipo arista.
    *   @throws - Parametro de Salida: true en caso de agregar la arista en el 
    * grafo, de lo contrario retorna false.
    *   - Orden de ejecucion: constante O(n).
    */
    public boolean agregarArista(Arista a) {
        
        // Revisamos si la arista ya se encuentra en la lista de aristas
        for (Arista b : aristas){
            if(a.getId().equals(b.getId())){
                System.out.println("La arista que intento agregar ya existe");
                return false;
            }
        }

        // Anadimos los vertices extremos a las listas de adyacencias
        adyacencias.get(a.getExtremo1().getId()).add(a.getExtremo2());
        adyacencias.get(a.getExtremo2().getId()).add(a.getExtremo1());
        
        // Anadimos la arista a la lista de aristas
        aristas.add(a);

        // Aumentamos el contador de aristas
        nLados++;

        System.out.println("Se agrego la arista " + a.getExtremo1().getId() + "--" + a.getExtremo2().getId());
        
        return true;

    }

    /**
    *   - Metodo agregarArista: agrega una arista con identificador id si nin-
    * guna arista del grafo posee el mismo identificador.
    *   @param - Parametros de Entrada: string id (identificador) y peso de ver-
    * tice, u y v (dos objetos tipo vertice).
    *   @throws - Parametro de Salida: booleano true en caso de agregar la aris-
    * ta en el grafo, de lo contrario retorna false.
    *   - Orden de ejecucion: constante O(n).
    */
    public boolean agregarArista(String id, double peso, String u, String v) {

        // Revisamos si la arista ya se encuentra en la lista de aristas
        Arista a = new Arista(id, peso, obtenerVertice(u), obtenerVertice(v));
        
        for (Arista b : aristas){
            if(id.equals(b.getId())){
                System.out.println("La arista que intento agregar ya existe");
                return false;
            }
        }

        // Anadimos los vertices extremos a las listas de adyacencias
        adyacencias.get(u).add(obtenerVertice(v));
        adyacencias.get(v).add(obtenerVertice(u));
        
        // Anadimos la arista a la lista de aristas
        aristas.add(a);

        // Aumentamos el contador de aristas
        nLados++;

        System.out.println("Se agrego la arista " + u + "--" + v);

        return true;

    }

    /**
    *   - Metodo eliminarArista: elimina una arista con identificador id del 
    * grafo.
    *   @param - Parametros de Entrada: string id, identificador de la arista.
    *   @throws - Parametro de Salida: true en caso de ser eliminada la arista 
    * del grafo de lo contrario retorna false.
    *   - Orden de ejecucion:
    */
    public boolean eliminarArista(String id) {

        for (int i = 0; i < nLados; i++){
            if(aristas.get(i).getId().equals(id)){
                // Eliminamos el arco de la lista de arcos
                aristas.remove(i);

                // Eliminamos la conexion en la lista de adyacencias
                adyacencias.get(aristas.get(i).getExtremo1().getId()).remove(aristas.get(i).getExtremo2());
                adyacencias.get(aristas.get(i).getExtremo2().getId()).remove(aristas.get(i).getExtremo1());
                
                nLados--;

                return true;
            }
        }

        // No se encontro el arco.
            return false;

    }

    /**
    *   - Metodo obtenerArista: devuelve la arista que tiene el identificador id.
    *   @param - Parametros de Entrada: string id, identificador de la arista.
    *   @throws - Parametro de Salida: booleano true en caso de encontrar la a-
    * rista, de o contrario retorna la excepcion NoSuchELementException.
    *   - Orden de ejecucion:
    */  
    public Arista obtenerArista(String id) {
        // Iteramos sobre la lista de aristas
        for (Arista a : aristas){
            if (a.getId().equals(id)){
                return a;
            }
        }
        throw new NoSuchElementException();
    }

    public static void main(String[] args) {
        GrafoNoDirigido g = new GrafoNoDirigido();
        g.cargarGrafo("Entrada.txt");
        System.out.println(g.toString());
    }
}