package cursoAndroid.cursoandroid;

import java.util.ArrayList;
import java.util.List;



public class Row{		
	List <Cell> posisiones;
	
	Row(int cels){
		posisiones = new ArrayList<Cell>();
		for(int i = 0;i < cels; i++){
			posisiones.add(new Cell());
		}
	}	
}	