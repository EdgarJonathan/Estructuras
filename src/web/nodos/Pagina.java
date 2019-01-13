package web.nodos;

public class Pagina {
	public NodoB datos[];
    public Pagina hijos[];
    public int Usados;
    public int grado;
    
    public Pagina(int grado){
        datos = new NodoB[grado];
        hijos = new Pagina[grado+1];
        Usados = 0;
        this.grado = grado;
    }
    
    public void Agregar(String codigo,String pais){
        for(int pos = 0; pos < grado; pos++){
            if(datos[pos]==null){
                datos[pos] = new NodoB(codigo, pais);
                Usados++;
                Ordenar();
                break;
            }
        }
    }
    
    public void AgregarHijo(Pagina hijo){
        if(hijo!=null){
            for(int pos = 0; pos <= grado; pos++){
            if(hijos[pos]==null){
                hijos[pos] = hijo;
                OrdenarHijo();
                break;
            }
          }
        }
    }
    
    
    private void Ordenar(){
        boolean bandera = false;
        NodoB temp;
        
        while(!bandera){
            bandera = true;
            for(int pos = 0 ; pos < Usados-1;pos++){
            	if(datos[pos].codigo.compareTo( datos[pos+1].codigo ) > 0) {
                    temp = datos[pos];
                    datos[pos] = datos[pos+1];
                    datos[pos+1] = temp;
                    bandera = false;
                }
            }
        }
    }
    
    
    public void OrdenarHijo(){
        boolean bandera = false;
        Pagina temp;
        
        while(!bandera){
            bandera = true;
            for(int pos = 0 ; pos < Usados;pos++){
                if(hijos[pos]!=null && hijos[pos+1]!=null){
                    if(hijos[pos].datos[0]!=null && hijos[pos+1].datos[0]!=null){
                    	if(hijos[pos].datos[0].codigo.compareTo(hijos[pos+1].datos[0].codigo) > 0) {
                            temp = hijos[pos];
                            hijos[pos] = hijos[pos+1];
                            hijos[pos+1] = temp;
                            bandera = false;
                        }
                    }
                    
                }else{
                    break;
                }
            }
        }
    }
    
    
    
}
