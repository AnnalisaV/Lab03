package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ModelDictionary {
	
	private Set<String> dictionary= new HashSet<String>(); 
	private List<String> listaParoleErrate= new LinkedList<String>();
	private String lingua=""; // lingua corrente

	/**
	 * Permette di avere il dizionario della lingua desiderata 
	 * @param language lingua selezionata 
	 */
	public void loadDictionary (String language) {
		this.lingua= language; 
		// che lingua e' stata selezionata 
		if (language.compareTo("Italiano")==0) {
			
			try {
				FileReader fr= new FileReader("C:\\Users\\HP\\git\\Lab03\\Lab03_SpellChecker\\src\\main\\resources\\Italian.txt"); 
				BufferedReader br= new BufferedReader(fr );
				
				String parola;
				while ( (parola= br.readLine()) != null) {
					// aggiungo al dictionary
					this.dictionary.add(parola);
				}
				fr.close();
			}
			catch (IOException ioe) {
				System.out.println("Anomalia nella lettura del file : "+ioe); 
			}
			
		}
		else if (language.compareTo("English")==0) {
			try {
				FileReader fr= new FileReader("C:\\Users\\HP\\git\\Lab03\\Lab03_SpellChecker\\src\\main\\resources\\English.txt"); 
				BufferedReader br= new BufferedReader(fr );
				
				String parola;
				while ( (parola= br.readLine()) != null) {
					// aggiungo al dictionary
					this.dictionary.add(parola);
				}
				fr.close();
			}
			catch (IOException ioe) {
				System.out.println("Anomalia nella lettura del file : "+ioe); 
			}
			
			
		}
	}
	
	/**
	 * Controlla le parole inserite dall'utente 
	 * @param input testo da controllare gia' trasformato in stringa 
	 * @param lingua dizionario che e' stato selezionato
	 * @return lista di parole errate = non presenti in quel dizionario 
	 */
	public List<String> spellCheckText(String input, String language){
		
	if (language.compareTo(lingua) !=0) {
			this.dictionary.clear(); // si cambia dizionario
			this.loadDictionary(language);
		}
	
		// altrimenti la lingua e' sempre la stessa quindi si prosegue 
		listaParoleErrate.clear(); // svuota la lista 
		String[] lista = input.split(" ");// da stringa unica ad 'elenco'
		
		for (String s: lista) {
			if (!this.dictionary.contains(s)) {
			listaParoleErrate.add(s); 
				}
			
		}
		return listaParoleErrate; 
	}
	
}
