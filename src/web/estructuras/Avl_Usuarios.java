package web.estructuras;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import web.nodos.Nodo_Avl;

public class Avl_Usuarios {
	public Nodo_Avl raiz;
	
	public Avl_Usuarios() {
		raiz = null;
	}
	
	//***** METODO BUSCAR ******
	public Nodo_Avl Buscar(String id, Nodo_Avl nodo) 
	{
		if(nodo == null)
			return null;
		
		if(nodo.getId().compareTo(id) == 0) {
			return nodo;
		}else if(nodo.getId().compareTo(id) < 0) {
			return Buscar(id, nodo.getDerecho());
		}else {
			return Buscar(id,nodo.getIzquierdo());
		}
	} 
	
	//****** METODO PARA OBTENER EL FACTOR DE EQUILIBRIO *********
	public int ObtenerFe(Nodo_Avl nodo) 
	{
		if(nodo == null) 
			return  -1;
		
		return nodo.getFe();
		
	}
	
	// ********* 	METODOS PARA INSERTAR DATO NUEVO  ***************
	public String Insertar(Nodo_Avl nuevo) 
	{
		if( Buscar(nuevo.getId(), raiz) == null )
		{
			if(raiz == null) 
			{
				raiz = nuevo;
			}else {
				raiz = Insertar(nuevo, raiz);
			}
			return "Se Ingreso un nuevo Usuario";
		}
		return "Ya existe un Usuario con el Id";
	}
	
	// ********* 	METODO AUX PARA INSERTAR DATO NUEVO  ***************
	public Nodo_Avl Insertar(Nodo_Avl nuevo, Nodo_Avl aux) 
	{
		Nodo_Avl raiz = aux;
		
		if(nuevo.getId().compareTo(aux.getId()) < 0) 
		{
			if(aux.getIzquierdo() == null) 
			{
				aux.setIzquierdo(nuevo);
			}else {
				aux.setIzquierdo(Insertar(nuevo,aux.getIzquierdo())); 
				if( (ObtenerFe(aux.getIzquierdo())) - (ObtenerFe(aux.getDerecho())) == 2)
				{
					if(nuevo.getId().compareTo(aux.getIzquierdo().getId()) < 0 ) {
						raiz = Rotacion_Izquierda(aux);
					}else {
						raiz = Doble_Izquierda(aux);
					}
				}
			}
		}else if(nuevo.getId().compareTo(aux.getId()) > 0)
		{
			if(aux.getDerecho() == null)
			{
				aux.setDerecho(nuevo);
			}else 
			{
				aux.setDerecho(Insertar(nuevo,aux.getDerecho()));
				if( (ObtenerFe(aux.getDerecho())) - (ObtenerFe(aux.getIzquierdo())) == 2 ) {
					if(nuevo.getId().compareTo(aux.getDerecho().getId()) > 0 ) {
						raiz = Rotacion_Derecha(aux);
					}else {
						raiz = Doble_Derecha(aux);
					}
				}
			}
		}else {
			//    NODO DUPLICADO O ID YA EXISTE
		}
		
		if((aux.getIzquierdo() == null) && (aux.getDerecho() != null)) 
		{
			aux.setFe(aux.getDerecho().getFe()+1);
		}else if((aux.getDerecho() == null) && (aux.getIzquierdo() != null))
		{
			aux.setFe(aux.getIzquierdo().getFe()+1);
		}else
		{
			aux.setFe( Math.max( ObtenerFe(aux.getIzquierdo()) , ObtenerFe(aux.getDerecho())) +1 ) ;
		}
		
		return raiz;
	}
	
	// ********* 	METODOS PARA ELIMINAR DATO  ***************
	public String Eliminar(String id) {
		
		if( Buscar(id, raiz) != null )
		{
			raiz = Eliminar(id,raiz);
			
			return "Se Elimino Correctamente el usuario";
		}
		return "No existe un Usuario con el Id";
	}
	
	public Nodo_Avl Eliminar(String id, Nodo_Avl nodo) {
		if(nodo == null) {
			return null;
		}else {
			if(nodo.getId().compareTo(id) < 0) 
			{
				nodo.setDerecho(Eliminar(id,nodo.getDerecho()));
			}else if(nodo.getId().compareTo(id) > 0) 
			{
				nodo.setIzquierdo(Eliminar(id,nodo.getIzquierdo()));
			}else if(nodo.getIzquierdo() == null) 
			{
				nodo = nodo.getDerecho();
			}else if(nodo.getDerecho() == null)
			{
				nodo = nodo.getIzquierdo();
			} 
			else if(ObtenerFe(nodo.getIzquierdo()) > ObtenerFe(nodo.getDerecho())) 
			{
				nodo = Rotacion_Derecha(nodo);
				nodo.setIzquierdo(Eliminar(id,nodo.getIzquierdo()));
			}else {
				nodo = Rotacion_Izquierda(nodo);
				nodo.setDerecho(Eliminar(id,nodo.getDerecho()));
			}
			
			if(nodo != null) {
				nodo.setFe(ObtenerFe(nodo.getIzquierdo()) + ObtenerFe(nodo.getDerecho()));
			}
		}
		return nodo;
	}
	
