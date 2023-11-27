package aed.hibernate;

import org.hibernate.Session;

public class Main {

	public static void main(String[] args) {

			Session sesion = HibernateUtil.getSessionFactory().openSession(); // crea la sesion
			sesion.beginTransaction();
			
			try {
	            Familia familia = new Familia();
	            familia.setCodFamilia(1);
	            familia.setDenoFamilia("Fruta");
	    //        sesion.persist(familia);
	          

	            Producto producto = new Producto();
	            producto.setCodProducto(1);
	            producto.setDenoProducto("Manzanas");
	            producto.setcodFamilia(1);
	            producto.setCongelado(false);
	            sesion.persist(producto);
	        

	            Tienda tienda = new Tienda();
	            tienda.setCodTienda(1);
	            tienda.setDenoTienda("Mercadona");
	            tienda.setCodigoPostal("10001");
	            sesion.persist(tienda);
	       

	            Stock stock = new Stock();
	            stock.setCodProducto(1);
	            stock.setCodTienda(1);
	            stock.setUnidades(100);
	   //         sesion.persist(stock);
	        

	            ProductoObservacion productoObservacion = new ProductoObservacion();
	            productoObservacion.setCodProducto(1);
	            productoObservacion.setObservacion("Todo correcto");
	        //    sesion.persist(productoObservacion);
	            sesion.getTransaction().commit();
	        } catch (Exception e) {
	            // En caso de error, realiza un rollback
	            sesion.getTransaction().rollback();
	            e.printStackTrace(); // Manejo de excepciones adecuado
	        } finally {
	            // Cierre de la sesión al final
	            sesion.close();
	        }
	    }

}
