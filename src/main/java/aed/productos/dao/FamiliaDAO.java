package aed.productos.dao;

import java.util.ArrayList;
import java.util.List;

import aed.hibernate.Familia;

public class FamiliaDAO {

	private static List<Familia> familias; 
	
	
	static {
		
		familias = new ArrayList<Familia>();
		
		Familia f1 = new Familia();
		familias.add(f1);
		
	}
	
	public static List<Familia> getFamilias() {
		
		return familias;
		
		
	}
	
	/*public static Familia findByid(int codFamilia) {
		return familias
			//	.stream()
		//		.filter( f- -> f.getCodfamilia)
		
	}
	*/
}
