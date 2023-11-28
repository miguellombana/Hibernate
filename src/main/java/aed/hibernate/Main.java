package aed.hibernate;

import org.hibernate.Session;

public class Main {

	public static void main(String[] args) {

		Session sesion = HibernateUtil.getSessionFactory().openSession(); // crea la sesion
		sesion.beginTransaction();

		try {

			addFamilia("Cereales", sesion);
			addFamilia("Bebidas", sesion);
			addFamilia("Congelados", sesion);
			addFamilia("Consumibles", sesion);

			Familia verduras = new Familia();
			verduras.setDenoFamilia("Verduras");
			sesion.persist(verduras);
			
			Familia frutas = new Familia();
			frutas.setDenoFamilia("Frutas");
			sesion.persist(frutas);

			Familia congelados = new Familia();
			congelados.setDenoFamilia("Congelados");
			sesion.persist(congelados);
			
			Tienda tienda1 = new Tienda();
			tienda1.setDenoTienda("T001");
			tienda1.setCodigoPostal("38370");
			sesion.persist(tienda1);

			
			

			Producto a = addProducto("Lechugas", 10.00, verduras, false, sesion);
			addProducto("Tomates", 10.00, verduras, false, sesion);
			addProducto("Manzanas", 10.00, frutas, false, sesion);
			addProducto("Gambas", 10.00, congelados, true, sesion);
			
			
			Stock stock1 = new Stock();
			stock1.setCodProducto(a);
			stock1.setCodTienda(tienda1);
			stock1.setUnidades(100);
			sesion.persist(stock1);
			
			

			/*
			 * 
			 * 
			 * 
			 * 
			 * Producto producto = new Producto(); producto.setCodProducto(1);
			 * producto.setDenoProducto("Manzanas"); producto.setcodFamilia(familia);
			 * producto.setCongelado(false); sesion.persist(producto);
			 * 
			 * 
			 * Tienda tienda = new Tienda(); tienda.setCodTienda(1);
			 * tienda.setDenoTienda("Mercadona"); tienda.setCodigoPostal("10001");
			 * sesion.persist(tienda);
			 * 
			 * 
			 * Stock stock = new Stock(); stock.setCodProducto(1); stock.setCodTienda(1);
			 * stock.setUnidades(100); sesion.persist(stock);
			 * 
			 * 
			 * ProductoObservacion productoObservacion = new ProductoObservacion();
			 * //productoObservacion.setCodProducto(1);
			 * productoObservacion.setObservacion("Todo correctoiiii");
			 * sesion.persist(productoObservacion);
			 */
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

		Producto producto = new Producto();
		producto.setDenoProducto(nombre);
		producto.setPrecioBase(precio);
		producto.setcodFamilia(familia);
		producto.setCongelado(congelado);

		sesion.persist(producto);
		return producto;

	}

}
