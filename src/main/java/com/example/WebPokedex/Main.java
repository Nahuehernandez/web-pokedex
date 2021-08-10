package com.example.WebPokedex;

import Db.ConnectionDb;
import Db.Db;
import pokedex.Ability;
import pokedex.PokemonEvolution;
import pokedex.PokemonSimple;
import pokedex.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args){
        ConnectionDb connectionDb = new ConnectionDb();
        connectionDb.connection();
        if(connectionDb.getConnection() == null) {
            System.out.println("Conexion fallida");
            cerrarConnection(connectionDb);
        }else{
            System.out.println("Conexion establecida");
            //mostrar(connectionDb);
            //pruebaNuevoPokemon(connectionDb);
            //obtenerPokemon(connectionDb);
            //listarPokemons(connectionDb);
            //agregarNuevoType(connectionDb);
            //nuevaEvolution(connectionDb);
            //eliminarType(connectionDb);
            cambiarnombre(connectionDb);
            System.out.println("Pokemon agregado");
        }
    }

    public static void cerrarConnection(ConnectionDb connectionDb){
        try {
            connectionDb.getConnection().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void mostrar(ConnectionDb connectionDb){
        String query = "select id_pokemon, name_pokemon, level_pokemon from pokedex.pokemon";
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                int id_pokemon = resultSet.getInt("id_pokemon");
                String name = resultSet.getString("name_pokemon");
                int level = resultSet.getInt("level_pokemon");
                System.out.println(id_pokemon + " " + name + " "+ level);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void mostrarNombre(ConnectionDb connectionDb){
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            String query = "select id_pokemon, name_pokemon, level_pokemon from pokedex.pokemon where name_pokemon = 'Nahuel'";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            int level = resultSet.getInt("level_pokemon");
            String name = resultSet.getString("name_pokemon");
            System.out.println("El nombre del pokemon es: " + name + ", y su nivel es: " + level);
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void pruebaNuevoPokemon(ConnectionDb connectionDb){
        Db db = new Db();
        Type type = new Type("Mercurio");
        Ability ability = new Ability("Planeta");
        PokemonEvolution pokemonEvolution = new PokemonEvolution("Estrella");
        PokemonSimple pokemon = new PokemonSimple(100, "Nahuel");
        pokemon.addTypes(type);
        pokemon.setAbilities(ability);
        pokemon.setEvolutions(pokemonEvolution);
        db.newPokemon(connectionDb, pokemon);
    }

    public static void obtenerPokemon(ConnectionDb connectionDb){
        Db db = new Db();
        PokemonSimple pokemon = db.generatePokemon(connectionDb, "Nahuel");

        System.out.println(pokemon.getName());
        System.out.println(pokemon.getLevel());
        for(int i = 0; i < pokemon.getTypes().size(); i++){
            System.out.println(pokemon.getTypes().get(i).getDescription());
        }
        for(int i = 0; i < pokemon.getAbilities().size(); i++){
            System.out.println(pokemon.getAbilities().get(i).getDescription());
        }
        for(int i = 0; i < pokemon.getEvolutions().size(); i++){
            System.out.println(pokemon.getEvolutions().get(i).getName());
        }
    }

    public static void listarPokemons(ConnectionDb connectionDb){
        Db db = new Db();
        List<PokemonSimple> pokemons = db.listAllPokemons(connectionDb);

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
    }

    public static void agregarNuevoType(ConnectionDb connectionDb){
        Db db = new Db();
        String name = "Nahuel";
        Type type = new Type("Alfa Centauri");
        db.addNewType(connectionDb, name, type);
    }

    public static void nuevaEvolution(ConnectionDb connectionDb){
        Db db = new Db();
        String name = "Nahuel";
        PokemonEvolution pokemonEvolution = new PokemonEvolution("Messi");
        db.newEvolution(connectionDb, name, pokemonEvolution);
    }

    public static void eliminarType(ConnectionDb connectionDb){
        Db db = new Db();
        String name = "Nahuel";
        Type type = new Type("Alfa Centauri");
        db.removeType(connectionDb, name, type);
    }

    public static void cambiarnombre(ConnectionDb connectionDb){
        Db db = new Db();
        String oldName = "Nahuel";
        String newName = "Darwin";
        db.modifyName(connectionDb, oldName, newName);
    }
}
