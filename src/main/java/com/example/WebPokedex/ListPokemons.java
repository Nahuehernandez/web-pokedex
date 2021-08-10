package com.example.WebPokedex;

import Db.*;
import pokedex.Pokemon;
import pokedex.PokemonSimple;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListPokemons", value = "/ListPokemons")
public class ListPokemons extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*PrintWriter out = response.getWriter();
        Db db = new Db();
        ConnectionDb connectionDb = new ConnectionDb();
        connectionDb.connection();
        if(connectionDb.getConnection() != null) {
            List<PokemonSimple> pokemons = db.listAllPokemons(connectionDb);

            out.println("<html><body>");
            out.println("Pokemons in the DataBase:");
            for(int i = 0; i < pokemons.size(); i++){
                System.out.println(pokemons.get(i).getName());
                System.out.println(pokemons.get(i).getLevel());
                for(int j = 0; j < pokemons.get(i).getTypes().size(); j++){
                    System.out.println(pokemons.get(i).getTypes().get(j).getDescription());
                }
                for(int j = 0; j < pokemons.get(i).getAbilities().size(); j++){
                    System.out.println(pokemons.get(i).getAbilities().get(j).getDescription());
                }
                for(int j = 0; j < pokemons.get(i).getEvolutions().size(); j++){
                    System.out.println(pokemons.get(i).getEvolutions().get(j).getName());
                }
            }

            for(int i = 0; i < allPokemons.size(); i++){
                out.println("POKEMON " + (i+1) + ":");
                out.println("Name: " + allPokemons.get(i).getName());
                out.println("Level: " + allPokemons.get(i).getLevel());
                out.println("Types: ");
                for(int j = 0; j < allPokemons.get(i).getTypes().size(); j++){
                    out.println("      " + allPokemons.get(i).getTypes().get(j));
                }
                out.println("");
            }
            out.println("</body></html>");
        }else{
            out.println("<html><body>");
            out.println("Connection failed");
            try {
                connectionDb.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
