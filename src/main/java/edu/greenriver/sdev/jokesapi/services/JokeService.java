package edu.greenriver.sdev.jokesapi.services;

import edu.greenriver.sdev.jokesapi.db.IJokesRepository;
import edu.greenriver.sdev.jokesapi.model.Joke;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class JokeService
{
    private IJokesRepository repo;

    public JokeService(IJokesRepository repo)
    {
        this.repo = repo;
    }
//    private List<Joke> jokes = new ArrayList<>(List.of(
//            new Joke("What did Han Solo say to the waiter who recommended the haddock? Never sell me the cods!"),
//            new Joke("Why didn’t any of Luke Skywalker’s marriages last? He always followed Obi-Wan’s advice: " +
//                    "'Use divorce, Luke.'"),
//            new Joke("What was Lando’s nickname before he became a skilled pilot? Crashdo."),
//            new Joke("Why does Princess Leia keep her hair tied up in buns? So it doesn’t Hang Solow."),
//            new Joke("What is Admiral Ackbar's favorite type of music? Trap."),
//            new Joke("What do you call a rebel princess who only shops at Whole Foods? Leia Organic."),
//            new Joke("What do you call an eel that loves the new Star Wars trilogy? A More-Rey Eel."),
//            new Joke("Where did Luke get his cybernetic hand? The second hand store.")
//    ));

    public List<Joke> getAllJokes()
    {
        //return jokes;
        return repo.findAll();
        //findAll would be querys for reading - returns the data from database
    }

    public Joke getJokeById(int id)
    {
        //the filter() method receives a lambda method
//       Optional<Joke> found = jokes.stream()
//                .filter(joke -> joke.getId() == id)
//                .findFirst();

        Optional<Joke> found = repo.findById(id);

        //joke -> joke.getId() == id this is a method
        //joke this is the peremeter of the method

//        return found.get();
        return found.orElse(null);
    }

    public boolean jokeExistsbyId(int id)
    {
        return getJokeById(id) != null;
    }


    public Joke random()
    {
        Random random = new Random();
        List<Joke> jokes = getAllJokes(); //new code from database
        return jokes.get(random.nextInt(jokes.size()));
    }

    public Joke addJoke(Joke joke)
    {
        //generate new id for the inserted record
       // joke.generateId();

        //insert the record
        joke = repo.save(joke);
       // jokes.add(joke);

        //TODO make sure our joke is returned with a new id
        //returning the joke with new id
        return joke;
    }

    public Joke updateJoke(Joke updatedJoke)
    {
        Joke savedJoke = getJokeById(updatedJoke.getId());
        savedJoke.setJokeText(updatedJoke.getJokeText());

        //save the changes to our joke to the db
        savedJoke = repo.save(savedJoke);

        return savedJoke;
    }

    public void deleteJoke(int id)
    {
        repo.deleteById(id);
//        for (int i = 0; i < jokes.size(); i++)
//        {
//            if (jokes.get(i).getId() == id)
//            {
//                jokes.remove(i);
//                break;
//            }
//        }
    }


}
