package web.nodos;

public class Nodo_Info {
	
	public String origen;
	public String destino;
	public String costo;
	public String tiempo;

	Nodo_Info siguiente,atras,arriba,abajo;
	
	public Nodo_Info(String origen, String destino,String costo, String tiempo) {
		this.origen = origen;
		this.destino = destino;
		this.costo = costo;
		this.tiempo = tiempo;
		this.siguiente = null;
		this.abajo = null;
		this.atras = null;
		this.arriba = null;
	}
	

	public String getOrigen() {
		return origen;
	}


	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public String getDestino() {
		return destino;
	}


	public void setDestino(String destino) {
		this.destino = destino;
	}


	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public Nodo_Info getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo_Info siguiente) {
		this.siguiente = siguiente;
	}

	public Nodo_Info getAtras() {
		return atras;
	}

	public void setAtras(Nodo_Info atras) {
		this.atras = atras;
	}

	public Nodo_Info getArriba() {
		return arriba;
	}

	public void setArriba(Nodo_Info arriba) {
		this.arriba = arriba;
	}

	public Nodo_Info getAbajo() {
		return abajo;
	}

	public void setAbajo(Nodo_Info abajo) {
		this.abajo = abajo;
	}
}
