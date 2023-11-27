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
    private int codProducto;
    
    @Column(name = "denoproducto", length = 100) 
    private String denoProducto;
    
    @Column(name = "preciobase") //
    private double precioBase;
    
    @Column(name = "codfamilia") 
    private int codFamilia;
    
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

	public int getcodFamilia() {
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
