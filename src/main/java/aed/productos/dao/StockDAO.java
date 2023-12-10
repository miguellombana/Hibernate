package aed.productos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import aed.hibernate.HibernateUtil;
import aed.hibernate.Producto;
import aed.hibernate.Stock;
import aed.hibernate.Tienda;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class StockDAO {
	
	
	   private static List<Stock> stocks = new ArrayList<>();

	   public static List<Stock> getStocks() {
		    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		        // Realiza la consulta para obtener información adicional sobre el stock
		        Query<Stock> query = session.createQuery("FROM Stock", Stock.class);
		        return query.list();
		    } catch (Exception e) {
		        e.printStackTrace();
		        // Maneja la excepción según tus necesidades (lanzarla, loggearla, etc.)
		        return null;
		    }
		}
 
	
	
	
	
	
	    public static Stock addStock(Producto producto, Tienda tienda, int unidades, Session sesion) {
	        Transaction transaction = sesion.beginTransaction();

	        Stock stock = new Stock();
	        stock.setCodProducto(producto);
	        stock.setCodTienda(tienda);
	        stock.setUnidades(unidades);

	        sesion.persist(stock);
	        transaction.commit();

	        stocks.add(stock);

	        return stock;
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
    public static List<Stock> obtenerStockPorProducto(Producto producto, Session session) {
        String hql = "FROM Stock WHERE producto = :producto";
        Query<Stock> query = session.createQuery(hql, Stock.class);
        query.setParameter("producto", producto);
        return query.getResultList();
    }


    public static void eliminarStock(int codigoProducto, Session sesion) {
        Transaction transaction = sesion.beginTransaction();

        try {
            // Buscar el stock por el código del producto
            Query<Stock> query = sesion.createQuery(
                    "FROM Stock WHERE codProducto.codProducto = :codigoProducto",
                    Stock.class);
            query.setParameter("codigoProducto", codigoProducto);
            Stock stock = query.uniqueResult();

            if (stock != null) {
                // Eliminar el stock
                sesion.remove(stock);
                System.out.println("Stock eliminado con éxito.");
            } else {
                System.out.println("No se encontró stock para el producto con código " + codigoProducto);
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }


	
}
