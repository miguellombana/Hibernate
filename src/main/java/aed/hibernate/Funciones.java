package aed.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Funciones {


	

	



	
	
	public static void Visualizacion01(Session session) {
	    // HQL query to fetch products with their stock, stores, and families
		String hqlQuery = "SELECT p, s, t, f FROM Producto p " +
                "JOIN Stock s ON p.codproducto = s.codproducto " +
                "JOIN Tienda t ON s.codtienda = t.codtienda " +
                "JOIN Familia f ON p.familia = f";


	    Query<Object[]> query = session.createQuery(hqlQuery, Object[].class);
	    List<Object[]> results = query.getResultList();

	    // Display the results
	    for (Object[] result : results) {
	        Producto producto = (Producto) result[0];
	        Stock stock = (Stock) result[1];
	        Tienda tienda = (Tienda) result[2];
	        Familia familia = (Familia) result[3];

	        System.out.println("Producto: " + producto.getDenoProducto());
	        System.out.println("Stock: " + stock.getUnidades());
	        System.out.println("Tienda: " + tienda.getDenoTienda());
	        System.out.println("Familia: " + familia.getDenoFamilia());
	        System.out.println("------------------------------");
	    }
	}	
}
