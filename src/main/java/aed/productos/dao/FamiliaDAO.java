package aed.productos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aed.hibernate.Familia;
import aed.hibernate.HibernateUtil;
import aed.hibernate.Producto;

public class FamiliaDAO {

private static List<Familia> familias;
	
	static {
		
		familias = new ArrayList<Familia>();
		

		
	}
	
	public static List<Familia> getFamilias() {
		
		Session session = null;
		
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("FROM Familia", Familia.class).list();
			
		} finally {
				
			if(session != null) {
				session.close();
			}

		}
	}
	
	public static Familia findById(int codFamilia) {
		return familias
				.stream()
				.filter(f -> f.getCodFamilia() == codFamilia)
				.findFirst()
				.get();
	}


	public static Familia addFamilia(String nombre, Session sesion) {
		Transaction transaction = sesion.beginTransaction();
		Familia familia = new Familia();
		familia.setDenoFamilia(nombre);
		familias.add(familia);
		sesion.persist(familia);
		transaction.commit();
		return familia;

	}
	
    public static Familia obtenerFamiliaPorIdProducto(int idProducto, Session sesion) {
        try {
            // Obtener el producto por su ID
            Producto producto = sesion.get(Producto.class, idProducto); 

            // Verificar si el producto existe
            if (producto != null) {
                // Obtener la familia asociada al producto
                return producto.getcodFamilia();
            } else {
                System.out.println("Producto no encontrado.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
