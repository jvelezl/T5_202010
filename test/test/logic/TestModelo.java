package test.logic;

import static org.junit.Assert.*;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*; 
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {
	
	private Modelo modelo;
	
	@Before
	public void setUp1() {
		modelo= new Modelo();
		modelo.cargarDatos();
		modelo.copiarComparendos();
	}

	@Test
	public void testModelo() {
		assertTrue(modelo!=null);
		assertEquals(0, modelo.darTamano());  // Modelo con 0 elementos presentes.
	}
	@Test

	public void testDarTamanoAC()
	{
		setUp1();
		assertSame(modelo.darLista().darLongitud(),modelo.copiarComparendos().length);
	}


}