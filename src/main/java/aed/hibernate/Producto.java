package aed.hibernate;
import org.hibernate.Session;
import java.util.List;

import aed.productos.dao.ObservacionDAO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Entity
@Table(name = "producto")
public class Producto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codproducto") 
    private int codProducto;
    
    @Column(name = "denoproducto", length = 100) 
    private String denoProducto;
    
    @Column(name = "preciobase") //
    private double precioBase;
    
    @ManyToOne
    @JoinColumn(name = "codFamilia") 
    private Familia codFamilia;
    
    @Column(name = "congelado")
    private boolean congelado;

	public int getCodProducto() {
		return codProducto;
	}

	public String getDenoProducto() {
		return denoProducto;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public Familia getcodFamilia() {
		return codFamilia;
	}

	public boolean isCongelado() {
		return congelado;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	public void setDenoProducto(String denoProducto) {
		this.denoProducto = denoProducto;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public void setcodFamilia(Familia i) {
		this.codFamilia = i;
	}

	public void setCongelado(boolean congelado) {
		this.congelado = congelado;
	}

	   public ProductoObservacion obtenerObservacion() {
		      
		   Session sesion = HibernateUtil.getSessionFactory().openSession();
	        // Lógica para obtener la observación del producto
	        ProductoObservacion observacion = ObservacionDAO.obtenerObservacionPorProducto(this, sesion);
	        if (observacion != null) {
	            return observacion;
	        } else {
	            return null;
	        }
	    }
	   
		public Familia getDenoFamilia() {
		    Session sesion = HibernateUtil.getSessionFactory().openSession();
		    sesion.beginTransaction();
		    
		    Familia familia = sesion.get(Familia.class, this.codFamilia);
		    
		    sesion.getTransaction().commit();
		    sesion.close();
		    
		    return familia;
		}
		
		
		public static String obtenerUnidades(int codProducto) {
		    Session sesion = HibernateUtil.getSessionFactory().openSession();
		    sesion.beginTransaction();

		    CriteriaBuilder builder = sesion.getCriteriaBuilder();
		    CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
		    Root<Stock> root = query.from(Stock.class);
		    query.select(root);
		    query.where(builder.equal(root.get("id").get("Codproducto"), codProducto));

		    List<Stock> stockList = sesion.createQuery(query).getResultList();

		    sesion.getTransaction().commit();
		    sesion.close();

		    StringBuilder result = new StringBuilder();
		    for (Stock stock : stockList) {
		        result.append(stock.unidades()); 
		    }

		    return result.toString();
		}
		
		public static String obtenerDenoTienda(int codProducto) {
		    Session sesion = HibernateUtil.getSessionFactory().openSession();
		    sesion.beginTransaction();

		    CriteriaBuilder builder = sesion.getCriteriaBuilder();
		    CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
		    Root<Stock> root = query.from(Stock.class);
		    query.select(root);
		    query.where(builder.equal(root.get("id").get("Codproducto"), codProducto));

		    List<Stock> stockList = sesion.createQuery(query).getResultList();

		    sesion.getTransaction().commit();
		    sesion.close();

		    StringBuilder result = new StringBuilder();
		    for (Stock stock : stockList) {
		        result.append(stock.getDenoTienda());
		    }
		    
		    return result.toString();
		}
		
		public static String obtenerCodTienda(int codProducto) {
		    Session sesion = HibernateUtil.getSessionFactory().openSession();
		    sesion.beginTransaction();

		    CriteriaBuilder builder = sesion.getCriteriaBuilder();
		    CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
		    Root<Stock> root = query.from(Stock.class);
		    query.select(root);
		    query.where(builder.equal(root.get("id").get("Codproducto"), codProducto));

		    List<Stock> stockList = sesion.createQuery(query).getResultList();

		    sesion.getTransaction().commit();
		    sesion.close();

		    StringBuilder result = new StringBuilder();
		    for (Stock stock : stockList) {
		        result.append(stock.getCodTienda());
		    }
		    
		    return result.toString();
		}
	   
	   
}
