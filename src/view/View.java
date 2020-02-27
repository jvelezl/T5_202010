package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar Lista de comparendos");
			System.out.println("2. Buscar comparendo");
			System.out.println("3. Exit ");
			System.out.println("4. Copiar a arreglo comparable");
			System.out.println("5. Ordenarlo por shellSort.");
			System.out.println("6. Ordenarlo por MergeSort.");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		/**public void printModelo(int modelo)
		{
			for(int i=0;i<modelo.darTamano();i++)
			{				
					if(modelo.buscarPos(i)!=null)
					System.out.println(modelo.buscarPos(i));
			}
			// TODO OK implementar
		}*/
}
