package web.nodos;

public class Nodo_Avl {
	String id;
	String nombre;
	String password;
	
	Nodo_Avl izquierdo, derecho;
	
	int fe;
	
	public Nodo_Avl(String id, String nombre, String password) 
	{
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.izquierdo = null;
		this.derecho = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Nodo_Avl getIzquierdo() {
		return izquierdo;
	}

	public void setIzquierdo(Nodo_Avl izquierdo) {
		this.izquierdo = izquierdo;
	}

	public Nodo_Avl getDerecho() {
		return derecho;
	}

	public void setDerecho(Nodo_Avl derecho) {
		this.derecho = derecho;
	}

	public int getFe() {
		return fe;
	}

	public void setFe(int fe) {
		this.fe = fe;
	}
	
	
}
