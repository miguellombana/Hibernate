package aed.productos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import aed.hibernate.Familia;
import aed.hibernate.HibernateUtil;
import aed.hibernate.Producto;
import aed.hibernate.ProductoObservacion;
import aed.hibernate.Stock;
import aed.hibernate.Tienda;

public class ProductoDAO {

	
private static List<Producto> productos = new ArrayList<Producto>();
	
	static {
		
		
	}
	
	public static List<Producto> getProductos() {
		
		
		Session session = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("FROM Producto", Producto.class).list();
			
		} finally {
				
			if(session != null) {
				session.close();
			}

		}
		

	}
	
	public static void addProductoDAO(Producto producto) {
		System.out.println("Producto añadido al DAO: " + producto);
		productos.add(producto);
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
			productos.add(producto);

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

	public static void modificarProducto(int productId, String newProductName, Double newPrice, Boolean congelado, Familia familia, String observacion, Session sesion) {
	    Transaction transaction = null;

	    try {
	        transaction = sesion.getTransaction();

	        if (!transaction.isActive()) {
	            transaction.begin();
	        }

	        Producto producto = sesion.get(Producto.class, productId);

	        if (producto != null) {
	            producto.setDenoProducto(newProductName);
	            producto.setPrecioBase(newPrice);
	            producto.setCongelado(congelado);
	            producto.setcodFamilia(familia);

	            // Modifica la observación del producto
	           ObservacionDAO.modificarObservacionProducto(productId, observacion, sesion);

	            sesion.merge(producto);
	            System.out.println("Producto actualizado con éxito.");
	        } else {
	            System.out.println("Producto no encontrado.");
	        }

	        if (transaction.getStatus() == TransactionStatus.ACTIVE) {
	            transaction.commit();
	        }
	    } catch (Exception e) {
	        if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
	            transaction.rollback();
	        }
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
	            ProductoObservacion observacion = ObservacionDAO.obtenerObservacionPorProducto(producto, sesion);
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
	
	public static List<Producto> ListarProductos (Session session){
		List<Producto> ListarProductos = session.createQuery("from Producto", Producto.class).getResultList();
		for(Producto producto : ListarProductos) {
			System.out.println("\nCodigo Producto: " + producto.getCodProducto() +
            "\nNombre Producto : " + producto.getDenoProducto() +
            "\nFamilia : " + producto.getcodFamilia() +
            "\nPrecio : " + producto.getPrecioBase() +
            "\nCongelado : " + producto.isCongelado() );
            
            
         // Obtener la observación asociada al producto
            ProductoObservacion observacion = ObservacionDAO.obtenerObservacionPorProducto(producto, session);

            if (observacion != null) {
                System.out.println("Observación: " + observacion.getObservacion());
            } else {
                System.out.println("Sin observación.");
                
            }
          System.out.println("--------------------------------------------------------------------");
			
		}
		return ListarProductos;
		
		
	}


	
	public static List<Object[]> listarProductosConStock(Session session) {
        String hql = "SELECT p, f, t, s " +
                     "FROM Producto p " +
                     "JOIN p.codFamilia f " +
                     "JOIN Stock s ON p = s.codProducto " +
                     "JOIN s.codTienda t";
        
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        List<Object[]> resultados = query.getResultList();

        for (Object[] resultado : resultados) {
            Producto producto = (Producto) resultado[0];
            Familia familia = (Familia) resultado[1];
            Tienda tienda = (Tienda) resultado[2];
            Stock stock = (Stock) resultado[3];

            System.out.println("\nCodigo Producto: " + producto.getCodProducto() +
                    "\nNombre Producto: " + producto.getDenoProducto() +
                    "\nPrecio: " + producto.getPrecioBase() +
                    "\nNombre Familia: " + familia.getDenoFamilia() +
                    "\nCodigo Familia: " + familia.getCodFamilia() +
                    "\nCongelado: " + producto.isCongelado() +
                    "\nNombre Tienda: " + tienda.getDenoTienda() +
                    "\nCodigo Tienda: " + tienda.getCodTienda() +
                    "\nCodigo Postal Tienda: " + tienda.getCodigoPostal() +
                    
                    "\nStock: " + stock.getUnidades()
            		
            		
            		);

            System.out.println("--------------------------------------------------------------------");
        }

        return resultados;
    }
	
	public static Producto getProductoPorId(int productId, Session sesion) {
	    try {
	        return sesion.get(Producto.class, productId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	public static void eliminarProductoDAO(int codigoProducto, Session sesion) {
	    // Verificar si hay una transacción activa
	    if (!sesion.getTransaction().isActive()) {
	        sesion.beginTransaction();
	    }

	    try {
	        // Obtener el producto por su ID
	        Producto producto = sesion.get(Producto.class, codigoProducto);

	        if (producto != null) {
	            // Eliminar el producto del DAO
	            productos.remove(producto);
	            System.out.println("Producto eliminado del DAO con éxito." );

	            // Realizar el commit de la transacción
	            sesion.getTransaction().commit();  
	        } else {
	            System.out.println("Producto no encontrado.");
	        } 
	    } catch (Exception e) {
	        sesion.getTransaction().rollback();
	        e.printStackTrace();
	    }
	}
	
}
