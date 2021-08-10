package com.example.WebPokedex;

import pokedex.Pokemon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NewPokemon", value = "/NewPokemon")
public class NewPokemon extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*PrintWriter out = response.getWriter();

        Adapter adapter = new Adapter("DataBase.txt");
        Pokemon pokemonExists = adapter.searchPokemon(request.getParameter("name"));
        out.println("<html><body>");
        if(pokemonExists != null){
            out.println("The pokemon " + request.getParameter("name") + " is already exists");
        }else {
            Pokemon pokemon = new Pokemon(request.getParameter("name"), Integer.parseInt(request.getParameter("level")));
            pokemon.setTypes(request.getParameter("type"));
            pokemon.setAbility(request.getParameter("abilty"));
            adapter.savePokemon(pokemon);
            out.println("Pokemon added");
        }
        out.println("</body></html>");*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
