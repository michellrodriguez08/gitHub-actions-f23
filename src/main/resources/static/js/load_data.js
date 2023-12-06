//wait until the page loads
window.onload = function () {
  //create objects to make a GET request
  let uri = "http://localhost:8080/jokes";
  let params = {
    method: "get"
  };

  fetch(uri, params)
      .then(function(response){
        // console.log(response);
        // console.log(response.json)
        return response.json();
      })
      .then(function(data){
        showJokes(data);
      });
      // .then(function(json){
      //   console.log(json);
      // });

};

function showJokes(data) {
  //access the list in our HTML
  let jokesList = document.getElementById("jokes-list");

  //create a bunch of new sections
  /*
     <section>
         <h2>Joke # ...</h2>
         <p>Han and leis...</p>
     </section>
    */
  for (let i = 0; i < data.length; i++)
  {
    let joke = data[i];

  //create a new list for each joke
  //   let li = document.createElement("li");

   //create a new section
    let section = document.createElement("section");
    let h2 = document.createElement("h2");
    let paragraph = document.createElement("p");

    //add contents
    h2.innerText = "Joke #" + joke.id;
    paragraph.innerText = joke.jokeText;
    // li.innerText = joke.jokeText;

    //connect them
    section.appendChild(h2);
    section.appendChild(document.createElement("hr"));
    section.appendChild(paragraph);

  //add the list item to the list
    jokesList.appendChild(section);
  //   jokesList.appendChild(li);
  }
}