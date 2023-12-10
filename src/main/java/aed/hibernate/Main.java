package aed.hibernate;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import aed.productos.dao.FamiliaDAO;
import aed.productos.dao.ObservacionDAO;
import aed.productos.dao.ProductoDAO;
import aed.productos.dao.StockDAO;
import aed.productos.dao.TiendaDAO;

import java.util.List;

public class Main {

	public static void main(String[] args) {

	/*	Session sesion = HibernateUtil.getSessionFactory().openSession(); // crea la sesion

		try {

			// Añadiendo Valores por defecto
			Familia cereales = FamiliaDAO.addFamilia("Cereales", sesion);
			Familia Bebidas = FamiliaDAO.addFamilia("Bebidas", sesion);
			Familia Congelados = FamiliaDAO.addFamilia("Congelados", sesion);
			Familia Frutas = FamiliaDAO.addFamilia("Consumibles", sesion);

			Tienda Mercadona = TiendaDAO.addTienda("Mercadona", "38380", sesion);
			Tienda Hiperdino = TiendaDAO.addTienda("Hiperdino", "38380", sesion);
			Tienda Spar = TiendaDAO.addTienda("Spar", "38380", sesion);
			Tienda Alteza = TiendaDAO.addTienda("Alteza", "38380", sesion);

			Producto manzanas = ProductoDAO.addProducto("Manzanas", 20.00, Frutas, false, sesion);
			ProductoObservacion observacionmanzans = ObservacionDAO.addObservacion(manzanas,
					"Manzanas de muy buena calidad", sesion);
			
			Producto Naranjas = ProductoDAO.addProducto("Naranjas", 20.00, Frutas, false, sesion);
			ProductoObservacion observacionNaranjas = ObservacionDAO.addObservacion(manzanas,
					"naranjas de muy buena calidad", sesion);
			
			
			Producto Chocapicks = ProductoDAO.addProducto("Chocapicks", 20.00, cereales, false, sesion);

			
			
			
			
			
			
			
			
			

			Stock primero = StockDAO.addStock(manzanas, Alteza, 1000, sesion);
			Stock segundo = StockDAO.addStock(manzanas, Spar, 300, sesion);
			Stock tercero = StockDAO.addStock(manzanas, Mercadona, 400, sesion);

			// Menu de opciones
			Scanner scanner = new Scanner(System.in);
			int opcion = 0;

			while (opcion < 6) {
				System.out.println("\nMenú de Opciones:");
				System.out.println("1. Añadir Producto");
				System.out.println("2. Modificar Producto");
				System.out.println("3. Borrar Producto");
				System.out.println(
						"4. Visualizar todos los productos con todos sus datos incluyendo la observación de la que tenga");
				System.out.println(
						"5. Visualizar todos los productos y sus stock, incluyendo los datos de las tiendas y familias de los productos.");
				System.out.println("6. Salir");
				System.out.print("Ingrese la opción deseada: ");

				opcion = scanner.nextInt();

				switch (opcion) {
				case 1:
					Scanner sc = new Scanner(System.in);
					System.out.println("Introduce el Nombre del producto: ");
					String nombre = sc.next();
					System.out.println("Introduce el precio del producto: ");
					Double precio = sc.nextDouble();

					System.out.println("Introduce el codigo de  la familia  del producto: ");
					FamiliaDAO.getFamilias();

					int codigoFamilia = sc.nextInt();

					// Buscar la familia por el código
					try {
						Familia familia = FamiliaDAO.findById(codigoFamilia);
						System.out.println("Indica si el producto es congelado (Si/No): ");
						boolean congelado = obtenerRespuestaUsuario(scanner);

						System.out.println("Quieres añadir Observacion al producto (Si/No): ");

						boolean agregarObservacion = obtenerRespuestaUsuario(scanner);

						if (agregarObservacion) {

							System.out.println("Indica la observacion del producto: ");
							String observacion = sc.nextLine();

							// Agregar el producto con la familia seleccionada
							Producto nuevo = ProductoDAO.addProducto(nombre, precio, familia, congelado, sesion);
							ObservacionDAO.addObservacion(nuevo, observacion, sesion);

						} else {
							Producto nuevo = ProductoDAO.addProducto(nombre, precio, familia, congelado, sesion);
						}

					} catch (Exception e) {

						System.out.println("La familia con el código proporcionado no existe.");

					}

					opcion = 0;
					break;

				case 2:

					System.out.println("Introduce el id del producto a modificar : ");
					Scanner sc1 = new Scanner(System.in);
					int idModificar = sc1.nextInt();
					System.out.println("Introduce el nuevo nombre del producto : ");
					String nombreModificar = sc1.next();
					System.out.println("Introduce el nuevo Precio del producto : ");
					double precioModificar = sc1.nextDouble();

					ProductoDAO.modificarProducto(idModificar, nombreModificar, precioModificar, sesion);

					break;
				case 3:

					System.out.println("Introduce el id del producto a borrar : ");
					Scanner sc2 = new Scanner(System.in);
					int idBorrar = sc2.nextInt();
					ProductoDAO.deleteProducto(idBorrar, sesion);

					break;
				case 4:
					ProductoDAO.ListarProductos(sesion);

					break;
				case 5:
					ProductoDAO.listarProductosConStock(sesion);
					break;
				case 6:
					// Salir
					System.out.println("Saliendo del programa.");
					break;
				default:
					System.out.println("Opción no válida. Inténtelo de nuevo.");
					break;
				}

			}
			while (opcion != 6)
				;

		} catch (Exception e) {
			// En caso de error, realiza un rollback
			
			e.printStackTrace(); // Manejo de excepciones adecuado
		} finally {
		    if (sesion.getTransaction().isActive()) {
	
		        sesion.getTransaction().rollback();
		    }
			// Cierre de la sesión al final
			sesion.close();
		}
	}

	private static boolean obtenerRespuestaUsuario(Scanner scanner) {
		while (true) {
			String respuesta = scanner.nextLine().toLowerCase();

			if (respuesta.equals("si")) {
				return true;
			} else if (respuesta.equals("no")) {
				return false;
			} else {
				System.out.println("Por favor, ingresa 'Si' o 'No'.");
			}
		}
	}*/
}
	}
