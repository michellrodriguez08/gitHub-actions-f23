package edu.greenriver.sdev.jokesapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//this is lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Joke
{
  //  private static int classIds = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jokeText;

    public Joke(String jokeText)
    {
        //generateId();
        this.jokeText = jokeText;
    }

//    public void generateId()
//    {
//        id = classIds;
//        classIds++;
//    }
}
