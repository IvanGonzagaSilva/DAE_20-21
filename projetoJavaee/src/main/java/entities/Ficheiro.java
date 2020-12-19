package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ficheiro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String filepath;
    private String filename;


    public Ficheiro() {
    }

    public Ficheiro(String filepath, String filename) {
        this.filepath = filepath;
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
