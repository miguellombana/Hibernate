package aed.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ManyToOne
	@JoinColumn(name = "codproducto")
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

}
