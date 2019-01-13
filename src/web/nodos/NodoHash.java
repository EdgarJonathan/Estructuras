package web.nodos;


public class NodoHash {
	public String costo, tiempo, cliente, Lpaises;
	public int correlativo, reservacion=-1;
	public NodoHash abajo,dato;
	int cod = -1;
	public ListaPais paises;
	
	public NodoHash(String costo,String tiempo,String cliente,int reservacion,String lista) {
		this.reservacion = reservacion; //CODIGO DE LA RESERVACION - CORRELATIVO
		this.costo = costo;
		this.tiempo = tiempo;
		this.cliente = cliente;
		this.abajo=null;
		this.dato=null;
		this.paises = new ListaPais();
		
		this.Lpaises = lista;
		//LLenar_Lista(lista);
	}
	
	public NodoHash(int correlativo) {
		this.correlativo = correlativo;
		this.abajo=null;
		this.dato=null;
	}
	
	public void LLenar_Lista(String lista) {
		if(lista!=null) {
			String[] aux = lista.split("->");
			/*String aux2="";
		    for(int i=0;i<aux.length;i++) {
		    	aux2+=aux[i];
		    }*/
			for(int i=0;i<aux.length;i++)
				paises.Insertar(aux[i]);
		}
	}
	
	public String Datos_Lista() {
		NodoLista aux = paises.cabeza;
		String resp="";
		resp+=aux.pais+"->";
		aux = aux.siguiente;
		while(aux != null) {
			if(aux.siguiente != null) 
				resp += aux.pais+"->"+aux.siguiente.pais; 
			
		}
		return resp;
	}
	
	
	//---------------------- CLASES PARA LA LISTA --------------------
	public class NodoLista{
		public String pais;
		public NodoLista siguiente;
		public NodoLista() {
			this.pais = "";
			this.siguiente = null;
		}
	}
	
	public class ListaPais {
		public NodoLista cabeza;
		public NodoLista ultimo;
		
		public ListaPais() {
			this.cabeza = null;
			this.ultimo = null;
		}
		
		public void Insertar(String nuevo) {
			if(cabeza == null) { 
				cabeza = ultimo = new NodoLista();
				cabeza.pais = nuevo;
				ultimo = cabeza;
			}else {
				NodoLista aux = new NodoLista();
				aux.pais = nuevo;
				ultimo.siguiente = aux;
				ultimo = ultimo.siguiente;
			}
		}
	}
	
	
}