	// ********* 	METODOS PARA MODIFICAR DATO  ***************
	public String Modificar(String id,Nodo_Avl nuevo) {
		if( Buscar(id, raiz) != null )
		{
			Eliminar(id);
			Insertar(nuevo);
			
			return "Se Elimino Correctamente el usuario";
		}
		return "No existe un Usuario con el Id";
	}
	
	public void Graficar() {
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try {
			fichero = new FileWriter("/home/diego/Imágenes/Imagenes_Proyecto2/Avl_Usuarios.dot");
			pw = new PrintWriter(fichero);
			
			pw.println("digraph G {");
			pw.println("node [shape=box]");
			pw.println(Generar_Contenido(raiz));
			pw.println("}");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (null != fichero)
		              fichero.close();
		    } catch (Exception e2) {
		        e2.printStackTrace();
		    }
		}
		
		//system("dot -Tpng -O /home/diego/Imágenes/Imagenes_Proyecto2/Avl_Usuarios.dot");
        //system("xdg-open ABB.dot.png");
        try {
        	String [] cmd = {"dot","-Tpng","-O","/home/diego/Imágenes/Imagenes_Proyecto2/Avl_Usuarios.dot"};
        	Runtime.getRuntime().exec(cmd);
        }catch(IOException ioe) {
        	System.out.println(ioe);
        }
		
	}
	
	public String Generar_Contenido(Nodo_Avl raiz) {
		if(raiz != null)
		{
			String contenido = "";

			contenido+="nodo"+raiz.getId()+"[label=\"Id:"+raiz.getId()+"\nNombre:"+
					raiz.getNombre()+"\nPword:"+raiz.getPassword()+"\"];";
			if(raiz.getIzquierdo() != null) {
			//contenido = raiz.getId()+"->"+raiz.getIzquierdo().getId()+";\n";
				contenido+="nodo"+raiz.getId()+"->nodo"+raiz.getIzquierdo().getId()+";\n";
			}
			if(raiz.getDerecho() != null) {
			//contenido += raiz.getId()+"->"+raiz.getDerecho().getId()+";\n";
				contenido+="nodo"+raiz.getId()+"->nodo"+raiz.getDerecho().getId()+";\n";
			}
			
			contenido += Generar_Contenido(raiz.getDerecho());
			contenido += Generar_Contenido(raiz.getIzquierdo());
			return contenido;
		}
		return "";
	}
	
	
	// ----------------------- ROTACIONES ----------------------------
	//*****  ROTACION SIMPLE IZQUIERDA **********
	public Nodo_Avl Rotacion_Izquierda(Nodo_Avl nodo) 
	{
		Nodo_Avl aux = nodo.getIzquierdo();
		nodo.setIzquierdo(aux.getDerecho());
		aux.setDerecho(nodo);
		nodo.setFe(Math.max( ObtenerFe(nodo.getIzquierdo()),ObtenerFe(nodo.getDerecho())) +1);
		aux.setFe(Math.max( ObtenerFe(aux.getIzquierdo()),ObtenerFe(aux.getDerecho())) +1);
		return aux;
	}
	
	//***** ROTACION SIMPLE DERECHA *********
	public Nodo_Avl Rotacion_Derecha(Nodo_Avl nodo) 
	{
		Nodo_Avl aux = nodo.getDerecho();
		nodo.setDerecho(aux.getIzquierdo());
		aux.setIzquierdo(nodo);
		nodo.setFe(Math.max( ObtenerFe(nodo.getIzquierdo()),ObtenerFe(nodo.getDerecho())) +1);
		aux.setFe(Math.max( ObtenerFe(aux.getIzquierdo()),ObtenerFe(aux.getDerecho())) +1);
		return aux;
	}
	
	//******** ROTACION DOBLE IZQUIERDA ***********
	public Nodo_Avl Doble_Izquierda(Nodo_Avl nodo)
	{
		Nodo_Avl aux;
		nodo.setIzquierdo(Rotacion_Derecha(nodo.getIzquierdo()));
		aux = Rotacion_Izquierda(nodo);
		return aux;
	}
	
	
	//******** ROTACION DOBLE DERECHA ***************
	public Nodo_Avl Doble_Derecha(Nodo_Avl nodo)
	{
		Nodo_Avl aux;
		nodo.setDerecho(Rotacion_Izquierda(nodo.getDerecho()));
		aux = Rotacion_Derecha(nodo);
		return aux;
	}
	
	
	//--------------  DISTINTOS RECORRIDOS  -----------------
	//**********  InORDEN  ************
	public void InOrden(Nodo_Avl nodo) {
		if(nodo!=null) {
			InOrden(nodo.getIzquierdo());
			System.out.print(nodo.getId() + ", ");
			InOrden(nodo.getDerecho());
		}
	}
	
	public void preOrden(Nodo_Avl nodo) {
		if(nodo!=null) {
			System.out.print(nodo.getId() + ", ");
			preOrden(nodo.getIzquierdo());
			preOrden(nodo.getDerecho());
		}
	}
	
	public void postOrden(Nodo_Avl nodo) {
		if(nodo!=null) {
			postOrden(nodo.getIzquierdo());
			postOrden(nodo.getDerecho());
			System.out.print(nodo.getId() + ", ");
		}
	}
	
	
	
	
}
