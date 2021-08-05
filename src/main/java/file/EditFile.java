package file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EditFile {
    File file;// = new File("DataBase.txt");
    String path;
    public EditFile(String fileName){
        this.file = new File(fileName);
        this.path = fileName;
    }

    public void createFile() {
        if(!this.file.exists()){
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear archivo");
                //e.printStackTrace();
            }
        }
    }

    public void writeListOfStrings(List<String> abilities, PrintWriter pw){
        if(abilities.size() > 0) {
            pw.write(abilities.get(0));
            for (int i = 1; i < abilities.size(); i++) {
                pw.write(",");
                pw.write(abilities.get(i));
            }
        }
    }

    public void writePokemon(String name, String level, List<String> types, List<String> abilities, List<String> namesEvolutions)  {
        this.createFile();
        FileWriter fw;
        try {
            fw = new FileWriter(this.file, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.write(name);
            pw.write("#");
            pw.write(level);
            pw.write("#");
            this.writeListOfStrings(types, pw);
            pw.write("#");
            this.writeListOfStrings(abilities, pw);
            pw.write("#");
            this.writeListOfStrings(namesEvolutions, pw);
            pw.println("#");
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error a escribir");
            //e.printStackTrace();
        }
    }

    public void writeModifyPokemon(String oldName, String name, String level, List<String> types, List<String> abilities, List<String> namesEvolutions){
        this.createFile();
        File auxFile = new File("Auxiliar.txt");
        FileWriter fw;
        FileReader fr;
        BufferedReader reader;
        String line = "";
        try {
            fw = new FileWriter(auxFile, true);
            PrintWriter pw = new PrintWriter(fw);
            fr = new FileReader(this.file);
            reader = new BufferedReader(fr);

            while((line = reader.readLine()) != null){
                if(line.startsWith(oldName)){
                    pw.write(name);
                    pw.write("#");
                    pw.write(level);
                    pw.write("#");
                    this.writeListOfStrings(types, pw);
                    pw.write("#");
                    this.writeListOfStrings(abilities, pw);
                    pw.write("#");
                    this.writeListOfStrings(namesEvolutions, pw);
                    pw.println("#");
                }else{
                    pw.println(line);
                }
            }
            pw.close();
            fw.close();
            reader.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Error al sobrescribir");
            //e.printStackTrace();
        }

        this.file.delete();
        //auxFile.renameTo(new File("DataBase.txt"));
        auxFile.renameTo(new File(path));
    }

    public String readPokemon(String name) {
        this.createFile();
        FileReader fr;
        BufferedReader reader;
        String line = "";
        try {
            fr = new FileReader(this.file);
            reader = new BufferedReader(fr);

            while((line = reader.readLine()) != null){
                try {
                    if(line.startsWith(name)){
                        reader.close();
                        fr.close();
                        return line;
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer");
                    //e.printStackTrace();
                }
            }
            reader.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Otro error");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Otro error");
            //e.printStackTrace();
        }

        return "error";
    }

    public List<String> readDataBase(){
        this.createFile();
        FileReader fr;
        BufferedReader reader;
        String line = "";
        List<String> lines = new ArrayList<>();
        try {
            fr = new FileReader(this.file);
            reader = new BufferedReader(fr);
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
            reader.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Otro error");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Otro error");
            //e.printStackTrace();
        }

        return lines;
    }
}