package web.estructuras;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import web.nodos.NodoHash;

public class TablaHash {
	public NodoHash inicio;
	public int size,casillasllenas=0;
	
	public TablaHash(int sizeInicial) { //ENVIO EL TAMAÑO DE LA TABLA HASH EN MI CASO SERA 43
		inicio=null;
		this.size=sizeInicial;
		inicio=insertarCorrelativo(0,inicio);
		for(int i=1;i<sizeInicial;i++) {
			insertarCorrelativo(i,buscar(i-1));
		}
	}
	
	public NodoHash insertarCorrelativo(int correlativo,NodoHash nodofinal) {
		NodoHash nuevoco = new NodoHash(correlativo);
		NodoHash nuevodatonull= new NodoHash("","","",-1,null);
		if(nodofinal == null) {
			nuevoco.dato=nuevodatonull;
			nodofinal=nuevoco;
			
		}else {
			nuevoco.dato=nuevodatonull;
			nodofinal.abajo=nuevoco;	
		}
		return nuevoco;
	}
	
	public void resize(int nuevotam, int tamactual,NodoHash nodofinal) {
		System.out.println("Nodo Enviado: " + nodofinal.correlativo);
		for(int x=tamactual;x<nuevotam;x++) {
			insertarCorrelativo(x,buscar(x-1));
		}
		System.out.println("El tamaño actual era: "+ tamactual + " y  el nuevo tamaño es: " + nuevotam);
		this.size=nuevotam;
	}
	
	public NodoHash buscar(int correlativo) {
		NodoHash tempco = inicio;
		while(tempco!=null) {
			if(tempco.correlativo == correlativo) {
				return tempco;
			}
			tempco=tempco.abajo;
		}
		return null;
	}
	
	public int funcionHash(int cod) {
		int indice = cod%(this.size-1);
		int cont=1;
		while(buscar(indice).dato.reservacion != -1) {
			System.out.print("Ha ocurrido un Colision en el nodo: " + indice);
			//indice+=cont*cont;
			indice+=cont*2;
			indice%=this.size-1;
			System.out.println(" | Rehasheo a: "+ indice);
			cont++;
		}
		return indice;	
	}
	
	public void insertarDato(String costo,String tiempo,String cliente,int reservacion,String lista) {
		NodoHash tempco;
		NodoHash nuevodato = new NodoHash(costo,tiempo,cliente,reservacion,lista);
		tempco=buscar(funcionHash(reservacion));
		tempco.dato=nuevodato;
		this.casillasllenas++;
		if(this.casillasllenas == this.size*0.5) {
			System.out.println("50% de la tabla llena; Rediminesionando..."); 
			//int nuevotam = 2*this.size;//AQUI SE CAMBIA PARA EL NUEVO TAMAÑO DEL ARREGLO
			int nuevotam = this.size; boolean flag = true;
			//------ INCREMENTO HASTA EL NUEVO NUMERO PRIMO ---------
			while(flag) {
				nuevotam++;
				if(nuevotam%2!=0 && nuevotam%3!=0 && nuevotam%5!=0)
					flag = false;
			}
			resize(nuevotam,this.size,buscar(this.size-1));
		}
	}
	
	public void graficarHash() {
		NodoHash temp = inicio;
		String txt="digraph G{\n";
		FileOutputStream archivo;
		try {
			archivo = new FileOutputStream("/home/diego/Imágenes/Imagenes_Proyecto2/hash.dot");
			PrintStream imprimir = new PrintStream(archivo);
			while(temp!=null) {
				txt+="NodoCorrelativo"+temp.hashCode()+"[label=\""+temp.correlativo+"\",shape=\"box\"]\n";
				txt+="NodoDato"+temp.dato.hashCode()+"[label=\"Codigo: "+temp.dato.reservacion+"\\n Nombre: "+temp.dato.cliente+"\\nCosto: "+temp.dato.costo+"\\nTiempo: "+temp.dato.tiempo+"\",shape=\"box\"]\n";
				txt+="NodoLista"+temp.dato.paises.hashCode()+"[label=\""+temp.dato.Lpaises+"\",shape=\"box\"]\n";
				temp=temp.abajo;
			}
			
			temp=inicio;
			txt+="NodoCorrelativo"+temp.hashCode()+"->"+"NodoDato"+temp.dato.hashCode()+"->"+"NodoLista"+temp.dato.paises.hashCode()+"\n";
			txt+="{rank=same;NodoCorrelativo"+temp.hashCode()+",NodoDato"+temp.dato.hashCode()+",NodoLista"+temp.dato.paises.hashCode()+"};\n";
			txt+="NodoCorrelativo"+temp.hashCode();
			while(temp.abajo!=null) {
				temp=temp.abajo;
				txt+="->NodoCorrelativo"+temp.hashCode()+"\n";
				txt+="NodoCorrelativo"+temp.hashCode()+"->"+"NodoDato"+temp.dato.hashCode()+"->"+"NodoLista"+temp.dato.paises.hashCode()+"\n";
				txt+="{rank=same;NodoCorrelativo"+temp.hashCode()+",NodoDato"+temp.dato.hashCode()+",NodoLista"+temp.dato.paises.hashCode()+"};\n";
				txt+="NodoCorrelativo"+temp.hashCode();
			}
			txt+="}";
			imprimir.println(txt);
			imprimir.close();
			/*Runtime.getRuntime().exec("dot -Tpng hash.dot -o /home/diego/Imágenes/Imagenes_Proyecto2/hash.png");
            Thread.sleep(2000);
            File imagen = new File(System.getProperty("user.dir")+"\\hash.png");
            Desktop.getDesktop().open(imagen);*/
			Runtime.getRuntime().exec("dot -Tpng -O /home/diego/Imágenes/Imagenes_Proyecto2/hash.dot");
            Thread.sleep(1000);
            //Runtime.getRuntime().exec("nomacs hash.png");
            //Thread.sleep(2000);
          //SOLO COPIAR EL ARCHIVO Y METERLO EN EL SERVIDOR
            try {
            	Path origenPath = Paths.get("/home/diego/Imágenes/Imagenes_Proyecto2/hash.dot.png");
                Path destinoPath = Paths.get("/home/diego/Documentos/EDD/Front-end/assets/images/big/hash.dot.png");
                //sobreescribir el fichero de destino si existe y lo copia
                Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
            }catch (IOException e2) {
    	        e2.printStackTrace();
    	    }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
