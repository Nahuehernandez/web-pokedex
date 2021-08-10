package Db;

import pokedex.Ability;
import pokedex.PokemonEvolution;
import pokedex.PokemonSimple;
import pokedex.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Db {
    //NEW POKEMON
    public void newPokemon(ConnectionDb connectionDb, PokemonSimple pokemon){
        String query = "insert into pokedex.pokemon(name_pokemon, level_pokemon) values (?, ?)";
        try {
            PreparedStatement statement = connectionDb.getConnection().prepareStatement(query);
            statement.setString(1, pokemon.getName());
            statement.setInt(2, pokemon.getLevel());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        insertIfItHasTypes(connectionDb, pokemon);
        insertIfItHasAbilities(connectionDb, pokemon);
        insertIfItHasEvolutions(connectionDb, pokemon);
    }

    public void insertIfItHasTypes(ConnectionDb connectionDb, PokemonSimple pokemon){
        if(pokemon.getTypes().size() > 0){
            for(int i = 0; i < pokemon.getTypes().size(); i++){
                insertType(connectionDb, pokemon.getTypes().get(i));
            }
            String query = "select id_pokemon from pokedex.pokemon where name_pokemon = '" + pokemon.getName() + "' and id_type is null";
            ResultSet resultSet = null;
            List<Integer> ids_pokemon = new ArrayList<>();
            List<Integer> ids_types = new ArrayList<>();
            int cont = 0;
            try {
                Statement statement = connectionDb.getConnection().createStatement();
                resultSet = statement.executeQuery(query);
                for(int i = 0; (i < pokemon.getTypes().size()) && (resultSet.next()); i++){
                    ids_pokemon.add(resultSet.getInt("id_pokemon"));
                    cont++;
                }
                for(int i = 0; (i < pokemon.getTypes().size()); i++) {
                    query = "select id_type from pokedex.type_pokemon where description_type = '" + pokemon.getTypes().get(i).getDescription() + "'";
                    resultSet = statement.executeQuery(query);
                    resultSet.next();
                    ids_types.add(resultSet.getInt("id_type"));
                }
                resultSet.close();
                statement.close();
                if(cont == pokemon.getTypes().size()){
                    PreparedStatement newResult = null;
                    String sql;
                    for(int i = 0; i < pokemon.getTypes().size(); i++){
                        sql = "update pokedex.pokemon set id_type = ? where id_pokemon = ?";
                        newResult = connectionDb.getConnection().prepareStatement(sql);
                        newResult.setInt(1, ids_types.get(i));
                        newResult.setInt(2, ids_pokemon.get(i));
                        newResult.executeUpdate();
                    }
                    newResult.close();
                }else{
                    PreparedStatement update = null;
                    String sql;
                    for(int i = 0; i < ids_pokemon.size(); i++){
                        sql = "update pokedex.pokemon set id_type = ? where id_pokemon = ?";
                        update = connectionDb.getConnection().prepareStatement(sql);
                        update.setInt(1, ids_types.get(i));
                        update.setInt(2, ids_pokemon.get(i));
                        update.executeUpdate();
                    }
                    update.close();
                    PreparedStatement insert = null;
                    for(int i = cont; i < ids_types.size(); i++){
                        sql = "insert into pokedex.pokemon(name_pokemon, level_pokemon, id_type) values (?, ?, ?)";
                        insert = connectionDb.getConnection().prepareStatement(sql);
                        insert.setString(1, pokemon.getName());
                        insert.setInt(2, pokemon.getLevel());
                        insert.setInt(3, ids_types.get(i));
                        insert.executeUpdate();
                    }
                    insert.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void insertIfItHasAbilities(ConnectionDb connectionDb, PokemonSimple pokemon){
        if(pokemon.getAbilities().size() > 0){
            for(int i = 0; i < pokemon.getAbilities().size(); i++){
                insertAbiility(connectionDb, pokemon.getAbilities().get(i));
            }
            String query = "select id_pokemon from pokedex.pokemon where name_pokemon = '" + pokemon.getName() + "' and id_ability is null";
            ResultSet resultSet = null;
            List<Integer> ids_pokemon = new ArrayList<>();
            List<Integer> ids_abilites = new ArrayList<>();
            int cont = 0;
            try {
                Statement statement = connectionDb.getConnection().createStatement();
                resultSet = statement.executeQuery(query);
                for(int i = 0; (i < pokemon.getAbilities().size()) && (resultSet.next()); i++){
                    ids_pokemon.add(resultSet.getInt("id_pokemon"));
                    cont++;
                }
                for(int i = 0; (i < pokemon.getAbilities().size()); i++) {
                    query = "select id_ability from pokedex.ability where description_ability = '" + pokemon.getAbilities().get(i).getDescription() + "'";
                    resultSet = statement.executeQuery(query);
                    resultSet.next();
                    ids_abilites.add(resultSet.getInt("id_ability"));
                }
                resultSet.close();
                statement.close();
                if(cont == pokemon.getAbilities().size()){
                    PreparedStatement newResult = null;
                    String sql;
                    for(int i = 0; i < pokemon.getAbilities().size(); i++){
                        sql = "update pokedex.pokemon set id_ability = ? where id_pokemon = ?";
                        newResult = connectionDb.getConnection().prepareStatement(sql);
                        newResult.setInt(1, ids_abilites.get(i));
                        newResult.setInt(2, ids_pokemon.get(i));
                        newResult.executeUpdate();
                    }
                    newResult.close();
                }else{
                    PreparedStatement update = null;
                    String sql;
                    for(int i = 0; i < ids_pokemon.size(); i++){
                        sql = "update pokedex.pokemon set id_ability = ? where id_pokemon = ?";
                        update = connectionDb.getConnection().prepareStatement(sql);
                        update.setInt(1, ids_abilites.get(i));
                        update.setInt(2, ids_pokemon.get(i));
                        update.executeUpdate();
                    }
                    update.close();
                    PreparedStatement insert = null;
                    for(int i = cont; i < ids_abilites.size(); i++){
                        sql = "insert into pokedex.pokemon(name_pokemon, level_pokemon, id_ability) values (?, ?, ?)";
                        insert = connectionDb.getConnection().prepareStatement(sql);
                        insert.setString(1, pokemon.getName());
                        insert.setInt(2, pokemon.getLevel());
                        insert.setInt(3, ids_abilites.get(i));
                        insert.executeUpdate();
                    }
                    insert.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void insertIfItHasEvolutions(ConnectionDb connectionDb, PokemonSimple pokemon){
        if(pokemon.getEvolutions().size() > 0){
            for(int i = 0; i < pokemon.getEvolutions().size(); i++){
                insertEvolution(connectionDb, pokemon.getEvolutions().get(i));
            }
            String query = "select id_pokemon from pokedex.pokemon where name_pokemon = '" + pokemon.getName() + "' and id_evolution is null";
            ResultSet resultSet = null;
            List<Integer> ids_pokemon = new ArrayList<>();
            List<Integer> ids_evoloutions = new ArrayList<>();
            int cont = 0;
            try {
                Statement statement = connectionDb.getConnection().createStatement();
                resultSet = statement.executeQuery(query);
                for(int i = 0; (i < pokemon.getEvolutions().size()) && (resultSet.next()); i++){
                    ids_pokemon.add(resultSet.getInt("id_pokemon"));
                    cont++;
                }
                for(int i = 0; (i < pokemon.getEvolutions().size()); i++) {
                    query = "select id_evolution from pokedex.evolution where name_evolution = '" + pokemon.getEvolutions().get(i).getName() + "'";
                    resultSet = statement.executeQuery(query);
                    resultSet.next();
                    ids_evoloutions.add(resultSet.getInt("id_evolution"));
                }
                resultSet.close();
                statement.close();
                if(cont == pokemon.getEvolutions().size()){
                    PreparedStatement newResult = null;
                    String sql;
                    for(int i = 0; i < pokemon.getEvolutions().size(); i++){
                        sql = "update pokedex.pokemon set id_evolution = ? where id_pokemon = ?";
                        newResult = connectionDb.getConnection().prepareStatement(sql);
                        newResult.setInt(1, ids_evoloutions.get(i));
                        newResult.setInt(2, ids_pokemon.get(i));
                        newResult.executeUpdate();
                    }
                    newResult.close();
                }else{
                    PreparedStatement update = null;
                    String sql;
                    for(int i = 0; i < ids_pokemon.size(); i++){
                        sql = "update pokedex.pokemon set id_evolution = ? where id_pokemon = ?";
                        update = connectionDb.getConnection().prepareStatement(sql);
                        update.setInt(1, ids_evoloutions.get(i));
                        update.setInt(2, ids_pokemon.get(i));
                        update.executeUpdate();
                    }
                    update.close();
                    PreparedStatement insert = null;
                    for(int i = cont; i < ids_evoloutions.size(); i++){
                        sql = "insert into pokedex.pokemon(name_pokemon, level_pokemon, id_evolution) values (?, ?, ?)";
                        insert = connectionDb.getConnection().prepareStatement(sql);
                        insert.setString(1, pokemon.getName());
                        insert.setInt(2, pokemon.getLevel());
                        insert.setInt(3, ids_evoloutions.get(i));
                        insert.executeUpdate();
                    }
                    insert.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void insertType(ConnectionDb connectionDb, Type type){
        String query = "select count(*) as total from pokedex.type_pokemon where description_type = '" + type.getDescription() +"'";
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            if(resultSet.getInt("total") == 0){
                String sql = "insert into pokedex.type_pokemon(description_type) values (?)";
                PreparedStatement insert = connectionDb.getConnection().prepareStatement(sql);
                insert.setString(1, type.getDescription());
                insert.executeUpdate();
                insert.close();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertAbiility(ConnectionDb connectionDb, Ability ability){
        String query = "select count(*) as total from pokedex.ability where description_ability = '" + ability.getDescription() +"'";
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            if(resultSet.getInt("total") == 0){
                String sql = "insert into pokedex.ability(description_ability) values (?)";
                PreparedStatement insert = connectionDb.getConnection().prepareStatement(sql);
                insert.setString(1, ability.getDescription());
                insert.executeUpdate();
                insert.close();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertEvolution(ConnectionDb connectionDb, PokemonEvolution evolution){
        String query = "select count(*) as total from pokedex.evolution where  name_evolution = '" + evolution.getName() +"'";
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            if(resultSet.getInt("total") == 0){
                String sql = "insert into pokedex.evolution(name_evolution) values (?)";
                PreparedStatement insert = connectionDb.getConnection().prepareStatement(sql);
                insert.setString(1, evolution.getName());
                insert.executeUpdate();
                insert.close();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //EDIT POKEMON ---> Doy porcentado que el pokemon existe, chequear eso
    public void modifyName(ConnectionDb connectionDb, String oldName, String newName){
        ResultSet resultSet = null;
        PreparedStatement update = null;
        String query = "select id_pokemon from pokedex.pokemon where name_pokemon = '" + oldName +"'";
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                query = "update pokedex.pokemon set name_pokemon = ? where id_pokemon = ?";
                update = connectionDb.getConnection().prepareStatement(query);
                update.setString(1, newName);
                update.setInt(2, resultSet.getInt("id_pokemon"));
                update.executeUpdate();
            }
            resultSet.close();
            update.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addNewType(ConnectionDb connectionDb, String name, Type type){
        insertType(connectionDb, type);

        String searchId = "select id_type from pokedex.type_pokemon where description_type = '" + type.getDescription() + "'";
        String query = "select count(*) as cantidad from pokedex.pokemon where name_pokemon = '" + name + "' and id_type is null";
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(searchId);
            resultSet.next();
            int id_type = resultSet.getInt("id_type");
            resultSet.close();
            ResultSet cantidad = statement.executeQuery(query);
            cantidad.next();
            int cant = cantidad.getInt("cantidad");
            cantidad.close();
            if(cant > 0){
                query = "select id_pokemon from pokedex.pokemon where name_pokemon = '" + name + "' and id_type is null limit 1";
                ResultSet query_id_pokemon = statement.executeQuery(query);
                query_id_pokemon.next();
                int id_pokemon = query_id_pokemon.getInt("id_pokemon");
                query_id_pokemon.close();
                statement.close();
                query = "update pokedex.pokemon set id_type = ? where id_pokemon = ?";
                PreparedStatement update = connectionDb.getConnection().prepareStatement(query);
                update.setInt(1, id_type);
                update.setInt(2, id_pokemon);
                update.executeUpdate();
                update.close();
            }else{
                statement.close();
                query = "insert into pokedex.pokemon(name_pokemon, id_type) values (?, ?)";
                PreparedStatement insert = connectionDb.getConnection().prepareStatement(query);
                insert.setString(1, name);
                insert.setInt(2, id_type);
                insert.executeUpdate();
                insert.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeType(ConnectionDb connectionDb, String name, Type type){
        ResultSet resultSet;
        String searchId = "select id_type from pokedex.type_pokemon where description_type = '" + type.getDescription() + "'";
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            resultSet = statement.executeQuery(searchId);
            resultSet.next();
            int id_type = resultSet.getInt("id_type");
            String query = "select id_pokemon, id_ability, id_evolution from pokedex.pokemon where name_pokemon = '" + name + "' and id_type = " + id_type;
            resultSet = statement.executeQuery(query);
            resultSet.next();
            if(resultSet.getString("id_ability") == null && resultSet.getString("id_evolution") == null){
                query = "delete from pokedex.pokemon where id_pokemon = ?";
                PreparedStatement delete = connectionDb.getConnection().prepareStatement(query);
                delete.setInt(1, resultSet.getInt("id_pokemon"));
                delete.executeUpdate();
                delete.close();
            }else{
                query = "update pokedex.pokemon set id_type = NULL where id_pokemon = ?";
                PreparedStatement update = connectionDb.getConnection().prepareStatement(query);
                update.setInt(1, resultSet.getInt("id_pokemon"));
                update.executeUpdate();
                update.close();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void newEvolution(ConnectionDb connectionDb, String name, PokemonEvolution evolution){
        insertEvolution(connectionDb, evolution);

        String searchId = "select id_evolution from pokedex.evolution where name_evolution = '" + evolution.getName() + "'";
        String query = "select count(*) as cantidad from pokedex.pokemon where name_pokemon = '" + name + "' and id_evolution is null";
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(searchId);
            resultSet.next();
            int id_evolution = resultSet.getInt("id_evolution");
            resultSet.close();
            ResultSet cantidad = statement.executeQuery(query);
            cantidad.next();
            int cant = cantidad.getInt("cantidad");
            cantidad.close();
            if(cant > 0){
                query = "select id_pokemon from pokedex.pokemon where name_pokemon = '" + name + "' and id_evolution is null limit 1";
                ResultSet query_id_pokemon = statement.executeQuery(query);
                query_id_pokemon.next();
                int id_pokemon = query_id_pokemon.getInt("id_pokemon");
                query_id_pokemon.close();
                statement.close();
                query = "update pokedex.pokemon set id_evolution = ?  where id_pokemon = ?";
                PreparedStatement update = connectionDb.getConnection().prepareStatement(query);
                update.setInt(1, id_evolution);
                update.setInt(2, id_pokemon);
                update.executeUpdate();
                update.close();
            }else{
                query = "insert into pokedex.pokemon(name_pokemon, id_evolution) values (?, ?)";
                PreparedStatement insert = connectionDb.getConnection().prepareStatement(query);
                insert.setString(1, name);
                insert.setInt(2, id_evolution);
                insert.executeUpdate();
                insert.close();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //LIST ALL POKEMONS
    public List<PokemonSimple> listAllPokemons(ConnectionDb connectionDb){
        String query = "select name_pokemon from pokedex.pokemon group by name_pokemon";
        List<PokemonSimple> pokemons = new ArrayList<>();
        ResultSet resultSet;
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                pokemons.add(generatePokemon(connectionDb, resultSet.getString("name_pokemon")));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pokemons;
    }

    public PokemonSimple generatePokemon(ConnectionDb connectionDb, String namePokemon){
        ResultSet resultSet;
        PokemonSimple pokemon = new PokemonSimple(0, namePokemon);
        String query = "select level_pokemon from pokedex.pokemon where name_pokemon = '" + namePokemon + "' limit 1";
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            pokemon.setLevel(resultSet.getInt("level_pokemon"));
            resultSet.close();
            statement.close();
            getTypes(connectionDb, pokemon);
            getAbilities(connectionDb, pokemon);
            getEvolutions(connectionDb, pokemon);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pokemon;
    }

    public void getTypes(ConnectionDb connectionDb, PokemonSimple pokemon){
        String query = "select P.id_type, T.description_type from pokedex.pokemon P join pokedex.type_pokemon T on P.id_type = T.id_type where name_pokemon = '" + pokemon.getName() + "' and P.id_type is not null";
        List<String> description_types = new ArrayList<>();
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                description_types.add(resultSet.getString("description_type"));
            }
            resultSet.close();
            statement.close();
            for(int i = 0; i < description_types.size(); i++){
                Type type = new Type(description_types.get(i));
                pokemon.addTypes(type);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAbilities(ConnectionDb connectionDb, PokemonSimple pokemon){
        String query = "select P.id_ability, A.description_ability from pokedex.pokemon P join pokedex.ability A on P.id_ability = A.id_ability where name_pokemon = '" + pokemon.getName() + "' and P.id_ability is not null";
        List<String> description_abilities = new ArrayList<>();
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                description_abilities.add(resultSet.getString("description_ability"));
            }
            resultSet.close();
            statement.close();
            for(int i = 0; i < description_abilities.size(); i++){
                Ability ability = new Ability(description_abilities.get(i));
                pokemon.setAbilities(ability);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getEvolutions(ConnectionDb connectionDb, PokemonSimple pokemon){
        String query = "select P.id_evolution, E.name_evolution from pokedex.pokemon P join pokedex.evolution E on P.id_evolution = E.id_evolution where name_pokemon = '" + pokemon.getName() + "' and P.id_evolution is not null";
        List<String> names_evolutions = new ArrayList<>();
        try {
            Statement statement = connectionDb.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                names_evolutions.add(resultSet.getString("name_evolution"));
            }
            resultSet.close();
            statement.close();
            for(int i = 0; i < names_evolutions.size(); i++){
                PokemonEvolution pokemonEvolution = new PokemonEvolution(names_evolutions.get(i));
                pokemon.setEvolutions(pokemonEvolution);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
