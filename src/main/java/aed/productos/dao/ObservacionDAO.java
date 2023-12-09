package aed.productos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import aed.hibernate.Producto;
import aed.hibernate.ProductoObservacion;

public class ObservacionDAO {

	public static ProductoObservacion addObservacion(Producto producto, String observacion, Session session) {
		Transaction transaction = session.beginTransaction();
		ProductoObservacion observacionNueva = null;

		try {
			observacionNueva = new ProductoObservacion();
			observacionNueva.setCodProducto(producto);
			observacionNueva.setObservacion(observacion);

			session.persist(observacionNueva);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return observacionNueva;
	}

	public static ProductoObservacion obtenerObservacionPorProducto(Producto producto, Session session) {
		String hql = "FROM ProductoObservacion WHERE codProducto = :producto";
		return session.createQuery(hql, ProductoObservacion.class).setParameter("producto", producto).uniqueResult();
	}

	public static void eliminarObservacionProducto(int codigoProducto, Session sesion) {
		Transaction transaction = sesion.beginTransaction();

		try {
			// Buscar la observación por el código del producto
			Query<ProductoObservacion> query = sesion.createQuery(
					"FROM ProductoObservacion WHERE codProducto.codProducto = :codigoProducto",
					ProductoObservacion.class);
			query.setParameter("codigoProducto", codigoProducto);
			ProductoObservacion observacion = query.uniqueResult();

			if (observacion != null) {
				// Eliminar la observación
				sesion.remove(observacion);
				System.out.println("Observación eliminada con éxito.");
			} else {
				System.out.println("No se encontró una observación para el producto con código: " + codigoProducto);
			}

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	public static void modificarObservacionProducto(int productId, String observacion, Session sesion) {
	
	    try {
	        // Buscar la observación por el código del producto
	        Query<ProductoObservacion> query = sesion.createQuery(
	                "FROM ProductoObservacion WHERE codProducto.codProducto = :codigoProducto",
	                ProductoObservacion.class);
	        query.setParameter("codigoProducto", productId);
	        ProductoObservacion observacionExistente = query.uniqueResult();

	        if (observacionExistente != null) {
	            // Modificar la observación existente
	            observacionExistente.setObservacion(observacion);
	            sesion.merge(observacionExistente);
	            System.out.println("Observación modificada con éxito.");
	        } else {
	            // Crear una nueva observación si no existe
	            ProductoObservacion nuevaObservacion = new ProductoObservacion();
	            nuevaObservacion.setCodProducto(ProductoDAO.getProductoPorId(productId, sesion)); // Asume que hay un constructor en Producto que acepta el ID
	            nuevaObservacion.setObservacion(observacion);
	            sesion.save(nuevaObservacion);
	            System.out.println("Observación creada con éxito.");
	        }


	    } catch (Exception e) {

	        e.printStackTrace();
	    }
	}
}
