package controller;

import java.util.Scanner;



import model.data_structures.Comparendos;

import model.data_structures.Node;
import model.logic.Modelo;
import view.View;

public class Controller <T extends Comparable<T>>{

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		int dato = 0;
		Comparendos respuesta =null;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					view.printMessage("--------- \nCargar Lista: "); 
					Comparendos[] xs=modelo.cargarDatos();
					Comparendos primero=xs[0];
					Comparendos ultimo=xs[1];
					System.out.println("El primer comparendo es: ObjectId: " + primero.darId() + ", Fecha: " + primero.darFecha()
					+ ", Infracción: "+ primero.darInfraccion()+ ", Localidad: " + primero.darLocalidad());
				    System.out.println("El último comparendo es: ObjectId: " + ultimo.darId() + ", Fecha: " + ultimo.darFecha()
						+ ", Infracción: "+ ultimo.darInfraccion()+ ", Localidad: " + ultimo.darLocalidad());
				    System.out.println("El número de tuplas en la Tabla de Hash Linear Probing es:"+modelo.linearProbing().darN()+" y la de "
				    		+ "la Tabla de Hash Separate Chaning es:"+modelo.separateChaning().darN());
				    System.out.println("El tamaño inicial en la Tabla de Hash Linear Probing es:"+modelo.linearProbing().darTamanoInicial()+" y la de "
				    		+ "la Tabla de Hash Separate Chaining es:"+modelo.separateChaning().TAMANO_INICIAL);
				    System.out.println("El tamaño final en la Tabla de Hash Linear Probing es:"+modelo.linearProbing().darM()+" y la de "
				    		+ "la Tabla de Hash Separate Chaining es:"+modelo.separateChaning().darM());
				    System.out.println("El factor de carga final en la Tabla de Hash Linear Probing es:"+modelo.linearProbing().factorDeCarga()+" y la de "
				    		+ "la Tabla de Hash Separate Chaining es:"+modelo.separateChaning().darFactorDeCarga());
				    System.out.println("El numero de rehash en la Tabla de Hash Linear Probing es:"+modelo.linearProbing().numeroRehash()+" y la de "
				    		+ "la Tabla de Hash Separate Chaining es:"+modelo.separateChaning().numeroResize());						
				   
				    break;

				case 2:
					Scanner lector2=new Scanner(System.in);
					view.printMessage("--------- \nBuscar comparendos: ");
					System.out.println("Introduzca la fecha (formato yyyy-MM-dd HH:mm), la clase del vehículo y  la infracción. Todos separados por comas y en ese orden.");
					String llave=lector2.nextLine();
					String key[]=llave.split(",");
					String key2=modelo.llaveComparendoLinear(key);
					Comparendos[] buscados=modelo.darComparendosPorLlaveLinear(key2);
					for(int i=0;i<modelo.retornarUReq2();i++)
					{
						System.out.println("Los comparendos son:");
						System.out.println("Comparendo: ObjectId: " + buscados[i].darId() + ", Fecha: " + buscados[i].darFecha()
						+ ", Infracción: "+ buscados[i].darInfraccion()+ ", Localidad: " + buscados[i].darLocalidad());
					}
					
					break;
				case 3:
					Scanner lector3=new Scanner(System.in);
					view.printMessage("--------- \nBuscar comparendos: ");
					System.out.println("Introduzca la fecha (formato yyyy-MM-dd HH:mm), la clase del vehículo y  la infracción. Todos separados por comas y en ese orden.");
					String llave2=lector3.nextLine();
					String key3[]=llave2.split(",");
					String key4=modelo.llaveComparendoChaining(key3);
					Comparendos[] buscado=modelo.darComparendosPorLlaveSeparate(key4);
					for(int i=0;i<modelo.tamanoRealRes();i++)
					{
						System.out.println("Los comparendos son:");
						System.out.println("Comparendo: ObjectId: " + buscado[i].darId() + ", Fecha: " + buscado[i].darFecha()
						+ ", Infracción: "+ buscado[i].darInfraccion()+ ", Localidad: " + buscado[i].darLocalidad());
					}
					break;
				case 4: 
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	
		
				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
