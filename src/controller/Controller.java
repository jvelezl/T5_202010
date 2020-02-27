package controller;

import java.util.Scanner;


import model.data_structures.Comparendos;
import model.data_structures.ListaEncadenada;
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
					ListaEncadenada<Comparendos> lista=modelo.cargarDatos();
					Comparendos primero=lista.darPrimero().darElemento();
					Comparendos ultimo=lista.darUltimo().darElemento();
				    view.printMessage("Lista Cargada");
				    System.out.println("El primer comparendo es: ObjectId: " + primero.darId() +", Fecha: " + primero.darFecha()
						+ ", Infracción: "+ primero.darInfraccion()+ ", Clase Vehículo: " + primero.darClaseVehi() + ", Tipo servicio: " + primero.darTipo()
						+  ", Localidad: " + primero.darLocalidad());
				    System.out.println("El último comparendo es: ObjectId: " + ultimo.darId() + ", Fecha: " + ultimo.darFecha()
						+ ", Infracción: "+ ultimo.darInfraccion()+ ", Clase Vehículo: " + ultimo.darClaseVehi() + ", Tipo servicio: " + ultimo.darTipo()
						+ ", Localidad: " + ultimo.darLocalidad());
				    view.printMessage("Numero actual de elementos " + modelo.darTamano()+ "\n---------");						
					break;

				case 2:
					view.printMessage("--------- \nIdentificador del comparendo a buscar: ");
					dato = lector.nextInt();
					respuesta= modelo.buscarPorId(dato);
					if ( respuesta != null)
					{
						view.printMessage("Comparendo encontrado: "+ "ObjectId: " + respuesta.darId() + ", Fecha: " + respuesta.darFecha()
						+ ", Infracción: "+ respuesta.darInfraccion()+ ", Clase Vehículo: " + respuesta.darClaseVehi() + ", Tipo servicio: " + respuesta.darTipo()
						+ ", Localidad: " + respuesta.darLocalidad());
					}
					else
					{
						view.printMessage("Elemento NO encontrado");
					}
					view.printMessage("Numero actual de elementos " + "\n---------");						
					break;
					
				case 3: 
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	
				case 4:
					view.printMessage("-----------\n Copiar a un arreglo Comparable \n-----------");
					Comparable[] a=modelo.copiarComparendos();
					view.printMessage("El tamaño del arreglo copia es:"+a.length);
					break;
				case 5:
					view.printMessage("-----------\n Ordenar por ShellSort.\n-----------");
					modelo.shellSort(modelo.copiarComparendos());
					 break;
				case 6:
					view.printMessage("-----------\n Ordenar por MergeSort.\n-----------");
					modelo.mergeSort(modelo.copiarComparendos());
					 break;
				case 7:
					view.printMessage("-----------\n Ordenar por MergeSort.\n-----------");
					modelo.quickSort(modelo.copiarComparendos());
					 break;
				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
