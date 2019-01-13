//import java.text.Collator;

import java.io.IOException;
import java.util.ArrayList;

import web.estructuras.ArbolB;
import web.estructuras.Avl_Usuarios;
import web.estructuras.Doble_Info;
import web.estructuras.Matriz;
import web.estructuras.TablaHash;
import web.nodos.Nodo_Avl;
import web.nodos.Nodo_Info;

public class Back_end2 {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		/*Avl_Usuarios arbol = new Avl_Usuarios(); 
		Nodo_Avl a = new Nodo_Avl("azazer","Diego","saoer");//10
		Nodo_Avl b = new Nodo_Avl("exogof","Juan","hrad");//05
		Nodo_Avl c = new Nodo_Avl("ogatim","Mario","miewe");//13
		Nodo_Avl d = new Nodo_Avl("uridun","Julisa","kkewr");//01
		Nodo_Avl e = new Nodo_Avl("asozul","Wendy","mosei");//06
		Nodo_Avl f = new Nodo_Avl("egozic","Fernando","3f3rr");//17
		arbol.Insertar(a);
		arbol.Insertar(b);
		arbol.Insertar(c);
		arbol.Insertar(d);
		arbol.Insertar(e);
		arbol.Insertar(f);
		
		arbol.InOrden(arbol.raiz);
		System.out.println("");
		arbol.postOrden(arbol.raiz);
		System.out.println("");
		arbol.preOrden(arbol.raiz);
		System.out.println("");
		
		arbol.Eliminar("exogof");//05
		arbol.InOrden(arbol.raiz);
		System.out.println("");
		
		Nodo_Avl g = new Nodo_Avl("ucerul","Fernando","3f3rr");//46
		Nodo_Avl h = new Nodo_Avl("ucomar","Fernando","3f3rr");//84
		Nodo_Avl i = new Nodo_Avl("okohil","Fernando","3f3rr");//15
		Nodo_Avl j = new Nodo_Avl("apajus","Fernando","3f3rr");//04
		Nodo_Avl k = new Nodo_Avl("iwodot","Fernando","3f3rr");//09
		System.out.println(arbol.Insertar(g));
		System.out.println(arbol.Insertar(h));
		System.out.println(arbol.Eliminar("ucomar"));//01
		System.out.println(arbol.Eliminar("ucerul"));//06
		System.out.println(arbol.Insertar(i));
		System.out.println(arbol.Insertar(j));
		System.out.println(arbol.Eliminar("aboqor"));//13
		System.out.println(arbol.Modificar("egozic", k));//10
		arbol.InOrden(arbol.raiz);
		System.out.println(""); arbol.Graficar();*/
		
		/*Doble_Info lista_fila = new Doble_Info();
		Doble_Info lista_columna = new Doble_Info();
		Doble_Info lista_fila2 = new Doble_Info();
		Doble_Info lista_columna2 = new Doble_Info();
		
		//   ORIGEN/DESTINO/PRECIO/TIEMPO
		Nodo_Info a = new Nodo_Info("001","002","20","50");
		Nodo_Info b = new Nodo_Info("001","003","20","50");//
		Nodo_Info c = new Nodo_Info("002","002","20","50");//
		/*Nodo_Info d = new Nodo_Info("583","582","20","50");//
		Nodo_Info e = new Nodo_Info("703","945","20","50");
		Nodo_Info f = new Nodo_Info("103","023","20","50");
		
		lista_fila.Insertar_F(a);
		lista_fila.Insertar_F(b);
		lista_fila.Insertar_F(c);
		lista_fila.Insertar_F(d);
		lista_fila.Insertar_F(f);
		lista_fila.Insertar_F(e);
		
		System.out.println(lista_fila.Recorrido());
		System.out.println(lista_fila.Eliminar_F("023"));
		System.out.println(lista_fila.Recorrido());
		System.out.println(lista_fila.Eliminar_F("945"));
		System.out.println(lista_fila.Recorrido());
		System.out.println(lista_fila.Eliminar_F("354"));
		System.out.println(lista_fila.Recorrido());*
		
		// ********* ASI LOS TENGO QUE USAR PARA ENLAZARLOS ********+
		lista_columna.Insertar_C( lista_fila.Insertar_F(a) );
		lista_columna2.Insertar_C( lista_fila.Insertar_F(b) );
		lista_columna.Insertar_C( lista_fila2.Insertar_F(c) );*/
		
