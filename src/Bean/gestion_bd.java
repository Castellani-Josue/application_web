package Bean;

import metier.article;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class gestion_bd
{
    private final DatabaseConnection dbConnect;

    private static gestion_bd instance = null;
    private gestion_bd() throws ClassNotFoundException, SQLException , IOException
    {
        System.out.println("je suis dans le constructeur de gestion_bd");

        System.out.println("je suis dans le try de gestion_bd");
        dbConnect = new DatabaseConnection(DatabaseConnection.MYSQL,
                "192.168.137.159",
                "PourStudent",
                "Student",
                "PassStudent1_");
        System.out.println("db Connect réussie !");



    }

    public static gestion_bd getInstance() throws SQLException, ClassNotFoundException, IOException {
        if(instance == null)
        {
            instance = new gestion_bd();
            System.out.println("je suis dans le constructeur du singleton");
        }
        return instance;
    }

    public ArrayList<article> RecupArticle() throws SQLException {

        System.out.println("je suis dans la fonction RecupArticle");
        String requete = "SELECT id,intitule,prix,stock,image FROM articles;";
        ResultSet trouve = null;


        String id = "";
        String intitule = "";
        String prix = "";
        String stock = "";
        String image = "";


        ArrayList<article> articlesList = new ArrayList<>();


        try {
            trouve = dbConnect.executeQuery(requete);
            System.out.println("je suis dans le try de RecupArticle après l'executeQuery");
            while(trouve.next())
            {
                System.out.println("je suis dans le while de RecupArticle");
                id = trouve.getString("id");
                intitule = trouve.getString("intitule");
                prix = trouve.getString("prix");
                stock = trouve.getString("stock");
                image = trouve.getString("image");
                System.out.println("id : " + id + " intitule : " + intitule + " prix : " + prix + " stock : " + stock + " image : " + image);
                article article = new article(Integer.parseInt(id),intitule,Double.parseDouble(prix),Integer.parseInt(stock),image);
                articlesList.add(article);
                System.out.println("je suis dans le while de RecupArticle après l'ajout de l'article");


            }
            System.out.println("je suis dans le try de RecupArticle après le while");
            return articlesList;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        /*finally
        {
            try {
                if (trouve != null)
                {
                    trouve.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }*/

    }


    public boolean UpdateArticle(String id ,String prix, String quantite)
    {


        String requete = "UPDATE article SET  prix = " + prix + ", quantite = " + quantite + " WHERE id = " + id + ";";


        try
        {
            int modification = dbConnect.executeUpdate(requete);
            if(modification == 0)
            {
                System.out.println("Aucune ligne n'a été modifier, article introuvable.");
                return false;
            }
            else
            {
                System.out.println("Mise à jour effectué avec succès.");
                return true;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
