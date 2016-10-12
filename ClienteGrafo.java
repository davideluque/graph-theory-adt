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

import java.util.*;

public class ClienteGrafo {
  public static void main(String [] args) {

  	Scanner scanner = new Scanner(System.in);
  	scanner.useLocale(Locale.US);

		// Menu principal
		System.out.println("Menu Principal de TADs Grafo!");	
		System.out.println("Introduce 1 para Digrafo");
		System.out.println("Introduce 2 para Grafo No Dirigido");
		System.out.println("Introduce 0 para salir");
		String opcion1 = scanner.nextLine();

		// Entramos en opciones de Digrafo
		if ("1".equals(opcion1)) {

			// Creamos Digrafo
			Digrafo D = new Digrafo();

			// Opciones de Digrafo
			while (true) {
			System.out.println("Opciones de Digrafo");
			System.out.println("Introduce el numero de la opcion que desees");			
			System.out.println("1- Cargar grafo");		
			System.out.println("2- Obtener numero de vertices del grafo");
			System.out.println("3- Obtener numero de lados del grafo");
			System.out.println("4- Agregar un vertice");
			System.out.println("5- Obtener un vertice");
			System.out.println("6- Verificar si se encuentra un vertice");
			System.out.println("7- Verificar si se encuentra un lado");
			System.out.println("8- Obtener los vertices del grafo");
			System.out.println("9- Obtener los lados del grafo");
			System.out.println("10- Calcular en grado de un vertice");
			System.out.println("11- Obtener los vertices adyacentes");
			System.out.println("12- Obtener los lados incidentes");
			System.out.println("13- Clonar grafo");
			System.out.println("14- Ver contenido del grafo");
			System.out.println("15- Agregar arco");
			System.out.println("16- Eliminar arco");
			System.out.println("17- Obtener arco");
			System.out.println("18- Calcular grado interior de un vertice");
			System.out.println("19- Calcular grado exterior de un vertice");
			System.out.println("20- Obtener vertices sucesores");
			System.out.println("21- Obtener vertices predecesores");
			System.out.println("22- Eliminar vertice");
			System.out.println("0- Salir");
			String opcion2 = scanner.nextLine();
			
			// Cargamos grafo
			if ("1".equals(opcion2)){ 
				System.out.println("Introduzca nombre del archivo ");
				String nombreArchivo = scanner.nextLine();
				D.cargarGrafo(nombreArchivo);
				System.out.println("Grafo cargado correctamente");
				continue; 
			}			
			// Obtenemos numero de vertices
			else if ("2".equals(opcion2)){
				System.out.println("El numero de vertices del grafo es: ");
				System.out.println(D.numeroDeVertices());
				continue;			
			}
			// Obtenemos numero de lados
			else if ("3".equals(opcion2)){
				System.out.println("El numero de lados del grafo es: ");
				System.out.println(D.numeroDeLados());
				continue;			
			}
			// Agregamos un vertice
			else if ("4".equals(opcion2)){
				System.out.println("Introduzca id del vertice a agregar");
				String id = scanner.nextLine();
				System.out.println("Introduzca peso del vertice a agregar");
				Double peso = scanner.nextDouble();
				D.agregarVertice(id, peso);
				continue;		
			}
			// Obtenemos un vertice
			else if ("5".equals(opcion2)){
				System.out.println("Introduzca el id del vertice a obtener");
				String id = scanner.nextLine();
				System.out.println("Vertice obtenido");
				System.out.println(D.obtenerVertice(id));
				continue;			
			}
			// Buscamos vertice
			else if ("6".equals(opcion2)){
				System.out.println("Intruduzca el id del vertice a verificar si esta");
				String id = scanner.nextLine();				
				System.out.println(D.estaVertice(id));
				continue;		
			}
			// Buscamos lado
			else if ("7".equals(opcion2)){
				System.out.println("Intruduzca el id del vertice incial");
				String id1 = scanner.nextLine();
				System.out.println("Introduzca el id del vertice final");
				String id2 = scanner.nextLine();				
				System.out.println(D.estaLado(id1,id2));
				continue;					
			}
			// Eliminamos vertice
			else if ("22".equals(opcion2)){
				System.out.println("Introduzca el id del vertice a eliminar");
				String id = scanner.nextLine();
				System.out.println(D.eliminarVertice(id));
				continue;			
			}
			// Obtenemos lista de vertices
			else if ("8".equals(opcion2)){
				System.out.println(D.vertices());
				continue;			
			}
			// Obtenemos lista de lados
			else if ("9".equals(opcion2)){
				System.out.println(D.lados());
				continue;		
			}
			// Calculamos grado del vertice
			else if ("10".equals(opcion2)){
				System.out.println("Introduzca el id del vertice a calcular grado");
				String id = scanner.nextLine();
				System.out.println(D.grado(id));
				continue;			
			}
			// Obtenemos vertices adyacentes
			else if ("11".equals(opcion2)){
				System.out.println("Introduzca el id del vertice para buscar sus adyacentes");
				String id = scanner.nextLine();
				System.out.println(D.adyacentes(id));
				continue;			
			}
			// Obtenemos lados incidentes 
			else if ("12".equals(opcion2)){
				System.out.println("Introduzca el id del vertice para buscar sus incidentes");
				String id = scanner.nextLine();
				System.out.println(D.incidentes(id));		
			}
			// Clonamos el grafo 
			else if ("13".equals(opcion2)){
				System.out.println("Grafo clonado");			
			}
			// Contenido del grafo
			else if ("14".equals(opcion2)){
				System.out.println(D);
				continue;			
			}
			// Agregamos arco
			else if ("15".equals(opcion2)){
				System.out.println("Introduzca id del arco a agregar");
				String id = scanner.nextLine();
				System.out.println("Introduzca peso del arco a agregar");
				Double peso = scanner.nextDouble();
				System.out.println("Introduzca el id del vertice inicial");
				String v1 = scanner.nextLine();
				System.out.println("Introduzca el id del vertice final");
				String v2 = scanner.nextLine();
				D.agregarArco(id, peso, v1, v2);
				continue;					
			}
			// Eliminamos arco
			else if ("16".equals(opcion2)){
				System.out.println("Introduzca id del arco a agregar");
				String id = scanner.nextLine();
				System.out.println(D.eliminarArco(id)); 
				continue;			
			}
			// Obtenemos arco
			else if ("17".equals(opcion2)){
				System.out.println("Introduzca el id del arco a obtener");
				String id = scanner.nextLine();
				System.out.println("Arco obtenido");
				System.out.println(D.obtenerArco(id));
				continue;			
			}
			// Calculamos grado interior
			else if ("18".equals(opcion2)){
				System.out.println("Introduzca el id del vertice a calcular grado interior");
				String id = scanner.nextLine();
				System.out.println(D.gradoInterior(id));
				continue;			
			}
			// Calculamos grado exterior
			else if ("19".equals(opcion2)){
				System.out.println("Introduzca el id del vertice a calcular grado exterior");
				String id = scanner.nextLine();
				System.out.println(D.gradoExterior(id));
				continue;		
			}
			// Obtenemos lista de sucesores
			else if ("20".equals(opcion2)){
				System.out.println("Introduzca el id del vertice para buscar sus sucesores");
				String id = scanner.nextLine();
				System.out.println(D.sucesores(id));
				continue;			
			}
			// Obtenemos lista de predecesores
			else if ("21".equals(opcion2)){
				System.out.println("Introduzca el id del vertice para buscar sus predecedores");
				String id = scanner.nextLine();
				System.out.println(D.predecesores(id));	
				continue;		
			}
			// Salida menu de digrafo
			else if ("0".equals(opcion2)) {
				System.out.println("Salimos");
				break;
			}
						
			}			

		}
		
		// Opciones de grafo no dirigido
		else if ("2".equals(opcion1)) {
			
			GrafoNoDirigido G = new GrafoNoDirigido();

			while (true) {
			System.out.println("Opciones de Grafo No Dirigido");
			System.out.println("Introduce el numero de la opcion que desees");			
			System.out.println("1- Cargar grafo");		
			System.out.println("2- Obtener numero de vertices del grafo");
			System.out.println("3- Obtener numero de lados del grafo");
			System.out.println("4- Agregar un vertice");
			System.out.println("5- Obtener un vertice");
			System.out.println("6- Verificar si se encuentra un vertice");
			System.out.println("7- Verificar si se encuentra un lado");
			System.out.println("8- Obtener los vertices del grafo");
			System.out.println("9- Obtener los lados del grafo");
			System.out.println("10- Calcular en grado de un vertice");
			System.out.println("11- Obtener los vertices adyacentes");
			System.out.println("12- Obtener los lados incidentes");
			System.out.println("13- Clonar grafo");
			System.out.println("14- Ver contenido del grafo");
			System.out.println("15- Agregar arista");
			System.out.println("16- Eliminar arista");
			System.out.println("17- Obtener arista");
			System.out.println("18- ELiminar vertice");
			System.out.println("0- Salir");
			String opcion2 = scanner.nextLine();
			
			// Cargamos grafo
			if ("1".equals(opcion2)){
				System.out.println("Introduzca nombre del archivo ");
				String nombreArchivo = scanner.nextLine();
				G.cargarGrafo(nombreArchivo);
				System.out.println("Grafo cargado correctamente");
				continue; 
			}			
			// Obtenemos numero de vertices
			else if ("2".equals(opcion2)){
				System.out.println("El numero de lados del grafo es: ");
				System.out.println(G.numeroDeLados());
				continue;			
			}
			// Obtenemos numero de lados
			else if ("3".equals(opcion2)){
				System.out.println("El numero de lados del grafo es: ");
				System.out.println(G.numeroDeLados());
				continue;			
			}
			// Agregamos un vertice
			else if ("4".equals(opcion2)){
				System.out.println("Introduzca id del vertice a agregar");
				String id = scanner.nextLine();
				System.out.println("Introduzca peso del vertice a agregar");
				Double peso = scanner.nextDouble();
				G.agregarVertice(id, peso);
				continue;			
			}
			// Obtenemos un vertice
			else if ("5".equals(opcion2)){
				System.out.println("Introduzca el id del vertice a obtener");
				String id = scanner.nextLine();
				System.out.println("Vertice obtenido");
				System.out.println(G.obtenerVertice(id));
				continue;			
			}
			// Buscamos vertice
			else if ("6".equals(opcion2)){
				System.out.println("Intruduzca el id del vertice a verificar si esta");
				String id = scanner.nextLine();				
				System.out.println(G.estaVertice(id));
				continue;			
			}
			// Buscamos lado
			else if ("7".equals(opcion2)){
				System.out.println("Intruduzca el id del vertice 1");
				String id1 = scanner.nextLine();
				System.out.println("Introduzca el id del vertice 2");
				String id2 = scanner.nextLine();				
				System.out.println(G.estaLado(id1,id2));
				continue;			
			}
			// Obtenemos lista de vertices
			else if ("8".equals(opcion2)){
				System.out.println(G.vertices());
				continue;			
			}
			// Obtenemos lista de lados
			else if ("9".equals(opcion2)){
				System.out.println(G.lados());
				continue;			
			}
			// Calculamos grado del vertice
			else if ("10".equals(opcion2)){
				System.out.println("Introduzca el id del vertice a calcular grado");
				String id = scanner.nextLine();
				System.out.println(G.grado(id));
				continue;			
			}
			// Obtenemos vertices adyacentes
			else if ("11".equals(opcion2)){
				System.out.println("Introduzca el id del vertice para buscar sus adyacentes");
				String id = scanner.nextLine();
				System.out.println(G.adyacentes(id));
				continue;			
			}
			// Obtenemos lados incidentes 
			else if ("12".equals(opcion2)){
				System.out.println("Introduzca el id del vertice para buscar sus incidentes");
				String id = scanner.nextLine();
				System.out.println(G.incidentes(id));
				continue;		
			}
			// Clonamos el grafo 
			else if ("13".equals(opcion2)){
				System.out.println("Grafo clonado");			
			}
			// Contenido del grafo
			else if ("14".equals(opcion2)){
				System.out.println(G);	
				continue;		
			}
			// Agregamos arista
			else if ("15".equals(opcion2)){
				System.out.println("Introduzca el id de la arista a agregar");
				String id = scanner.nextLine();
				System.out.println("Introduzca vertice extremo 1");
				String v1 = scanner.nextLine();
				System.out.println("Introduzca vertice extremo 2");
				String v2 = scanner.nextLine();
				System.out.println("Introduzca el peso de la arista");
				Double p = scanner.nextDouble();
				System.out.println(G.agregarArista(id, p, v1, v2));
				continue;			
			}
			// Eliminamos arista
			else if ("16".equals(opcion2)){
				System.out.println("Introduzca el id de la arista a eliminar");
				String id = scanner.nextLine();
				System.out.println(G.eliminarArista(id));
				continue;			
			}
			// Obtenemos arista
			else if ("17".equals(opcion2)){
				System.out.println("Introduzca el id del arista a obtener");
				String id = scanner.nextLine();
				System.out.println("Arista obtenido");
				System.out.println(G.obtenerArista(id));
				continue;			
			}
			// Eliminamos vertice
			else if ("18".equals(opcion2)){
				System.out.println("Introduzca el id del vertice a eliminar");
				String id = scanner.nextLine();
				System.out.println(G.eliminarVertice(id));
				continue;
			}
			// Salida de menu de grafo no dirigido
			else if ("0".equals(opcion2)) {
				System.out.println("Salimos");
				break;
			}
						
			}			

		}
		// Salida de menu principal		
		else if ("0".equals(opcion1)) {
			System.out.println("Salimos");
		}

	scanner.close();
  }
}
