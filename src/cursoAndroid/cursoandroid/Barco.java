package cursoAndroid.cursoandroid;

import android.graphics.Point;

public class Barco{
	public int largo;
	public Point Ini;		
	public Point Fin;
	
	public Barco(Point ini,Point fin) throws Exception{
		if(ini.x > fin.x){
			Point tem = ini;
			ini = fin;
			fin = tem;
		}
		if(ini.y > fin.y){
			Point tem = ini;
			ini = fin;
			fin = tem;
		}			
		this.Ini = ini;
		this.Fin = fin;
		if(!validar()){
			throw new Exception();
		}
	}
	
	public Boolean validar(){
		if (Ini.x == Fin.x){
			int val = Fin.y - Ini.y;
			if(val >= 2 && val <= 5){
				return true;
			}else{
				return false;
			}
		}else if(Ini.y == Fin.y){
			int val = Fin.x - Ini.x;
			if(val >= 2 && val <= 5){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}