package edu.greenriver.sdev.jokesapi.controllers;

import edu.greenriver.sdev.jokesapi.model.Joke;
import edu.greenriver.sdev.jokesapi.services.JokeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
//Api is the controller
public class JokesApi
{
    private JokeService service;

    public JokesApi(JokeService service)
    {
        this.service = service;
    }

    @GetMapping("jokes") //http://localhost:8080/jokes
    public ResponseEntity<List<Joke>> allJokes()
    {
        return new ResponseEntity<>(service.getAllJokes(), HttpStatus.OK);
    }
//    public List<Joke> allJokes()
//    {
//       return service.getAllJokes();
//    }

    @GetMapping("jokes/random")
    public ResponseEntity<Joke> random()
    {
        return new ResponseEntity<>(service.random(), HttpStatus.OK);
    }
//    public Joke random()
//    {
//        return service.random();
//    }

    @GetMapping("jokes/{jokeId}") //http://localhost:8080/jokes/3
    public ResponseEntity<Joke> getjokeById(@PathVariable int jokeId)
    {
        return new ResponseEntity<>(service.getJokeById(jokeId), HttpStatus.OK); //200
    }
//    public Joke getjokeById(@PathVariable int jokeId)
//    {
//        return service.getJokeById(jokeId);
//    }

    //pass in a new Joke object through the request body
    @PostMapping("jokes")
    public ResponseEntity<Joke> addJoke(@RequestBody Joke joke)
    {
        return new ResponseEntity<>(service.addJoke(joke), HttpStatus.CREATED); //201
    }

//    public Joke addJoke(@RequestBody Joke joke)
//    {
//        return service.addJoke(joke);
//    }

    //http://localhost:8080/jokes?author=josh&text=hello,world!
    @PostMapping("jokes/query")
    public ResponseEntity<Joke> addJoke(@RequestParam String author, @RequestParam String text)
    {
        Joke newJoke = new Joke(author + ": " + text);
        return new ResponseEntity<>(service.addJoke(newJoke), HttpStatus.CREATED); //201
    }

    @PutMapping("jokes")
    public ResponseEntity<Joke> editJoke(@RequestBody Joke joke)
    {
        if (!service.jokeExistsbyId(joke.getId()))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.updateJoke(joke), HttpStatus.OK); //200
    }

    @DeleteMapping("jokes")
    public ResponseEntity<Joke> deleteJoke(@RequestBody Joke joke)
    {
        if (!service.jokeExistsbyId(joke.getId()))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.deleteJoke(joke.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    public void deleteJoke(@RequestBody Joke joke)
//    {
//        service.deleteJoke(joke.getId());
//    }
}
