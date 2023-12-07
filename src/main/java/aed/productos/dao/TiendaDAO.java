package aed.productos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aed.hibernate.Tienda;

public class TiendaDAO {

	
    private static List<Tienda> tiendas = new ArrayList<>();

    static {

    }

    public static List<Tienda> getTiendas() {
        System.out.println("Obteniendo tiendas del DAO");
        return tiendas;
    }
	
	

	
	
	public static Tienda addTienda(String Denotienda, String codPostal, Session sesion) {
		Transaction transaction = sesion.beginTransaction();
		Tienda Tienda = null;
		
		try {
			Tienda = new Tienda();
			Tienda.setDenoTienda(Denotienda);
			Tienda.setCodigoPostal(codPostal);
			//Tienda.add(Tienda);

			sesion.persist(Tienda);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		
		return Tienda;
	}
}
