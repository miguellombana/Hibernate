package aed.hibernate;
import org.hibernate.Session;

import aed.productos.dao.ObservacionDAO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
}
