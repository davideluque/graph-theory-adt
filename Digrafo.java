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

public class Digrafo implements Grafo
{   
    private int nVertices;
    private int nLados;
    private Hashtable<String, LinkedList<Vertice>> adyacencias;
    private ArrayList<Arco> arcos;
    private ArrayList<Vertice> lvertices;

    /**
    *   - Constructor Digrafo: Construye un digrafo nuevo.
    *   - Orden de ejecución: Constante O(1)
    */
    public Digrafo() {
        // Inicializa los valores del grafo dirigido.
        nVertices = 0;
        nLados = 0;
        adyacencias = new Hashtable<String, LinkedList<Vertice>>();
        arcos = new ArrayList<Arco>();
        lvertices = new ArrayList<Vertice>();
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

        // Numero de vértices del grafo a cargar y número de lados que serán utilizados en el ciclo.        
        nVx = Integer.parseInt(sc.next());
        nLx = Integer.parseInt(sc.next());

        // Iteramos sobre los vertices y los añadimos
        String vId;
        double vP;
        for (int i = 0; i < nVx; i++){
            vId = sc.next();
            vP = Double.parseDouble(sc.next());
            agregarVertice(vId, vP);
        }

        // Itera sobre los arcos y los anade
        String aId;
        String vIni;
        String vFin;
        double aP;
        for (int j = 0; j < nLx; j++){
            aId = sc.next();
            vIni = sc.next();
            vFin = sc.next();
            aP = Double.parseDouble(sc.next());

            agregarArco(aId, aP, vIni, vFin);
        }
        return true;
    }

    /**
    *   - Metodo numeroDeVertices: indica el numero de vertices que posee el 
    * grafo.
    *   - Parametros de Entrada: no posee.
    *   @throws - Parametro de Salida: entero que indica el numero de vertices 
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
            lvertices.add(v);
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
        // Verificamos si el vertice ya existia
        if (!estaVertice(id)){
            // Inicializamos una lista vacia que contendra las adyacencias del vertice
            LinkedList emptyList = new LinkedList();

            // Anadimos la lista vacia
            adyacencias.put(id, emptyList);

            // Se crea un objeto con los parametros dados
            Vertice v = new Vertice(id, peso);

            // Anadimos el vertice a la lista de vertice y aumentamos el contador
            lvertices.add(v);
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
        for (Vertice v : lvertices){
            if (v.getId().equals(id)){
                return v;
            }
        }
        // En caso de que se termine el ciclo y no se encuentre se lanza la
        // excepcion.
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
        for (Vertice v : lvertices){
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
    public boolean estaLado(String u, String v){
        /* Iteramos sobre el arreglo de arcos para verificar si los extremos
        * inicial y final concuerdan.
        */
        for (Arco a : arcos) {
            if (a.getExtremoInicial().getId().equals(u) && a.getExtremoFinal().getId().equals(v)){
                return true;
            }
        }
        // No concuerdan, retornamos falso.
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
        // Variable eliminado en donde se almacenara el objeto a eliminar
        Object eliminado;
    
        // 1. Se procede a eliminar la lista de adyacencias del vertice
        eliminado = adyacencias.remove(id);

        // Si el vertice no estaba en la lista de adyacencias, retorna falso
        if (eliminado == null){
            return false;
        }
        // Si se elimino la lista de adyacencias de ese vertice entonces:

        // 2. Si hay conexiones con dicho vertice, eliminamos el vertice de 
        // las adyacencias de los demas

        // Si el vertice solo es extremo inicial, aca no sucede nada porque  
        // la eliminacion se da al eliminar su lista de adyacencias nada mas.
        for (int i = 0; i < nLados; i++){
            if(arcos.get(i).getExtremoFinal().getId().equals(id)){
                adyacencias.get(arcos.get(i).getExtremoInicial().getId()).remove(obtenerVertice(id));
                arcos.remove(i);
            }
        }

