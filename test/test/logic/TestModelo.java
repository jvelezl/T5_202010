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
	}

	@Test
	public void testModelo() {
		assertTrue(modelo!=null);
		assertEquals(0, modelo.darTamano());  // Modelo con 0 elementos presentes.
	}

	@Test
	public void testDarTamano() {
		setUp1();
		assertSame(19,modelo.darTamano());
	}

	@Test
	public void testBuscar() {
		setUp1();
		assertSame(176695, modelo.buscarPorId(176695).darId());
		// TODO Completar la prueba
	}



}