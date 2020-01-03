  

	package Automobiliai;

	public class Auto {

		public Double greitis;
		public Double laiko_intervalas;
		public Auto() {
			
			this.greitis = 0.0;
			this.laiko_intervalas = 0.0;
		}
		
		public void keistiGreiti ( double greitis ){
			
			this.greitis = greitis;
		}
		
		public void judeti ( double laiko_intervalas ) {
			
			this.laiko_intervalas += laiko_intervalas;			
		}
	
		public double nuvaziotas_atstumas () {
			
			return (this.greitis * laiko_intervalas);
		}
		
	}