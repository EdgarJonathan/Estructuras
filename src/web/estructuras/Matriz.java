package web.estructuras;

//import java.awt.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
//import java.util.Arrays;

import web.nodos.NodoB;
import web.nodos.Nodo_Info;
import web.nodos.Pagina;

public class Matriz {
	
	public ArbolB arbolF;  //ARBOL B PARA LAS FILAS
	public ArbolB arbolC;  //ARBOL B PARA LAS COLUMNAS
	public String contenido_rutas = "";
	//SOLO LA USO COMO AXILIAR PARA GRAFICAR LAS RUTAS SOLO PARA QUE NO SE REPITAN LINEAS EN LA GRAFICA
	public ArrayList<String> verificar_ContenidoR = new ArrayList<String>(); 
	//LISTA QUE USO PARA GUARDAR POSIBLES RUTAS OBTENIDAS DE LA MATRIZ
	public ArrayList<String>  posibles_rutas = new ArrayList<String>();
	
	public Matriz() {
		this.arbolF = new ArbolB();
		this.arbolC = new ArbolB();
	}
	
	//******* INGRESAR NUEVOS NODOS DE LOS ARBOLES, SON LOS PAISES *************
	public String Nodo_Destino(String codigo, String pais) throws InterruptedException {
		arbolF.Agregar(codigo,pais);
		arbolC.Agregar(codigo,pais);
		
		return "Se ingreso los paises correctamente!!";
	}
	
	// ORIGEN - DESTINO - COSTO - TIEMPO
	
	//********** INGRESAR NUEVAS RUTAS, QUE SON LOS NODOS ************	
	public String Ingresar_Ruta(Nodo_Info nuevo) {
		NodoB nodoF = Buscar_PaisF(nuevo.origen) ;
		NodoB nodoC = Buscar_PaisC(nuevo.destino) ;
		if(nodoF == null) 
			return "No existe el pais de origen y/o destino!!";
		
		/********* ASI LOS TENGO QUE USAR PARA ENLAZARLOS ********
		lista_columna.Insertar_C( lista_fila.Insertar_F(a) );
		lista_columna2.Insertar_C( lista_fila.Insertar_F(b) );
		lista_columna.Insertar_C( lista_fila2.Insertar_F(c) );*/
		Nodo_Info aux1 = nodoF.celda.Insertar_F(nuevo);
		if(aux1 == null)
			return "El pais origen no existe!!!";
		
		return nodoC.celda.Insertar_C( aux1 );	
	}
	
	
	//********** ELIMINAR ALGUNA RUTA, QUE SON LOS NODOS ************	
	public String Eliminar_Ruta(String origen, String destino) {
		NodoB nodoF = Buscar_PaisF(origen) ;
		NodoB nodoC = Buscar_PaisC(destino) ;
		if(nodoF == null) 
			return "No existe el pais de origen y/o destino!!";
		String aux = nodoF.celda.Eliminar_F(destino);
		if(aux.equals("La ruta no existe!!"))
			return aux;
		return nodoC.celda.Eliminar_C(origen);
		
	}
	
	//********** MOFDIFICAR ALGUNA RUTA, QUE SON LOS NODOS ************
	public String Modificar_Ruta(String origen, String destino, Nodo_Info nuevo) {
		NodoB nodoF = Buscar_PaisF(origen) ;
		NodoB nodoC = Buscar_PaisC(destino) ;
		if(nodoF == null) 
			return "No existe el pais de origen y/o destino!!";
		String aux = nodoF.celda.Eliminar_F(destino);
		if(aux.equals("La ruta no existe!!"))
			return aux;
		aux = nodoC.celda.Eliminar_C(origen);
		if(aux.equals("La ruta no existe!!"))
		return aux;
		nodoC.celda.Insertar_C( nodoF.celda.Insertar_F(nuevo) );
		return "Se modifico la celda!!";
	}
	
	
	
	//***** BUSCA EL NODO DEL ARBOLB DE LAS FILAS****
	public NodoB Buscar_PaisF(String codigo) {
		return Buscar_PaisF(codigo,arbolF.raiz);
	}
	
	private NodoB Buscar_PaisF(String codigo,Pagina padre) {
		if(padre != null) 
		{
			for(int i= 0; i < padre.Usados; i++) 
			{
				if(padre.datos[i].codigo.equals(codigo))
					return padre.datos[i];
			}
			
			for(int i=0; i <= padre.Usados ;i++) {
				NodoB aux =Buscar_PaisF(codigo,padre.hijos[i]);
				if(aux != null)
					return aux;
			}
		}
		return null;
	}
	
	//BUSCA EL NODO DEL ARBOLB DE LAS COLUMNAS
	public NodoB Buscar_PaisC(String codigo) {
		return Buscar_PaisC(codigo,arbolC.raiz);
	}
	
	private NodoB Buscar_PaisC(String codigo,Pagina padre) {
		if(padre != null) 
		{
			for(int i= 0; i < padre.Usados; i++) 
			{
				if(padre.datos[i].codigo.equals(codigo))
					return padre.datos[i];
			}
			
			for(int i=0; i <= padre.Usados ;i++) {
				NodoB aux =Buscar_PaisC(codigo,padre.hijos[i]);
				if(aux != null)
					return aux;
			}
		}
		return null;
	}
	
