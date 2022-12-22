package videostore;
import java.util.*;

// 1.0
public class Cliente {
    
	private String _nome;
    private List<Noleggio> _noleggi = new ArrayList<Noleggio>();

    public Cliente (String nome) {
        _nome = nome;
    }

    public void addNoleggio(Noleggio arg) {
        _noleggi.add(arg);
    }
    
    public String getNome(){
        return _nome;
    }

    public String rendiconto() {
       
    	double ammontareTotale = 0;
        Iterator<Noleggio> noleggi = _noleggi.iterator();
        String r = "Rendiconto noleggi per " + getNome() + " ";
        
        while (noleggi.hasNext()) {
            double questoAmmontare = 0;
            Noleggio ognuno = (Noleggio) noleggi.next();
            
            // ammontare per ogni noleggio
            switch (ognuno.getFilm().getCodicePrezzo()) {
            case Film.REGOLARE:
                questoAmmontare += 2;
                if (ognuno.getGiorniNoleggio() > 2)
                    questoAmmontare += (ognuno.getGiorniNoleggio() - 2) * 1.5;
                break;
            case Film.NOVITA:
                questoAmmontare += ognuno.getGiorniNoleggio() * 3;
                break;
            case Film.BAMBINI:
                questoAmmontare += 1.5;
                if (ognuno.getGiorniNoleggio() > 3)
                    questoAmmontare += (ognuno.getGiorniNoleggio() - 3) * 1.5;
                break;
            }

            // aggiungi al risultato
            r += ognuno.getFilm().getTitolo() + " " +
                 String.valueOf(questoAmmontare)+ " ";
            
            ammontareTotale += questoAmmontare;
        } // while

        // aggiungi totale
        r += "L'ammontare dovuto e' " + String.valueOf(ammontareTotale);
        return r;
    }
}
