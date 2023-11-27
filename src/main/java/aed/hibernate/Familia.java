package aed.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Familia")
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codFamilia")
    private int codFamilia;
    
    @Column(name = "denofamilia", length = 50)
    private String denoFamilia;

	public int getCodFamilia() {
		return codFamilia;
	}

	public void setCodFamilia(int codFamilia) {
		this.codFamilia = codFamilia;
	}

	public String getDenoFamilia() {
		return denoFamilia;
	}

	public void setDenoFamilia(String denoFamilia) {
		this.denoFamilia = denoFamilia;
	}


	@Override
	public String toString() {
		return this.denoFamilia;
	}
	
	
	
	
	
}