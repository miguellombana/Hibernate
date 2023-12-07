package aed.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Funciones {

	
	
	
	public static void addFamilia(String nombre, Session sesion) {

		Familia familia = new Familia();
		familia.setDenoFamilia(nombre);
		sesion.persist(familia);

	}

	public static void add(String nombre, Session sesion) {

		Familia familia = new Familia();
		familia.setDenoFamilia(nombre);
		sesion.persist(familia);

	}

	public static Producto addProducto(String nombre, Double precio, Familia familia, boolean congelado, Session sesion) {

		Transaction transaction = sesion.beginTransaction();
		Producto producto = null;

		try {
			producto = new Producto();
			producto.setDenoProducto(nombre);
			producto.setPrecioBase(precio);
			producto.setcodFamilia(familia);
			producto.setCongelado(congelado);

			sesion.persist(producto);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return producto;

	}

	public static void deleteProducto(int productId, Session sesion) {
		Transaction transaction = sesion.beginTransaction();
		try {
			Producto producto = sesion.get(Producto.class, productId);
			if (producto != null) {
				sesion.remove(producto);
				System.out.println("Producto eliminado con éxito.");
			} else {
				System.out.println("Producto no encontrado.");
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	public static void modificarProducto(int productId, String newProductName, Double newPrice, Session sesion) {
		Transaction transaction = sesion.beginTransaction();
		try {
			Producto producto = sesion.get(Producto.class, productId);
			if (producto != null) {
				producto.setDenoProducto(newProductName);
				producto.setPrecioBase(newPrice);
				sesion.merge(producto);
				System.out.println("Producto actualizado con éxito.");
			} else {
				System.out.println("Producto no encontrado.");
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}
	
	
	public static void showProductos(Session sesion) {
	    try {
	        // Realizar una consulta para obtener todos los productos con sus observaciones
	        String hql = "SELECT p FROM Producto p LEFT JOIN FETCH p.observacion";
	        Query<Producto> query = sesion.createQuery(hql, Producto.class);
	        java.util.List<Producto> productos = query.getResultList();

	        // Mostrar los productos y sus datos
	        System.out.println("Lista de Productos:");
	        for (Producto producto : productos) {
	            System.out.println("ID: " + producto.getCodProducto());
	            System.out.println("Nombre: " + producto.getDenoProducto());
	            System.out.println("Precio: " + producto.getPrecioBase());
	            System.out.println("Congelado: " + producto.isCongelado());

	            // Verificar si el producto tiene observación
	            ProductoObservacion observacion = producto.getObservacion();
	            if (observacion != null) {
	                System.out.println("Observación: " + observacion.getObservacion());
	            } else {
	                System.out.println("Sin observación.");
	            }

	            System.out.println("-------------------");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	public static List<Stock> ListarStock (Session session){
		System.out.println("Pruebas 1");
		List<Stock> ListarStock = session.createQuery("from Stock", Stock.class).getResultList();
		for(Stock stock : ListarStock) {
			System.out.println("Pruebas 24");
			System.out.println("Codigo Tienda: " + stock.getCodTienda().getDenoTienda() +
            "\nCod producto : " + stock.getCodProducto().getDenoProducto() +
            "\nUnidades : " + stock.getUnidades());
			
		}
		return ListarStock;
		
		
	}
	
	public static List<Producto> ListarProductos (Session session){
		List<Producto> ListarProductos = session.createQuery("from Producto", Producto.class).getResultList();
		for(Producto producto : ListarProductos) {
			System.out.println("Codigo Producto: " + producto.getCodProducto() +
            "\nCodigo Producto : " + producto.getDenoProducto() +
            "\nFamilia : " + producto.getcodFamilia() +
            "\nPrecio : " + producto.getPrecioBase() +
            "\nCongelado : " + producto.isCongelado() );
            
            
         // Obtener la observación asociada al producto
            ProductoObservacion observacion = obtenerObservacionPorProducto(producto, session);

            if (observacion != null) {
                System.out.println("Observación: " + observacion.getObservacion());
            } else {
                System.out.println("Sin observación.");
                
            }
          System.out.println("--------------------------------------------------------------------");
			
		}
		return ListarProductos;
		
		
	}
	

	private static ProductoObservacion obtenerObservacionPorProducto(Producto producto, Session session) {
	    String hql = "FROM ProductoObservacion WHERE codProducto = :producto";
	    return session.createQuery(hql, ProductoObservacion.class)
	            .setParameter("producto", producto)
	            .uniqueResult();
	}
	
	
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

	public static void addProducto(Producto producto) {
		// TODO Auto-generated method stub
		
	}
	
}
