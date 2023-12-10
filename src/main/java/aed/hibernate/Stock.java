package aed.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Entity
@Table(name = "stock") 
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ManyToOne
	@JoinColumn(name = "codproducto")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Producto codProducto;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ManyToOne
	@JoinColumn(name = "codTienda") 
	private Tienda codTienda;

	@Column(name = "unidades")
	private int unidades;

	public Producto getCodProducto() {
		return codProducto; 
	}

	public void setCodProducto(Producto codProducto) {
		this.codProducto = codProducto;
	}

	public Tienda getCodTienda() {
		return codTienda;
	}

	public void setCodTienda(Tienda codTienda) {
		this.codTienda = codTienda;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
    public static String obtenerUnidades(int codProducto) {
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    sesion.beginTransaction();

	    HibernateCriteriaBuilder builder = sesion.getCriteriaBuilder();
	    CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
	    Root<Stock> root = query.from(Stock.class);
	    query.select(root);
	    query.where(builder.equal(root.get("id").get("Codproducto"), codProducto));

	    List<Stock> stockList = sesion.createQuery(query).getResultList();

	    sesion.getTransaction().commit();
	    sesion.close();

	    StringBuilder result = new StringBuilder();
	    for (Stock stock : stockList) {
	        result.append(stock.getUnidades()); 
	    }

	    return result.toString();
	}
	
	public static String obtenerTienda(int codProducto) {
	    Session sesion = HibernateUtil.getSessionFactory().openSession();
	    sesion.beginTransaction();

	    HibernateCriteriaBuilder builder = sesion.getCriteriaBuilder();
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
