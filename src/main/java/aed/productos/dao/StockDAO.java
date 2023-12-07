package aed.productos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import aed.hibernate.Producto;
import aed.hibernate.Stock;
import aed.hibernate.Tienda;

public class StockDAO {
	
	
	   private static List<Stock> stocks = new ArrayList<>();

	    public static List<Stock> getStocks() {
	        System.out.println("Obteniendo stocks del DAO");
	        return stocks;
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
	
	
}
