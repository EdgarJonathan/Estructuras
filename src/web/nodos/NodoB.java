package web.nodos;

import web.estructuras.Doble_Info;

public class NodoB {
    public String codigo;
    public String pais;
    public Doble_Info celda;
    
    public NodoB(String codigo, String pais){	
        this.codigo = codigo;
        this.pais = pais;
        celda = new Doble_Info();
    }
}
