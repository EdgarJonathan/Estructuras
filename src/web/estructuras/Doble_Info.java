package web.estructuras;

import web.nodos.Nodo_Info;

public class Doble_Info {
	
	Nodo_Info cabeza;
	Nodo_Info ultimo;
	
	
	public Doble_Info(){
		cabeza = null;
		ultimo = null;
	}
	
	public Nodo_Info Insertar_F(Nodo_Info nuevo) 
	{	
		if(!Buscar_C(nuevo.getDestino())) {
			if(cabeza == null) {
				cabeza = ultimo = nuevo;
				//return "Se ingreso correctamente datos ruta (FILA)";
			}else {
				Nodo_Info aux = cabeza;
				while(aux != null) {
					if(nuevo.getDestino().compareTo(aux.getDestino()) < 0) {
						if(aux == cabeza) {
							cabeza.setAtras(nuevo);
							nuevo.setSiguiente(cabeza);
							cabeza = nuevo;break;
						}else{
							aux.getAtras().setSiguiente(nuevo);
							nuevo.setAtras(aux.getAtras());
							nuevo.setSiguiente(aux);
							aux.setAtras(nuevo); break;
						}
					}else if(nuevo.getDestino().compareTo(ultimo.getDestino()) > 0) {
						ultimo.setSiguiente(nuevo);
						nuevo.setAtras(ultimo);
						ultimo = nuevo; break;
					}
					aux = aux.getSiguiente();
				}
			}
			//return "Se Ingreso correctamente datos ruta (FILA)";
			return nuevo;
		}
		//return "La ruta ya existe (HECHO EN FILAS)";
		return null;
	}
	
	public String Insertar_C(Nodo_Info nuevo) 
	{	
		if(!Buscar_F(nuevo.getOrigen()) && nuevo!=null) {
			if(cabeza == null) {
				cabeza = ultimo = nuevo;
				return "Se Ingreso correctamente datos ruta!! ";
			}else {
				//Nodo_Info aux = cabeza;
				Nodo_Info aux = ultimo;
				while(aux != null) {
					if(nuevo.getOrigen().compareTo(aux.getOrigen()) < 0) {
						/*if(aux == cabeza) {
							cabeza.setArriba(nuevo);
							nuevo.setAbajo(cabeza);
							cabeza = nuevo;break;*/
						if(aux == ultimo) {
							ultimo.setAbajo(nuevo);
							nuevo.setArriba(ultimo);
							ultimo = nuevo;break;
						}else{
							/*aux.getArriba().setAbajo(nuevo);
							nuevo.setArriba(aux.getArriba());
							nuevo.setAbajo(aux);
							aux.setArriba(nuevo); break;*/
						    aux.getAbajo().setArriba(nuevo);
							nuevo.setAbajo(aux.getAbajo());
							nuevo.setArriba(aux);
							aux.setAbajo(nuevo); break;
						}
					}/*else if(nuevo.getOrigen().compareTo(ultimo.getOrigen()) > 0) {
						ultimo.setAbajo(nuevo);
						nuevo.setArriba(ultimo);
						ultimo = nuevo; break;*/
					else if(nuevo.getOrigen().compareTo(cabeza.getOrigen()) > 0) {
						cabeza.setArriba(nuevo);
						nuevo.setAbajo(cabeza);
						cabeza = nuevo; break;

					}
					//aux = aux.getAbajo()
					aux = aux.getArriba();
				}
			}
			return "Se Ingreso correctamente datos ruta!! ";
		}
		return "La ruta ya existe ";
	}
	
	//****** ESTE METODO SERVIRA PARA LAS FILAS *******
	public boolean Buscar_C(String destino) {
		if(cabeza != null) {
			Nodo_Info aux = cabeza;
			while(aux != null) {
				if(destino.equals(aux.getDestino()))
					return true;
				aux = aux.getSiguiente();
				//aux = aux.getArriba();
			}
		}
		return false;
	}
	
