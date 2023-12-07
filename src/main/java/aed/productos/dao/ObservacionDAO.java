package aed.productos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
	    return session.createQuery(hql, ProductoObservacion.class)
	            .setParameter("producto", producto)
	            .uniqueResult();
	}
}
