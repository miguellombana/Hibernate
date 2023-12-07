package aed.productos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aed.hibernate.Familia;

public class FamiliaDAO {

private static List<Familia> familias;
	
	static {
		
		familias = new ArrayList<Familia>();
		

		
	}
	
	public static List<Familia> getFamilias() {
		
	    for (Familia familia : familias) {
	        System.out.println("ID: " + familia.getCodFamilia() + ", Nombre: " + familia.getDenoFamilia());
	    }
		
		return familias;
	}
	
	public static Familia findById(int codFamilia) {
		return familias
				.stream()
				.filter(f -> f.getCodFamilia() == codFamilia)
				.findFirst()
				.get();
	}


	public static Familia addFamilia(String nombre, Session sesion) {
		Transaction transaction = sesion.beginTransaction();
		Familia familia = new Familia();
		familia.setDenoFamilia(nombre);
		familias.add(familia);
		sesion.persist(familia);
		transaction.commit();
		return familia;

	}
}