	//****** ESTE METODO SERVIRA PARA LAS COLUMNAS *******
		public boolean Buscar_F(String origen) {
			if(cabeza != null) {
				Nodo_Info aux = cabeza;
				while(aux != null) {
					if(origen.equals(aux.getOrigen()))
						return true;
					//aux = aux.getSiguiente();
					aux = aux.getArriba();
				}
			}
			return false;
		}
	
	// ********** METODO PARA ELIMINAR EN LA FILA *********
	public String Eliminar_F(String destino) {
		
		if(Buscar_C(destino)) {
			Nodo_Info aux = cabeza;
			while(aux != null) {
				if(destino.equals(aux.getDestino())) {
					if(aux == cabeza) {
						cabeza = cabeza.getSiguiente();
						if(cabeza != null) {
							cabeza.getAtras().setSiguiente(null);
							cabeza.setAtras(null);break;
						}else {
							ultimo = cabeza;
						}
					}else{
						aux.getAtras().setSiguiente(aux.getSiguiente());
						aux.getSiguiente().setAtras(aux.getAtras());
						aux.setSiguiente(null);
						aux.setAtras(null);break;
					}
				}else if(destino.equals(ultimo.getDestino())) {
					ultimo = ultimo.getAtras();
					ultimo.getSiguiente().setAtras(null);
					ultimo.setSiguiente(null);break;
				}
				aux = aux.getSiguiente();
			}
			return "Se Elimino la ruta correctamente (FILA)";
		}
		return "La ruta no existe!!";	
	}
	
	// ********** METODO PARA ELIMINAR EN LA COLUMNA *********
		public String Eliminar_C(String origen) {
			
			if(Buscar_F(origen)) {
//				Nodo_Info aux = cabeza;
				Nodo_Info aux = ultimo;
				while(aux != null) {
					if(origen.equals(aux.getOrigen())) {
						/*if(aux == cabeza) {
							cabeza = cabeza.getAbajo();
							if(cabeza != null) {
								cabeza.getArriba().setAbajo(null);
								cabeza.setArriba(null);break;*/
						if(aux == ultimo) {
							ultimo = ultimo.getArriba();
							if(ultimo != null) {
								ultimo.getAbajo().setArriba(null);
								ultimo.setAbajo(null);break;
							}else {
								cabeza= ultimo;
							}
						}else{
							/*aux.getArriba().setAbajo(aux.getAbajo());
							aux.getAbajo().setArriba(aux.getArriba());
							aux.setAbajo(null);
							aux.setArriba(null);break;*/
							aux.getAbajo().setArriba(aux.getArriba());
							aux.getArriba().setAbajo(aux.getAbajo());
							aux.setArriba(null);
							aux.setAbajo(null);break;
						}
					}/*else if(origen.equals(ultimo.getOrigen())) {
						ultimo = ultimo.getArriba();
						ultimo.getAbajo().setArriba(null);
						ultimo.setAbajo(null);break;*/
					else if(origen.equals(cabeza.getOrigen())) {
						cabeza = cabeza.getAbajo();
						cabeza.getArriba().setAbajo(null);
						cabeza.setArriba(null);break;
					}
//					aux = aux.getAbajo();
					aux = aux.getArriba();
				}
				return "Se Elimino la ruta correctamente";
			}
			return "La ruta no existe!!";	
		}
		
	public String Modificar_F( String destino, Nodo_Info nuevo) {
		String aux = Eliminar_F(destino);
		if(aux.equals("La ruta no existe!!"))
			return aux;
		Insertar_F(nuevo);
		return "Modifico celda fila";
	}
	
	public String Modificar_C( String origen, Nodo_Info nuevo) {
		String aux = Eliminar_C(origen);
		if(aux.equals("La ruta no existe!!"))
			return aux;
		Insertar_C(nuevo);
		return "Modifico celda columna";
	}
	
	public String Recorrido() {
		String respuesta="";
		Nodo_Info aux = cabeza;
		while(aux != null) {
			respuesta += aux.getDestino()+", ";
			aux = aux.getSiguiente();
		}
		return respuesta;
	}
	
	
}