	public void Graficar_Arbol() throws InterruptedException, IOException {
		arbolF.graficar();
	}
	
	public void Grafica_Rutas() throws InterruptedException, IOException{
		if(arbolF.raiz == null){
        }else{
            try{
            	contenido_rutas = "";
    			verificar_ContenidoR.clear();
                FileOutputStream arch = new FileOutputStream("/home/diego/Imágenes/Imagenes_Proyecto2/Grafica_Rutas.dot");
            
                try (PrintStream imprimir = new PrintStream(arch)) {
                    imprimir.println("digraph g{");
                    imprimir.println("node [shape=ellipse];");
                    
                    Contenido_Rutas(arbolF.raiz);
                    imprimir.println(contenido_rutas);
                    
                    imprimir.println("}");
                }
                
                Runtime.getRuntime().exec("dot -Tpng -O /home/diego/Imágenes/Imagenes_Proyecto2/Grafica_Rutas.dot");
                Thread.sleep(1000);
                Runtime.getRuntime().exec("nomacs Grafica_Rutas.png");
                Thread.sleep(2000);
            }catch(IOException e){ }
        }
	}
	
	public void Contenido_Rutas(Pagina padre) {
		if(padre!=null){
            
            for(int a = 0; a < padre.Usados; a++){
            	String ver="";
            	if(padre.datos[a].celda.cabeza != null) {
            		ver = padre.datos[a].codigo +"->"+padre.datos[a].celda.cabeza.destino;
            		if(!verificar_ContenidoR.contains(ver)) {
    					contenido_rutas += ver+";\n";
    					verificar_ContenidoR.add(ver);
    				}
            	}
            	
            	Nodo_Info aux = padre.datos[a].celda.cabeza;
            	while(aux != null) {
            		if(aux.getSiguiente() != null) {
            			ver = aux.destino+"->"+aux.getSiguiente().destino;
            			if(!verificar_ContenidoR.contains(ver)) {
            				contenido_rutas += ver+";\n";
            				verificar_ContenidoR.add(ver);
            			}
            		}
            		aux = aux.getSiguiente();
            	}
            }
            for(int i=0; i <= padre.Usados ;i++) 
				Contenido_Rutas(padre.hijos[i]);
        }
	}
	
	public void Planes_Vuelo(String origen,String destino){
		NodoB nodoF = Buscar_PaisF(origen);
		if(nodoF != null) {
			Nodo_Info aux = nodoF.celda.cabeza;
			ruta="";cont=0;
			
			ruta+=nodoF.codigo;
			this.origen = nodoF.codigo;
			posibles_rutas.clear(); 
			
			if(aux.destino.equals(destino)) {
				ruta+=","+destino;
				posibles_rutas.add(ruta);
			}
			ruta = this.origen +","+aux.destino;
			two_lines = this.origen +","+aux.destino;
			
			cont++; ultima_parada = aux.destino;
			Planes_Vuelo(aux.getSiguiente(),destino,1);
			cont=0; cont++;ultima_parada = aux.destino;
			Planes_Vuelo(aux.getArriba(),destino,3);
			cont=0; cont++;ultima_parada = aux.destino;
			Planes_Vuelo(aux.getAbajo(),destino,2);
		}
		
		
		//return ruta;
	}
	String ruta="",origen="", two_lines="",ultima_parada=""; int cont=0;
	
	public void Planes_Vuelo(Nodo_Info nodo,String destino,int anulado) {
		if(nodo != null && cont<6) {
			//String ruta="";
			if(nodo.destino.equals(destino)){
				ruta+=","+destino;
				posibles_rutas.add(ruta);
				ruta = this.two_lines; cont=0;
				return;
			}
			
			if(!nodo.destino.equals(ultima_parada) && !nodo.destino.equals(this.origen)  ) {
				ruta+=","+nodo.destino;
				//cont++;
			}
			cont++;
			ultima_parada = nodo.destino;
			
			//SIGUIENTE 0
			if(nodo.getSiguiente() != null && anulado!=0 && cont < 6) {
				Planes_Vuelo(nodo.getSiguiente(),destino,1);
			}
			//ATRAS 1
			if(nodo.getAtras() != null && anulado!=1 && cont < 6) {
				Planes_Vuelo(nodo.getAtras(),destino,0);
			}	 
			//ARRIBA 2
			if(nodo.getArriba() != null && anulado!=2 && cont < 6) {
				Planes_Vuelo(nodo.getArriba(),destino,3);
			}
			//ABAJO 3
			if(nodo.getAbajo() != null && anulado!=3 && cont < 6) {
				Planes_Vuelo(nodo.getAbajo(),destino,2);
			}
			
		}
		String aux = ruta;
		for(int i = aux.length(); i>=0 ;i--) {
			aux = aux.substring(0, aux.length()-1);
			
			if(aux.charAt(i-2)==',') {
				aux = aux.substring(0, aux.length()-1);
				break;
			}
		}
	}
	
	
	
}
