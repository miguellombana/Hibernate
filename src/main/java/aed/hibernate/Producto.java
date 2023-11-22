package aed.hibernate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codproducto") 
    private long codProducto;
    
    @Column(name = "denoproducto", length = 100) 
    private String denoProducto;
    
    @Column(name = "preciobase") //
    private double precioBase;
    
    @Column(name = "codfamilia") 
    private long codFamilia;
    
    @Column(name = "congelado")
    private boolean congelado;

	public long getCodProducto() {
		return codProducto;
	}

	public String getDenoProducto() {
		return denoProducto;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public long getcodFamilia() {
		return codFamilia;
	}

	public boolean isCongelado() {
		return congelado;
	}

	public void setCodProducto(long codProducto) {
		this.codProducto = codProducto;
	}

	public void setDenoProducto(String denoProducto) {
		this.denoProducto = denoProducto;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public void setcodFamilia(int i) {
		this.codFamilia = i;
	}

	public void setCongelado(boolean congelado) {
		this.congelado = congelado;
	}

	public void setObservacion(ProductoObservacion productoObservacion) {
		// TODO Auto-generated method stub
		
	}
    
    
    
    
    
}
