package web.estructuras;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import web.nodos.NodoB;
import web.nodos.Pagina;

public class ArbolB {
	public Pagina raiz;
    String grafica = "",nodo="";
    
    //public void Agregar(NodoB nuevo) throws InterruptedException {
    //	Agregar(nuevo.codigo,nuevo.pais);
    //}
    
    public void Agregar(String codigo,String pais) throws InterruptedException{
        raiz = Insertar(null,raiz,5,codigo,pais);
        raiz = Verificar(raiz,5);
    }
    
    public Pagina Insertar(Pagina padre,Pagina actual, int grado, String codigo,String pais){
        if(actual == null){
            actual = new Pagina(grado);
            actual.Agregar(codigo,pais);
        }else if(actual.hijos[0]!=null){
        	if(codigo.compareTo(actual.datos[0].codigo) < 0) {
                actual.hijos[0] = Insertar(actual,actual.hijos[0], grado, codigo, pais);
            }else{
                int x=0;
                for(x = 0; x < actual.Usados-1; x++){

                	if(codigo.compareTo(actual.datos[x].codigo)>0 &&  codigo.compareTo(actual.datos[x+1].codigo)<0) {
                        actual.hijos[x+1] = Insertar(actual,actual.hijos[x+1], grado, codigo, pais);
                    }
                }
                if(codigo.compareTo(actual.datos[x].codigo) > 0 ) {
                    actual.hijos[x+1] = Insertar(actual,actual.hijos[x+1], grado, codigo, pais);
                }
            }
        }else{
            actual.Agregar(codigo,pais);
        } 
        return actual;
    }
    
    
    private Pagina Verificar(Pagina actual,int grado){
        if(actual!=null){
            for(int a = 0; a<=actual.Usados;a++){
                actual.hijos[a] = Verificar(actual.hijos[a],grado);
            }
            
            if(actual.hijos[0]==null){
                if(actual == raiz){
                    if(actual.Usados == grado){
                        Pagina temp = null;
                        temp = Insertar(null,temp,grado,actual.datos[(grado-1)/2].codigo,actual.datos[(grado-1)/2].pais);
                    
                        Pagina siguienteHijo = null;
                
                        actual.datos[(grado-1)/2] = null;
                
                        for(int d = ((grado-1)/2)+1; d < actual.Usados; d++){
                            siguienteHijo = Insertar(null,siguienteHijo,grado,actual.datos[d].codigo,actual.datos[d].pais);
                            actual.datos[d] = null;
                        }
                        actual.Usados = (grado-1)/2;
                        temp.AgregarHijo(actual);
                        temp.AgregarHijo(siguienteHijo);
                
                        actual = temp;        
                    }
                }
            }else{
                for(int a=0;a<=actual.Usados;a++){
                    if(actual.hijos[a]==null){
                        break;
                    }else{
                        if(actual.hijos[a].Usados==grado){    
                            actual.Agregar(actual.hijos[a].datos[(grado-1)/2].codigo, actual.hijos[a].datos[(grado-1)/2].pais);
                            actual.hijos[a].datos[(grado-1)/2] = null;
                            
                            Pagina sHijo = null;
                        
                            for(int c = ((grado-1)/2)+1;c<actual.hijos[a].Usados;c++){
                                sHijo = Insertar(null,sHijo,grado,actual.hijos[a].datos[c].codigo,actual.hijos[a].datos[c].pais);
                                actual.hijos[a].datos[c]=null;
                            }
                            
                            for(int c = ((grado-1)/2)+1;c<=actual.hijos[a].Usados;c++){
                                sHijo.AgregarHijo(actual.hijos[a].hijos[c]);
                            }
                            
                            actual.hijos[a].Usados = (grado-1)/2;
                            actual.AgregarHijo(sHijo);
                        }  
                    }
                }
                if(actual!=null){
                if(actual==raiz){
                    if(actual.Usados==5){
                        Pagina temp = null;
                        temp = Insertar(null,temp,grado,actual.datos[(grado-1)/2].codigo,actual.datos[(grado-1)/2].pais);
                        Pagina sHijo = null;
                        actual.datos[(grado-1)/2] = null;
                
                        for(int d = ((grado-1)/2)+1; d < actual.Usados; d++){
                            sHijo = Insertar(null,sHijo,grado,actual.datos[d].codigo,actual.datos[d].pais);
                            actual.datos[d] = null;
                        }
                        if(actual.hijos[0]!=null){
                        for(int c = ((grado-1)/2)+1;c<=actual.Usados;c++){
                                sHijo.AgregarHijo(actual.hijos[c]);
                        }
                        }
                        actual.Usados = (grado-1)/2;
                        temp.AgregarHijo(actual);
                        temp.AgregarHijo(sHijo);
                
                        actual = temp;   
                    }
                }
               }
            } 
        }
        return actual;
    }
    
    public void graficar() throws InterruptedException, IOException{
        if(raiz == null){
        }else{
            try{
                FileOutputStream arch = new FileOutputStream("/home/diego/Imágenes/Imagenes_Proyecto2/ArbolB.dot");
            
                try (PrintStream imprimir = new PrintStream(arch)) {
                    imprimir.println("digraph g{");
                    imprimir.println("\tgraph [pad=\"0.15\", nodesep=\"0.15\", ranksep=\"1\"];");
                    imprimir.println("\tnode [shape=none];");
                    
                    grafica = "";
                    nodo = "";
                    
                    Recorrido(raiz);
                    imprimir.println(grafica);
                    imprimir.println(nodo);
                    
                    imprimir.println("}");
                }
                
                Runtime.getRuntime().exec("dot -Tpng -O /home/diego/Imágenes/Imagenes_Proyecto2/ArbolB.dot");
                Thread.sleep(1000);
                Runtime.getRuntime().exec("nomacs ArbolB.png");
                Thread.sleep(2000);
            }catch(IOException e){ }
        }
    }
    
    private void Recorrido(Pagina padre){
        if(padre!=null){
            nodo += "\t"+padre.hashCode()+"[label=<\n\t<table border=\"0\" cellborder=\"1\" cellspacing=\"0\"> <tr>";
            
            for(int a = 0; a < padre.Usados; a++){
                //nodo += " <td BGCOLOR=\"cyan4\">" + padre.datos[a].codigo + "</td>";
            	nodo += " <td >" + padre.datos[a].codigo+" "+padre.datos[a].pais + "</td>";
            }
            nodo += " </tr> </table>>]\n";
            
            for(int a = 0; a <= padre.Usados; a++){
                if(padre.hijos[a] != null){
                    grafica += "\t" + padre.hashCode() + "->" + padre.hijos[a].hashCode()+"\n";
                }
                Recorrido(padre.hijos[a]);
            }
        }
    }
    
    
}
