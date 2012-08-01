package cursoAndroid.cursoandroid;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

public class Tablero {
	String Partida;
	int alto;
	int ancho;
	List<Row> rows;		
	List<Barco> Barcos;
	//21 espacios
	int barcos_5 = 1;
	int barcos_4 = 1;
	int barcos_3 = 2;
	int barcos_2 = 3;
	public Boolean EdicionTerminada = false;
	
	public Tablero(){
		alto = 10;
		ancho = 10;
		rows = new ArrayList<Row>();
		for(int i = 0; i < ancho; i++){
			rows.add(new Row(alto));
		}
		Barcos = new ArrayList<Barco>();
	}
	
	public Boolean	CrearBarco(Point ini, Point fin){
		try{
			Barco barco = new Barco(ini, fin);
			
			if (barco.Ini.x == barco.Fin.x ){
				barco.largo = barco.Fin.x - barco.Ini.x;				
				for(int i = barco.Ini.y; i < barco.Fin.y; i++){
					if(haybarco(new Point(barco.Ini.x, i))){
						return false;
					}
				}
			}			
			if (barco.Ini.y == barco.Fin.y ){
				barco.largo = barco.Fin.y - barco.Ini.y;
				for(int i = barco.Ini.x; i < barco.Fin.x; i++){
					if(haybarco(new Point(i, barco.Ini.y))){
						return false;
					}
				}
			}
			switch (barco.largo) {
			case 2:
				if(barcos_2 > 0){
					barcos_2--;
				}else{
					return false;
				}
				break;
			case 3:
				if(barcos_3 > 0){
					barcos_3--;
				}else{
					return false;
				}					
				break;
			case 4:	
				if(barcos_4 > 0){
					barcos_4--;
				}else{
					return false;
				}
				break;
			case 5:	
				if(barcos_5 > 0){
					barcos_5--;
				}else{
					return false;
				}
				break;
			default:
				return false;
			}
			if(Barcos.add(barco)){
				return true;
			}
			return false;
		}catch(Exception ex){
			return false;
		}		
	}
	
	private Boolean haybarco(Point punto){
		if(this.rows.get(punto.x).posisiones.get(punto.y).barco){		
			return true;
		}
		return false;
	}
	
}
