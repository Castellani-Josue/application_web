package metier;

import java.util.ArrayList;
import java.util.List;

public class article
{
    public final int id;
    public final String intitule;

    public final double prix;

    public final int  quantite;

    public final String image;

    private static List<article> articlesList = new ArrayList<>();

    public article(int id, String intitule, double prix, int quantite, String image)
    {
        this.id = id;
        this.intitule = intitule;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
    }

    /*public static String convertArticleToJson()
    {

        // Convertir la liste des articles en format JSON
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < articlesList.size(); i++)
        {
            article currentArticle = articlesList.get(i);
            json.append("{")
                    .append("\"id\": ").append(currentArticle.id).append(", ")
                    .append("\"intitule\": \"").append(currentArticle.intitule).append("\", ")
                    .append("\"prix\": ").append(currentArticle.prix).append(", ")
                    .append("\"quantite\": ").append(currentArticle.quantite).append(", ")
                    .append("\"image\": \"").append(currentArticle.image).append("\"")
                    .append("}");

            if (i < articlesList.size() - 1) json.append(",");
        }
        json.append("]");
        return json.toString();
    }*/






}
