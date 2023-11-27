package aed.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tienda")
public class Tienda {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codTienda")
	private int codTienda;
	
	@Column(name = "denoTienda", length = 20)
	private String denoTienda;
	
	@Column(name = "codigoPostal", length = 5)
	private String codigoPostal;

	public int getCodTienda() {
		return codTienda;
	}

	public void setCodTienda(int codTienda) {
		this.codTienda = codTienda;
	}

	public String getDenoTienda() {
		return denoTienda;
	}

	public void setDenoTienda(String denoTienda) {
		this.denoTienda = denoTienda;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	
	
	
	
}
