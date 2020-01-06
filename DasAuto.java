
	package Automobiliai;

	import java.io.*;
	
	public class DasAuto {
		
		public static double atstumasTarpAutomobiliu ( double Atstumas, double nuvaziuotas_atstumas_1, double nuvaziuotas_atstumas_2 ) {
			
			double atstumas_tarp_automobiliu  =  Atstumas - (nuvaziuotas_atstumas_1 + nuvaziuotas_atstumas_2 );
				
			if ( atstumas_tarp_automobiliu <= 0 ) {
				
					atstumas_tarp_automobiliu = (nuvaziuotas_atstumas_1 + nuvaziuotas_atstumas_2 );
		  
			}
			return atstumas_tarp_automobiliu;
		}
		
		public static void lentelesEilute ( double judeti
				, boolean automobilis_1_dar_nepasieke_paskirties_vietos
				, double nuvaziuotas_atstumas_1
				, double atstumas_tarp_automobiliu
				, boolean automobilis_2_dar_nepasieke_paskirties_vietos
				, double nuvaziuotas_atstumas_2 
		) {
			System.out.print ( String.format( "| %10.0f |", judeti ) );
			
			if ( automobilis_1_dar_nepasieke_paskirties_vietos ) {
		
				System.out.print ( String.format( " %10.0f |", nuvaziuotas_atstumas_1 ) );
				
			} else  {
				
				System.out.print ( String.format( " %10s |", " " ) );					
			}
			System.out.print ( String.format( " %10.0f |", atstumas_tarp_automobiliu ) );
			
			if ( automobilis_2_dar_nepasieke_paskirties_vietos ) {
			
				System.out.print ( String.format( " %10.0f |", nuvaziuotas_atstumas_2 ) );
				
			} else {
				
				System.out.print ( String.format( " %10s |", " " ) );
			}
			System.out.println();
		}
			
		public static void main ( String[] args ) {
		
			String s;
			Auto [] Automobiliai = new Auto[ 10 ];
			Auto Automobilis_1;
			Auto Automobilis_2;
			
			double nuvaziotas_atstumas = 0.0;
			double greitis = 0.0;
			double laiko_intervalas = 0.0;
			
			double laiko_intervalas_x = 0.0;
			double liko_nuvaziuoti_1 = 0.0;
			double liko_nuvaziuoti_2 = 0.0;
			
			double maza_paklaida = 0.01;
			
			double atstumas_tarp_automobiliu  = 0.0;
			double judeti = 0.0;	
			double Atstumas = 0.0;
			double keistiGreiti = 0.0;
			
			double vaziavimo_trukme = 0.0;
			
			double skaiciojamas_atstumas = 0.0;
			
			InputStreamReader stream = new InputStreamReader(System.in);
					
			BufferedReader reader=new BufferedReader(stream);	
				
			System.out.println ( " Masinu aikstele:" );
		
			Automobilis_1 = new Auto ();		
			
			Automobilis_2 = new Auto ();
							
			try{
					
				System.out.println ();
		
				System.out.println ( " ivesti laika Sekundem" );
				s= reader.readLine();
				laiko_intervalas = Double.parseDouble( s ); 

				System.out.println ( "ivesti greiti 1 automobiliui" );
				s= reader.readLine();
				greitis = Double.parseDouble( s ); 	
				
				Automobilis_1.keistiGreiti (greitis);
					
				System.out.println ( "ivesti greiti 2 automobiliui" );
				s= reader.readLine();
				greitis = Double.parseDouble( s ); 	
					
				Automobilis_2.keistiGreiti (greitis);
			
				System.out.println ( " ivesti automobiliu atstuma Metrais" );
				s= reader.readLine();
				Atstumas = Double.parseDouble( s ); 
						
			} catch ( IOException e ) {
				
				System.out.println ( "Skaitymo klaida" );
			}

			System.out.println ( "------------------------------------------------------" );
			System.out.println ( "|   Laikas |  Nuvaziuotas atstumas Metrais           |" );
			System.out.println ( "|             |   Auto1   | Atstumas tarp |  Auto 2  |" );
			System.out.println ( "------------------------------------------------------" );
			
			boolean automobilis_1_dar_nepasieke_paskirties_vietos = true;
			boolean automobilis_2_dar_nepasieke_paskirties_vietos = true;
			
			while ( 																				// bent vienas automobilis nėra nuvažiavęs iki ..
					( 
							automobilis_1_dar_nepasieke_paskirties_vietos						 				// .. paskirties vietos
						= 
							Automobilis_1.nuvaziotas_atstumas() <= ( Atstumas - maza_paklaida )
					)
		
				|| 	
					( 
							automobilis_2_dar_nepasieke_paskirties_vietos
						=
							Automobilis_2.nuvaziotas_atstumas() <= ( Atstumas - maza_paklaida )
					)
			)  {
				
				if ( automobilis_1_dar_nepasieke_paskirties_vietos ) {
					
					liko_nuvaziuoti_1  =  Atstumas - Automobilis_1.nuvaziotas_atstumas();  
					
					laiko_intervalas_x = laiko_intervalas;													//  keisim, jei turi važiuoti trumpiau nei laiko žingsnis
					
					if ( ( Automobilis_1.greitis * laiko_intervalas ) > liko_nuvaziuoti_1 ) {							// ? turi važiuoti trumpiau, nei laiko žingsnis
												
						laiko_intervalas_x = liko_nuvaziuoti_1  / Automobilis_1.greitis;							// nauja, trumpam pakeista laiko žingsnio reikšmė
						double auto2_nuvaziuotas_atstumas = Automobilis_2.greitis * laiko_intervalas_x; 			// skaičiuojam, kiek nuvažiavo antras automobilis ..
																								// .. per pakeistą laiko žingsnį
						lentelesEilute ( 
								judeti + laiko_intervalas_x
								, true
								, Automobilis_1.nuvaziotas_atstumas() + liko_nuvaziuoti_1
								, atstumasTarpAutomobiliu ( 
									Atstumas
									, Automobilis_1.nuvaziotas_atstumas() + liko_nuvaziuoti_1
									, Automobilis_2.nuvaziotas_atstumas() + auto2_nuvaziuotas_atstumas 
								)
								, automobilis_2_dar_nepasieke_paskirties_vietos
								, Automobilis_2.nuvaziotas_atstumas() + auto2_nuvaziuotas_atstumas 
						);
					}
					Automobilis_1.judeti ( laiko_intervalas_x );
				}
				
				if ( automobilis_2_dar_nepasieke_paskirties_vietos ) {
					
					liko_nuvaziuoti_2  = Atstumas - Automobilis_2.nuvaziotas_atstumas();  
					
					 laiko_intervalas_x = laiko_intervalas;
					
					if ( ( Automobilis_2.greitis * laiko_intervalas ) > liko_nuvaziuoti_2 ) {
												
						laiko_intervalas_x = liko_nuvaziuoti_2  / Automobilis_2.greitis;
						
						double auto1_nuvaziuotas_atstumas = Automobilis_1.greitis * laiko_intervalas_x; 

						lentelesEilute (
						
								judeti + laiko_intervalas_x
								, automobilis_1_dar_nepasieke_paskirties_vietos
								, Automobilis_1.nuvaziotas_atstumas() + auto1_nuvaziuotas_atstumas
								, atstumasTarpAutomobiliu ( 
									Atstumas
									, Automobilis_1.nuvaziotas_atstumas() + auto1_nuvaziuotas_atstumas
									, Automobilis_2.nuvaziotas_atstumas() + liko_nuvaziuoti_2 
								)
								, true
								, Automobilis_2.nuvaziotas_atstumas() + liko_nuvaziuoti_2 						
						);
					}
					Automobilis_2.judeti ( laiko_intervalas_x );
				}
				judeti += laiko_intervalas; 
				
				atstumas_tarp_automobiliu = atstumasTarpAutomobiliu ( Atstumas, Automobilis_1.nuvaziotas_atstumas(), Automobilis_2.nuvaziotas_atstumas() );
				
				lentelesEilute ( 
					judeti
					, automobilis_1_dar_nepasieke_paskirties_vietos
					, Automobilis_1.nuvaziotas_atstumas()
					, atstumas_tarp_automobiliu, automobilis_2_dar_nepasieke_paskirties_vietos
					, Automobilis_2.nuvaziotas_atstumas() 
				);
			}
			System.out.println ( "------------------------------------------------------" );
			
			System.out.println ( 
			
				String.format ( "| %10.0f |  %10.0f | %10.0f | %10.0f |",
			
					judeti,
						Automobilis_1.nuvaziotas_atstumas(),
							atstumas_tarp_automobiliu,
								Automobilis_2.nuvaziotas_atstumas()
				)
			);
			
			System.out.println ( "------------------------------------------------------" );
	
		}	
	}