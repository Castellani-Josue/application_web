package API;

import Bean.gestion_bd;
import com.sun.net.httpserver.HttpExchange;
import metier.article;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FctProtocole
{

    static void sendResponse(HttpExchange exchange, int statusCode, String
            response) throws IOException
    {
        System.out.println("Envoi de la réponse (" + statusCode + ") : --" + response
                + "--");
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    static String readRequestBody(HttpExchange exchange) throws IOException
    {
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(exchange.getRequestBody()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null)
        {
            requestBody.append(line);
        }
        reader.close();
        return requestBody.toString();
    }
    /*private static Map<String, String> parseQueryParams(String query)
    {
        Map<String, String> queryParams = new HashMap<>();
        if (query != null)
        {
            String[] params = query.split("&");
            for (String param : params)
            {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2)
                {
                    queryParams.put(keyValue[0], keyValue[1]);

                }
            }
        }
        return queryParams;
    }*/

    private static ArrayList<article> articlesList = new ArrayList<>();

    public static String convertArticleToJson() throws SQLException, ClassNotFoundException, IOException {

        System.out.println("Avant");
        articlesList = gestion_bd.getInstance().RecupArticle();
        System.out.println("Apres");

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
        System.out.println(json);
        return json.toString();

    }

    static boolean Update(String updateArticle) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("je suis dans la fonction Update");
        updateArticle = updateArticle.substring(1,updateArticle.length()-1);
        System.out.println("apres le udpateArticle.substring");
        String[] tab = updateArticle.split(",");

        ArrayList<String> list = new ArrayList<String>();
        System.out.println("avant le for");

        for (String s : tab)
        {
            System.out.println("je suis dans le for");
            String[] tab2 = s.split(":");

            String key = tab2[0].replaceAll("\"", "").trim();
            String value = tab2[1].replaceAll("\"", "").replaceAll("}", "").trim();

            list.add(value);
            System.out.println("apres le add");
            System.out.println("key : " + key + " value : " + value);
        }
        System.out.println("ares le for");
        boolean modif = gestion_bd.getInstance().UpdateArticle(list.get(0),list.get(1),list.get(2));
        System.out.println("modif : " + modif);
        return modif;
    }




}


