package aed.hibernate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productoObservacion")
public class ProductoObservacion {

	@Id
	@OneToOne
	@JoinColumn(name = "codproducto")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Producto codProducto;
	
	private String observacion;

	public Producto getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Producto codigoContacto) {
		this.codProducto = codigoContacto;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String nombreContacto) {
		this.observacion = nombreContacto;
	}

	@Override
	public String toString() {
		return this.observacion;
	}


}