		// PRUEBA QUE HICE CON EL ARBOLB
		/*ArbolB arbolito = new ArbolB();
		arbolito.Agregar("003", "Gua");
		arbolito.Agregar("035", "Mex");
		arbolito.Agregar("093", "Sal");
		arbolito.Agregar("004", "Hon");
		arbolito.Agregar("012", "Esp");
		arbolito.Agregar("045", "Arg");
		arbolito.Agregar("064", "Usa");
		arbolito.Agregar("062", "Can");
		arbolito.Agregar("053", "Nor");
		arbolito.Agregar("089", "Ita");
		arbolito.Agregar("023", "Chi");
		arbolito.Agregar("043", "Bra");
		
		
		arbolito.Agregar("023", "Gua");
		arbolito.Agregar("059", "Mex");
		arbolito.Agregar("024", "Sal");
		arbolito.Agregar("253", "Hon");
		arbolito.Agregar("632", "Esp");
		arbolito.Agregar("453", "Arg");
		arbolito.Agregar("349", "Usa");
		arbolito.Agregar("139", "Can");
		arbolito.Agregar("492", "Nor");
		arbolito.Agregar("459", "Ita");
		arbolito.Agregar("429", "Chi");
		arbolito.Agregar("129", "Bra");
		
		arbolito.graficar();*/
		/*Matriz matriz = new Matriz();
		matriz.Nodo_Destino("003", "Gua");
		matriz.Nodo_Destino("035", "Mex");
		matriz.Nodo_Destino("093", "Sal");
		matriz.Nodo_Destino("004", "Hon");
		matriz.Nodo_Destino("012", "Esp");
		/*matriz.Nodo_Destino("045", "Arg");
		matriz.Nodo_Destino("064", "Usa");
		matriz.Nodo_Destino("062", "Can");*
		matriz.Graficar_Arbol();
		
		Nodo_Info a = new Nodo_Info("003","035","23","43");
		Nodo_Info b = new Nodo_Info("035","003","23","43");
		Nodo_Info c = new Nodo_Info("012","093","23","43");
		Nodo_Info d = new Nodo_Info("093","012","23","43");
		Nodo_Info e = new Nodo_Info("035","004","23","43");
		
		Nodo_Info f = new Nodo_Info("004","035","23","43");
		Nodo_Info g = new Nodo_Info("004","003","23","43");
		Nodo_Info h = new Nodo_Info("003","004","23","43");
		Nodo_Info i = new Nodo_Info("012","004","23","43");
		Nodo_Info j = new Nodo_Info("004","012","23","43");
		
		System.out.println(matriz.Ingresar_Ruta(a));
		System.out.println(matriz.Ingresar_Ruta(b));
		System.out.println(matriz.Ingresar_Ruta(c));
		System.out.println(matriz.Ingresar_Ruta(d));
		System.out.println(matriz.Ingresar_Ruta(e));
		System.out.println(matriz.Ingresar_Ruta(f));
		System.out.println(matriz.Ingresar_Ruta(g));
		System.out.println(matriz.Ingresar_Ruta(h));
		System.out.println(matriz.Ingresar_Ruta(i));
		System.out.println(matriz.Ingresar_Ruta(j));
		//System.out.println(matriz.Eliminar_Ruta("012","003"));
		
		//Nodo_Info cambio = new Nodo_Info("012","035","hola","funciono");
		//System.out.println(matriz.Modificar_Ruta(cambio.origen,cambio.destino,cambio));
		//matriz.Grafica_Rutas();
		matriz.Planes_Vuelo("093", "035");
		
		for(int n=0;n < matriz.posibles_rutas.size();n++) {
			System.out.println(matriz.posibles_rutas.get(n));
		}*/
		
		TablaHash tabla = new TablaHash(47);
		//ArrayList<String> lista = new ArrayList<String>();
		//lista.add("Gua");lista.add("Mex");lista.add("Nor");
		String lista = "Guatemala->Mexico->Panama";
		String lista2 = "Guatemala->Mexico->Noruega->Paraguay";
		
		int numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Alejandro", numero,lista2);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Caballeros", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Maria", numero,lista2);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Mario", numero,lista2);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Juan", numero,lista2);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Yulisa", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Mateo", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Ana", numero,lista2);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista2);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista2);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista2);
		/*tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Alejandro", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Caballeros", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Maria", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Mario", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Juan", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Yulisa", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Mateo", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Ana", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);
		numero = (int) (Math.random() * 200) + 1;
		tabla.insertarDato("50", "30", "Diego", numero,lista);*/
		
		tabla.graficarHash();
		
	}

}
