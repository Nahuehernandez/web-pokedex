package com.example.WebPokedex;

import file.Adapter;
import pokedex.Pokemon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ShowAll", value = "/ShowAll")
public class ShowAll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        Adapter adapter = new Adapter("DataBase.txt");
        Pokemon pokemon = adapter.searchPokemon(request.getParameter("name"));

        out.println("<html><body>");
        if(pokemon != null) {
            out.println("Possibles evolutions for the pokemon " + request.getParameter("name") + ":");
            for (int i = 0; i < pokemon.getEvolutions().size(); i++) {
                out.println("Name: " + pokemon.getEvolutions().get(i).getName());
                out.println("<br><br>");
                out.println("Types:");
                for (int j = 0; j < pokemon.getEvolutions().get(i).getTypes().size(); j++) {
                    out.println("      " + pokemon.getEvolutions().get(i).getTypes().get(j));
                    out.println("<br><br>");
                }
                out.println("Level: " + pokemon.getEvolutions().get(i).getLevel());
                out.println("<br><br>");
            }
        }else{
            out.println("The pokemon " + request.getParameter("name") + " doesn't exists");
            out.println("<br><br>");
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
