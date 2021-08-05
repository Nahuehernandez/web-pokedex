package com.example.WebPokedex;

import file.Adapter;
import pokedex.Pokemon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListPokemons", value = "/ListPokemons")
public class ListPokemons extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        Adapter adapter = new Adapter("DataBase.txt");

        List<Pokemon> allPokemons = adapter.allPokemons();

        out.println("<html><body>");
        out.println("Pokemons in the DataBase:");
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
