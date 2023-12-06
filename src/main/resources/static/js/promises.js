//let's create a promise that divides two numbers
let promise = new Promise(function (resolve, reject) {
    //inside the executor stored in the promise
    let num = prompt("Enter a numerator");
    let den = prompt("Enter a denominator");

    //convert to numbers
    num = parseFloat(num);
    den = parseFloat(den);

    //check for bad input
    if (Number.isNaN(num) || Number.isNaN(den))
    {
        throw Error("User entered non-numerical values");
    }

    if (den === 0)
    {
        reject("Cannot divide by zero");
    }
    else
    {
        let result = num/den;

        //report sucess using the first parameter (which is a function)
        resolve("Result is " + result);
    }

});

promise
    .then(function(sucess) {
        console.log("The promise was successful!");
        console.log(sucess);
    }, function(failure) {
        console.log("The promise was unsuccessful!");
        console.log(failure);
    })//this is not necesarry with the catch
    .catch(function(error) {
        console.log("Something went wrong");
        console.log(error);
    });

//promises are objects that help you write and asynchronous code
//we can provide a .then or we can call it .that on the promise
    // and we can provide a callback for when the promise has done its work
//we can catch errors