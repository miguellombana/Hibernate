package aed.hibernate;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
public class Main {

	public static void main(String[] args) {

		Session sesion = HibernateUtil.getSessionFactory().openSession(); // crea la sesion
		sesion.beginTransaction();

		try {

			Funciones.addFamilia("Cereales", sesion);
			Funciones.addFamilia("Bebidas", sesion);
			Funciones.addFamilia("Congelados", sesion);
			Funciones.addFamilia("Consumibles", sesion);

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

			sesion.getTransaction().commit();

			Producto a = Funciones.addProducto("Lechugas", 10.00, verduras, false, sesion);
			Funciones.addProducto("Tomates", 10.00, verduras, false, sesion);
			Funciones.addProducto("Manzanas", 10.00, frutas, false, sesion);
			Funciones.addProducto("Gambas", 10.00, congelados, true, sesion);
			

			
			
			Stock stock1 = new Stock();
			stock1.setCodProducto(a);
			stock1.setCodTienda(tienda1);
			stock1.setUnidades(100);
			sesion.beginTransaction();
			sesion.persist(stock1);
			sesion.getTransaction().commit();
			Scanner scanner = new Scanner(System.in);
			int opcion = 0;

			while (opcion < 6) {
				System.out.println("\nMenú de Opciones:");
				System.out.println("1. Añadir Producto");
				System.out.println("2. Modificar Producto");
				System.out.println("3. Borrar Producto");
				System.out.println("4. Visualizar todos los productos con todos sus datos incluyendo la observación de la que tenga");
				System.out.println("5. Visualizar todos los productos y sus stock, incluyendo los datos de las tiendas y familias de los productos.");
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
					System.out.println("Introduce  la familia  del producto: ");
					Familia familia = verduras;
					System.out.println("Indica si el producto es congelado (true/false): ");
					boolean congelado = sc.nextBoolean();
					Funciones.addProducto(nombre, precio, familia, congelado, sesion);
					opcion = 0 ; 
					break;

				case 2:

					System.out.println("Introduce el id del producto a modificar : ");
					Scanner sc1 = new Scanner(System.in);
					int idModificar = sc1.nextInt();
					System.out.println("Introduce el nuevo nombre del producto : ");
					String nombreModificar = sc1.next();
					System.out.println("Introduce el nuevo Precio del producto : ");
					double precioModificar = sc1.nextDouble();

					Funciones.modificarProducto(idModificar, nombreModificar, precioModificar, sesion);
					
					
					break;
				case 3:
					
					
					System.out.println("Introduce el id del producto a borrar : ");
					Scanner sc2 = new Scanner(System.in);
					int idBorrar = sc2.nextInt();
					Funciones.deleteProducto(idBorrar, sesion);
					
					break;
				case 4:
						Funciones.ListarProductos(sesion);

					break;
				case 5:
						Funciones.Visualizacion01(sesion);
					break;
				case 6:
					// Salir
					System.out.println("Saliendo del programa.");
					break;
				default:
					System.out.println("Opción no válida. Inténtelo de nuevo.");
					break;
				}

			} while (opcion != 6);

	

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


