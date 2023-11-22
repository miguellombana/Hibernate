package aed.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codproducto") 
    private long codProducto;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codTienda")
	private long codTienda;
	@Column(name = "unidades")
	private int unidades;
	public long getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(long codProducto) {
		this.codProducto = codProducto;
	}
	public long getCodTienda() {
		return codTienda;
	}
	public void setCodTienda(long codTienda) {
		this.codTienda = codTienda;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	
	
	
	
	
	
	
}