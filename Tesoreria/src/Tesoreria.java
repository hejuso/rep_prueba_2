
public class Tesoreria {

	public static void main(String[] args) {

		//VARIABLES
		int gastos [] = {-5,-120,-65};
		int ingresos [] = {10,3,40,120,60};		
		int balance=0;
		//LOGICA
		
		for(int i=0;i<ingresos.length;i++)
		{
			balance=balance+ingresos[i];
		}
		
		for(int i=0;i<gastos.length;i++)
		{
			balance=balance+gastos[i];
		}
		//IMPRESION
		System.out.println("El balance total es: "+balance);
		
		//prueba
		
		}

}
