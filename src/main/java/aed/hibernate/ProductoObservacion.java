package aed.hibernate;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "productoObservacion")
public class ProductoObservacion {

@Id 
private Integer codProducto;
private String observacion;


public Integer getCodProducto() {
	return codProducto;
}
public void setCodProducto(Integer codigoContacto) {
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