        // 3. Eliminamos el vertice de la lista de vertices
        for (int i = 0; i < nVertices; i++){
            if(lvertices.get(i).getId().equals(id)){
                lvertices.remove(i);
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
        for (Vertice v : lvertices) {
            listaDeVertices.add(v);
        }
        return listaDeVertices;
    }


    /**
    *   - Metodo lados: retorna lista con los lados del grafo.
    *   - Parametros de Entrada: no posee.
    *   @throws - Parametro de Salida: lista con los lados del grafo.
    *   - Orden de ejecucion: Lineal O(|E|).
    */    
    public List<Lado> lados() {
        // Inicializamos la lista que contendra los lados.
        List<Lado> listaDeLados = new LinkedList<Lado>();

        // Iteramos sobre cada lado y lo anadimos a la lista.
        for (Arco a : arcos){
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
    *   - Orden de ejecucion: Lineal.
    */
    public int grado(String id) {
        if (!estaVertice(id)){
            throw new NoSuchElementException();
        }
        return gradoInterior(id) + gradoExterior(id);
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

        // Aqui guardaremos los adyacentes que son los extremos finales del id
        // mas aquellos en los que el id es extremo final
        List<Vertice> listaDeAdyacentes = new LinkedList<Vertice>();
        
        // Esta lista contiene todos los elementos que son extremos finales
        // del id dado.
        LinkedList<Vertice> lista = adyacencias.get(id);

        // Anadimos a la lista de adyacentes todos los que son extremo final
        // del id dado.
        for(int i = 0; i < lista.size(); i++){
            listaDeAdyacentes.add(lista.get(i));
        }

        // Guardamos el conjunto de claves para iterar sobre la tabla
        Enumeration<String> conjunto_claves = adyacencias.keys();

        // Iteramos sobre todas las listas que no sean la del id dado ya que
        // sus adyacentes ya los agregamos
        for (String clave : Collections.list(conjunto_claves)){
            if (!clave.equals(id)){
                // Vemos si el vertice con el id dado esta en la lista de la 
                // clave que estamos evaluando.
               if(adyacencias.get(clave).contains(obtenerVertice(id))){
                // Si esta, lo anadimos entonces a la lista de adyacentes
                    listaDeAdyacentes.add(obtenerVertice(clave));
               }
            }
        }

        return listaDeAdyacentes;
    }
 
    /**
    *   - Metodo incidentes: Retorna los lados incidentes al vertice con identi-
    *   ficador id del grafo.
    *   @param - Parametros de Entrada: string id, identificador del vertice.
    *   @throws - Parametro de Salida: en caso de tener vertice retorna una lis-
    * ta que contiene los lados incidentes a dicho vertice del grafo, de lo con-
    * trario retorna la excepcion NoSuchElementException.
    *   - Orden de ejecucion: Lineal O().   
    */
    public List<Lado> incidentes(String id) {

        // Creamos una lista que contendra los incidentes
        List<Lado> listaDeIncidentes = new LinkedList<Lado>();
        
        // Iteramos sobre todos los arcos para determinar si incide ya sea
        // como extremo inicial o extremo final en el vertice.
        for (Arco a : arcos){
            if(a.getExtremoInicial().getId().equals(id) || a.getExtremoFinal().getId().equals(id)){
                // Si incide, lo anadimos
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

    public String toString() {
        String stringRepresentation = "";

        stringRepresentation += "--- REPRESENTACION GRAFO DIRIGIDO --- \n";
        if (lvertices.size() > 0){
            stringRepresentation += "-- VERTICES --\n";
            for (Vertice v : lvertices){
                stringRepresentation += "Vertice: " + v.toString() + ", ";
            }
            stringRepresentation += "\n -- ARCOS -- \n";
            for (Arco a : arcos){
                stringRepresentation += a.toString() + ", ";
            }
            return stringRepresentation;    
        }
    else {
            return "No hay vertices en este grafo, pruebe cargando un grafo o introduciendo vertices en el";
        }
        
    }

    /**
    *   - Metodo agregarArco: Agrega un nuevo arco con identificador id si nin-
    * gun arco del grafo posee el mismo identificador.
    *   @param - Parametros de Entrada: Objeto de tipo arco.
    *   @throws - Parametro de Salida: booleano true en caso de agregar el arco 
    * en el grafo, de lo contrario retorna false.
    *   - Orden de ejecucion: constante O(n).
    */
    public boolean agregarArco(Arco a) {

        // Revisamos si el arco ya se encuentran en la lista de arcos
        for (Arco b : arcos){
            if(a.getId().equals(b.getId())){
                System.out.println("El arco que intento agregar ya existe");
                return false;
            }
        }
    
         // Anadimos el vertice final a la lista de adyacencias del vertice inicial        
        adyacencias.get(a.getExtremoInicial().getId()).add(a.getExtremoFinal());
        
        // Anadimos el arco a la lista de arcos
        arcos.add(a);

        // Aumentamos el contador de arcos 
        nLados++;

        System.out.println("Se agrego el arco " + a.getExtremoInicial().getId() + "->" + a.getExtremoFinal().getId());

        return true;

    } 

    /**
    *   - Metodo agregarArco: agrega una arci con identificador id si nin-
    * guna arco del grafo posee el mismo identificador.
    *   @param - Parametros de Entrada: string id (identificador) y peso de ver-
    * tices, u y v (dos objetos tipo vertice).    
    *   @throws - Parametro de Salida: booleano true en caso de agregar el arco 
    * en el grafo, de lo contrario retorna false.
    *   - Orden de ejecucion: constante O(n).
    */
    public boolean agregarArco(String id, double peso, String eInicial, String eFinal){
        // Revisamos si el arco ya se encuentran en la lista de arcos
        Arco a = new Arco(id, peso, obtenerVertice(eInicial), obtenerVertice(eFinal));
        for (Arco b : arcos){
            if(id.equals(b.getId())){
                System.out.println("El arco que intento agregar ya existe");
                return false;
            }
        }
    
         // Anadimos el vertice final a la lista de adyacencias del vertice inicial        
        adyacencias.get(eInicial).add(a.getExtremoFinal());
        
        // Anadimos el arco a la lista de arcos
        arcos.add(a);

        // Aumentamos el contador de arcos 
        nLados++;

        System.out.println("Se agrego el arco " + a.getExtremoInicial().getId() + "->" + a.getExtremoFinal().getId());

        return true;
    }

    /**
    *   - Metodo gradoInterior: Retorna el grado interior de un vertice con i-
    * dentificador id en el grafo.
    *   @param - Parametros de Entrada: string id (identificador) del vertice.
    *   @throws - Parametro de Salida: Entero que representa el grado interno de 
    * un vertice, en caso de no haber vertice retorna la excepcion NoSuchEle-
    * mentException.
    *   - Orden de ejecucion: Lineal.
    */
    public int gradoInterior(String id) {

        if (!estaVertice(id)){
            throw new NoSuchElementException();
        }

        // Inicializamos el grado interno del vertice;
        int gradoInt = 0;

        // Guardamos el conjunto de claves para iterar sobre la tabla
        Enumeration<String> conjunto_claves = adyacencias.keys();

        // Iteramos sobre todas las listas que contengan al vertice y sumamos
        // uno a su grado interno.
        for (String clave : Collections.list(conjunto_claves)){
            if(adyacencias.get(clave).contains(obtenerVertice(id))){
                gradoInt++;
            }
        }

          return gradoInt;
    }

    /**
    *   - Metodo gradoExterior: Retorna el grado exterior de un vertice con iden-
    * tificador id en el grafo.
    *   @param - Parametros de Entrada: string id, identificador del vertice.
    *   @throws - Parametro de Salida: entero que representa el grado exterior 
    * de un vertice, en caso de no haber vertice retorna la excepcion NoSuchEle-
    * mentException.
    *   - Orden de ejecucion: Constante.
    */
    public int gradoExterior(String id) {

        if (!estaVertice(id)){
            throw new NoSuchElementException();
        }

        // Inicializamos el grado exterior del vertice
        int gradoExt = 0;

        // El tamano de la lista de adyacencias del vertice es su grado ext
        gradoExt = adyacencias.get(id).size();

        return gradoExt;
    }

      /**
      *   - Metodo sucesores: retorna una lista con los sucesores de un vertice con
      * identificador id.
      *   @param - Parametros de Entrada: string id, identificador del vertice.
      *   @throws - Parametro de Salida: lista de vertices sucesores al vertice con
      * identificador id, en caso de no haber vertice retorna la excepcion NoSuchE-
      * lementException.
      *   - Orden de ejecucion: Lineal O(|E|).
      */
    public List<Vertice> sucesores(String id) {
        
        if (!estaVertice(id)){
            throw new NoSuchElementException();
        }

        // Inicializamos la lista que contendra los sucesores
        List<Vertice> listaDeSucesores = new LinkedList<Vertice>();

        for (int i = 0; i < adyacencias.get(id).size(); i++){
            listaDeSucesores.add(adyacencias.get(id).get(i));
        }

        return listaDeSucesores;
    }

      /**
      *   - Metodo predecesores: retorna una lista con los predecesores de un verti-
      * ce con identificador id.
      *   @param - Parametros de Entrada: string id, identificador del vertice.
      *   @throws - Parametro de Salida: lista de vertices predecesores al vertice 
      * con identificador id, en caso de no haber vertice retorna la excepcion NoSu-
      * chElementException.
      *   - Orden de ejecucion: Lineal O(|V| * |E|).
      */
    public List<Vertice> predecesores(String id) {

        // Verificamos si esta el vertice
        if (!estaVertice(id)){
            throw new NoSuchElementException();
        }

        // Inicializamos la lista de predecesores
        List<Vertice> listaDePredecesores = new LinkedList<Vertice>();

        // Guardamos el conjunto de claves para iterar sobre la tabla
        Enumeration<String> conjunto_claves = adyacencias.keys();

        // Iteramos sobre todas las listas que contengan al vertice y sumamos
        // uno a su grado interno.
        for (String clave : Collections.list(conjunto_claves)){
            if (adyacencias.get(clave).contains(obtenerVertice(id))){
                listaDePredecesores.add(obtenerVertice(clave));
            }
        }
        return listaDePredecesores;

    }

    /**
    *   - Metodo eliminarArco: elimina un arco con identificador id del grafo.
    *   @param - Parametros de Entrada: string id, identificador del arco.
    *   @throws - Parametro de Salida: true en caso de ser eliminada el arco del 
    * grafo de lo contrario retorna false.
    *   - Orden de ejecucion:
    */
    public boolean eliminarArco(String id) {
    
        for (int i = 0; i < nLados; i++){
            if(arcos.get(i).getId().equals(id)){
                // Eliminamos el arco de la lista de arcos
                arcos.remove(i);

                // Eliminamos la conexion en la lista de adyacencias
                adyacencias.get(arcos.get(i).getExtremoInicial().getId()).remove(arcos.get(i).getExtremoFinal());
                
                nLados--;   
                return true;
            }
        }

        // No se encontro el arco.
            return false;
    }

    /**
    *   - Metodo obtenerArco: devuelve el arco que tiene el identificador id.
    *   @param - Parametros de Entrada: string id, identificador del lado.
    *   @throws - Parametro de Salida: Retorna el arco en caso de encontrarlo
    *   de lo contrario lanza la excepcion NoSuchElementException.
    *   - Orden de ejecucion:
    */
    public Arco obtenerArco(String id) {
        // Iteramos sobre la lista de arcos
        for (Arco a : arcos){
            if (a.getId().equals(id)){
                return a;
            }
        }
        throw new NoSuchElementException();
    }	

    public static void main(String[] args) {
        Digrafo g = new Digrafo();
        g.cargarGrafo("Entrada.txt");
        Vertice v = new Vertice("A", 1.5);
        g.agregarVertice(v);
        Arco a = new Arco("e5", 1.5, v, v);
        g.agregarArco(a);
        System.out.println(g.predecesores("D"));
        System.out.println(g);
        System.out.println(g.incidentes("B"));     
    }
}