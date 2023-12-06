//wait until the page loads
window.onload = async function(){
    await fetchJokes();

    //set an event handler for submitting a new joke
    let addJokeButtonv= document.querySelector("button");
    addJokeButtonv.onclick = addJoke;

    console.log("onload() ended")
};

async function addJoke(event)
{
    //stop the form from submitting, we will use fetch() instead!
    event.preventDefault();

    let jokeText = document.querySelector("input#joke-text").value;
    let newJoke = {
        jokeText: document.querySelector("input#joke-text").value
    };

    let uri = "http://localhost:8080/jokes";
    let config = {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        // body: newJoke
        body: JSON.stringify(newJoke)
    };

    let response = await fetch(uri, config);
    let json = await response.json();

    let section = document.querySelector("#jokes-list");
    addSingleJoke(json, section);

    console.log("Joke added", json);
}

async function fetchJokes()
{
    let uri = "http://localhost:8080/jokes";
    let config = {
        method: "get"
    };

    //use the await, async keywords to make your
    //code appear more synchronous
    //synchonous code goes in order, step1 step2, step3, etc.
    // asynchoronous goes side by side. can't tell which one is gonna finish first
    let response = await fetch(uri, config);

    //convert the body of the response to JSON format
    //using JSON.parse()
    //let json = response.json();
    let json = await response.json();
   // console.log(json);
    addCards(json);

    // fetch(uri, config)
    //     .then(function(response) {
    //         console.log(response)
    //         return response.json();
    //     })
    //     .then(function(json){
    //         console.log(json);
    //     });
    //it looks like this because we are dealing with asynchronis code
    //which means code that runs side by side with other pieces of our javaScript code

    // This is the good way!
    function addCards(jokesArray)
    {
        //loop over my array
        let section = document.querySelector("#jokes-list");
        for (let i= 0; i < jokesArray.length; i++)
        {
            let joke = jokesArray[i];

            addSingleJoke(joke, section);

            // //create HTML elements
            // let div = document.createElement("div");
            // let h1 = document.createElement("h1");
            // let p = document.createElement("p");
            //
            // //connect them (parent to child)
            // div.appendChild(h1);
            // div.appendChild(p);
            //
            // //add text or HTML attributes
            // h1.textContent = `Joke #${joke.id}`;
            // p.textContent = joke.jokeText;
            // div.className = "card";
            //
            // //add the div to the section
            // section.appendChild(div);


            // let html = `<div class="card">
            //                 <h1>Joke #<span id="joke-id">${joke.id}</span></h1>
            //                 <p id="joke-body">${joke.jokeText}</p>
            //                 </div>`;

           // section.innerHTML += html; //section.innerHTML = section.innerHTML + html
        }
    }

    // THIS WORKS
    // function addCards(jokesArray)
    // {
    //     //loop over my array
    //     let section = document.querySelector("#jokes-list");
    //     for (let i= 0; i < jokesArray.length; i++)
    //     {
    //         let joke = jokesArray[i];
    //         //add each joke
    //         //assemble the HTML for a joke using a string-template literal
    //         let html = `<div class="card">
    //                         <h1>Joke #<span id="joke-id">${joke.id}</span></h1>
    //                         <p id="joke-body">${joke.jokeText}</p>
    //                         </div>`;
    //
    //         section.innerHTML += html; //section.innerHTML = section.innerHTML + html
    //     }
    // }
}

function addSingleJoke(joke, section)
{
    //create HTML elements
    let div = document.createElement("div");
    let h1 = document.createElement("h1");
    let p = document.createElement("p");

    //connect them (parent to child)
    div.appendChild(h1);
    div.appendChild(p);

    //add text or HTML attributes
    h1.textContent = `Joke #${joke.id}`;
    p.textContent = joke.jokeText;
    div.className = "card";

    //add the div to the section
    section.appendChild(div);
}