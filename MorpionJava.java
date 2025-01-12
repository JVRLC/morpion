import java.util.Scanner;

public class MorpionJava {

    // Méthode pour initialiser le schéma avec des cases vides
    private static void initialiser(char[][] schema) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                schema[i][j] = ' ';
            }
        }
    }

    // Méthode pour afficher le schéma
    private static void voir(char[][] schema) {
        System.out.println(" -------------");
        for (int i = 0; i < 3; i++) {
            System.out.print(" | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(schema[i][j] + " | ");
            }
            System.out.println("\n -------------");
        }
    }

    // Méthode pour gérer chaque nouveau déplacement
    private static void nouveauDeplacement(char[][] schema, char tour) {
    	Scanner clavier = new Scanner(System.in);
        int ligne;
        int colonne;

        // Boucle pour s'assurer que le joueur entre des coordonnées valides
        while (true) {
            System.out.println("[Tour " + tour + "] Entrez la ligne et la colonne du déplacement (0, 1 ou 2)");
            ligne = clavier.nextInt();
            colonne = clavier.nextInt();

            // Vérifier si les coordonnées sont dans les limites de la grille
            if (ligne >= 0 && ligne < 3 && colonne >= 0 && colonne < 3) {
                // Vérifier si la case est vide
                if (schema[ligne][colonne] == ' ') {
                    schema[ligne][colonne] = tour;  // Placer le symbole 'X' ou 'O'
                    break;  // Sortir de la boucle si le déplacement est valide
                } else {
                    System.out.println("La case est déjà occupée, veuillez réessayer.");
                }
            } else {
                System.out.println("Coordonnées hors de la grille, veuillez réessayer.");
            }
        }
    }

    // Méthode pour vérifier si un joueur a gagné
    private static boolean aGagne(char[][] schema, char c) {
        // Vérification des lignes, colonnes et diagonales
        for (int i = 0; i < 3; i++) {
            if (schema[i][0] == c && schema[i][1] == c && schema[i][2] == c) return true;
            if (schema[0][i] == c && schema[1][i] == c && schema[2][i] == c) return true;
        }
        if (schema[0][0] == c && schema[1][1] == c && schema[2][2] == c) return true;
        if (schema[0][2] == c && schema[1][1] == c && schema[2][0] == c) return true;

        return false;
    }

    // Programme principal
    public static void main(String[] args) {
        char[][] schema = new char[3][3];  // Grille du jeu
        int mouvements = 0;  // Compteur de mouvements initialise
        boolean victoireX = false;  // Indicateurs de victoire 
        boolean victoireO = false;  // pour l'instant sur false
        char tour = 'X';  // Le joueur qui commence est 'X'

        // Initialisation de la grille
        initialiser(schema);

        // Boucle principale du jeu, jusqu'à 9 mouvements ou une victoire
        while (mouvements < 9 && victoireX == false && victoireO == false) {
            // Afficher la grille
            voir(schema);
            
            // Effectuer un nouveau déplacement
            nouveauDeplacement(schema, tour);
            mouvements++;

            // Vérifier si le joueur actuel a gagné
            if (aGagne(schema, tour)) {
            	voir(schema);
                if (tour == 'X') {
                    victoireX = true;
                    System.out.println("FÉLICITATIONS X, TU AS GAGNÉ !!!");
                } else {
                    victoireO = true;
                    System.out.println("FÉLICITATIONS O, TU AS GAGNÉ !!!");
                }
            }

            // Alterner les tours entre 'X' et 'O'
            if (tour == 'X') {
                tour = 'O';
            } else {
                tour = 'X';
            }
        }

        // Si aucun des deux joueurs n'a gagné après 9 tours, c'est une égalité
        if (victoireX == false && victoireO == false) {
        	voir(schema);
            System.out.println("Rien à faire... EGALITÉ !");
        }
    }
}





