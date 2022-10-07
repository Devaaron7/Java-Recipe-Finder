var counter = 0
var restore = document.getElementById('img_container')
var saved = restore.innerHTML
var img1 = document.getElementById('image1')
var link1 = document.getElementById('link1')
var img2 = document.getElementById('image2')
var img3 = document.getElementById('image3')
var titleBtn = document.getElementById('ModeSelectTitle')
var searchBtn = document.getElementById('btn')
var searchTerm = document.getElementById('form')
var titleInput = document.getElementById('buttons')
var addIngredient = document.getElementById('add')
var clearIngredient = document.getElementById('clear')
var showTags = document.getElementById('ingredients')
var tagOne = document.getElementById('tagOne')
var tagTwo = document.getElementById('tagTwo')
var tagThree = document.getElementById('tagThree')
var foodTag = document.getElementById('foodTag')
var dataForSearchResults = []

var dataForSearchResults = [{sourceUrl: 'http://www.afrolems.com/2014/03/08/plantain-pizza/', image: 'https://spoonacular.com/recipeImages/716300-556x370.jpg', id: 716300, title: 'Plantain Pizza'},
{sourceUrl: 'https://www.foodista.com/recipe/SHKG55X4/pizza-bites-with-pumpkin', image: 'https://spoonacular.com/recipeImages/656329-556x370.jpg', id: 656329, title: 'Pizza bites with pumpkin'}
,
{sourceUrl: 'http://www.pinkwhen.com/blt-pizza/', image: 'https://spoonacular.com/recipeImages/680975-556x370.jpg', id: 680975, title
: 
"BLT Pizza"}]
var tagCounter = 0






function search() {

    api()
    sendFormData()
    if (counter % 2 == 0) {
        img1.src = "./image_4.jpg";
        img2.src = "./image_5.jpg";
        img3.src = "./image_6.jpg";
        var restore = document.getElementById('img_container')
        var saved = restore.innerHTML
    } else {
        img1.src = "./image_1.jpg";
        img2.src = "./image_2.jpg";
        img3.src = "./image_3.jpg";
        var restore = document.getElementById('img_container')
        var saved = restore.innerHTML
    }

    if (counter > 0) {
        
        restore.innerHTML = "";
        restore.innerHTML = saved;
        
    }

    document.getElementById('img_container').style.display='flex';
    counter++
}



function api() {

    /// Kyle Continue work here. We need the website to change into "nightmode" is the counter displays 0
    var apiCounter = document.getElementById('api').innerText = Math.floor(Math.random() * 5);
    var errorText = document.getElementById('error').innerText
    if (apiCounter == 0) { 
        errorText = "OUT OF CREDIT"
    }
}


function sendFormData() {

    var input = {"data":document.getElementById('form').value};

    // var input = {"num1":10, "num2": 40};

    console.log(JSON.stringify(input));
    fetch("http://localhost:8080/test", {
    method: "POST",
    headers: {mode: 'cors', 'Content-Type': 'application/json'}, 
    body: JSON.stringify(input)
    }).then(res => res.json()
    .then(data =>{
    
    console.log("Request complete! response:", data);
    //var resultText = JSON.parse()
    //foodTag.innerText = JSON.stringify(data[2]["sourceUrl"]);

    //for (i = 0; i < data.length; i++ ){
    //    dataForSearchResults.push(JSON.stringify(data[i]));
    //}

    console.log(dataForSearchResults);


    setDataforResults()

    }));
}

function setDataforResults() {

    console.log(dataForSearchResults[0]["image"])

    console.log(dataForSearchResults[0]["sourceUrl"])

    console.log(dataForSearchResults)


    img1.src = dataForSearchResults[0]["image"];
    link1.setAttribute("href", dataForSearchResults[0]["sourceUrl"])
}




function showTitle() {

    titleInput.style.display = "flex";
    addIngredient.style.display = "none";
    clearIngredient.style.display = "none";
    showTags.style.display = "none";
    document.getElementById('btn').innerHTML = "Title Search";
        
}

function showIngredient() {

    titleInput.style.display = "flex";
    addIngredient.style.display = "flex";
    clearIngredient.style.display = "flex";
    showTags.style.display = "flex";
    document.getElementById('btn').innerHTML = "Ingredient Search";
    
}

function clearTags() {

    
    tagCounter = 0;
    tagOne.innerText = "";
    tagTwo.innerText = "";
    tagThree.innerText = "";
    
    
}

function showIconTitle() {
    if (searchTerm.value == "") {
        searchBtn.style.display = "none";
    } else {
        searchBtn.style.display = "flex";
    }
}

function addTag() {
    
    if (tagCounter == 0) {
        tagOne.innerText = searchTerm.value;
        tagCounter += 1
    } else if (tagCounter == 1) {
        tagTwo.innerText = searchTerm.value;
        tagCounter += 1 
    } else if (tagCounter == 2) {
        tagThree.innerText = searchTerm.value;
        tagCounter += 1 
    }


}