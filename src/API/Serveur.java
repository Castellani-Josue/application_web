package API;

import Bean.gestion_bd;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;


import static API.FctProtocole.*;


public class Serveur
{
    public static void main(String[] args)
    {
        //192.168.114.1
        HttpServer serveur;
        try
        {
            serveur = HttpServer.create(new InetSocketAddress(8081),0);
            serveur.createContext("/api/jo",new HandlerHtml());
            //serveur.createContext("/api/jo",new HandlerImg());
            System.out.println("Demarrage du serveur HTTP...");
            serveur.start();
        }
        catch (IOException e)
        {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    static class HandlerHtml implements HttpHandler
    {
        @Override
        public void handle(HttpExchange exchange) throws IOException
        {
            String requestMethod = exchange.getRequestMethod();
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin","*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods","GET, POST");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers","Content-Type");
            if (requestMethod.equalsIgnoreCase("GET"))
            {
                System.out.println("--- Requête GET reçue (obtenir la liste) ---");
                // Récupérer la liste des articles au format JSON
                String response = null;
                try {
                    response = convertArticleToJson();
                } catch (SQLException | ClassNotFoundException e)
                {
                    throw new RuntimeException(e);
                }
                sendResponse(exchange, 200, response);
            }
            else if (requestMethod.equalsIgnoreCase("POST"))
            {
                System.out.println("--- Requête POST reçue (update) ---");
                // Maj de la liste des articles
                String requestBody = readRequestBody(exchange);
                System.out.println("requestBody = " + requestBody);
                try {
                    Update(requestBody);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                sendResponse(exchange, 201, "Article mise à jour avec succes");
            }
            else sendResponse(exchange, 405, "Methode non autorisee");
        }

    }


    /*static class HandlerImg implements HttpHandler
    {
        @Override
        public void handle(HttpExchange exchange) throws IOException
        {
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("GET"))
            {
                System.out.println("--- Requête GET reçue (obtenir la liste) ---");
                // Récupérer la liste des articles au format JSON
                String response = null;
                try {
                    response = convertArticleToJson();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                sendResponse(exchange, 200, response);
            }
            else sendResponse(exchange, 405, "Methode non autorisee");
        }

    }*/











}
