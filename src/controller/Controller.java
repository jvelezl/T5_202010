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
					view.printMessage("--------- \nBuscar comparendo: ");
					System.out.println("Introduzca la fecha (formato yyyy-MM-dd HH:mm), la clase del vehículo y  la infracción. Todos separados por comas y en ese orden.");
					String llave=lector2.nextLine();
					String key[]=llave.split(",");
					String key2=modelo.llaveComparendoLinear(key);
					Comparendos[] buscados=modelo.darComparendosPorLlaveLinear(key2);
					for(int i=0;i<buscados.length;i++)
					{
						System.out.println("El primer comparendo es: ObjectId: " + buscados[i].darId() + ", Fecha: " + buscados[i].darFecha()
						+ ", Infracción: "+ buscados[i].darInfraccion()+ ", Localidad: " + buscados[i].darLocalidad());
					}
					
					break;
				case 3: 
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
