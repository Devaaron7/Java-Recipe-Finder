var counter = 0
var restore = document.getElementById('img_container')
var loader = document.getElementById('loader')
var saved = restore.innerHTML
var img1 = document.getElementById('image1')
var link1 = document.getElementById('link1')

var img2 = document.getElementById('image2')
var link2 = document.getElementById('link2')

var img3 = document.getElementById('image3')
var link3 = document.getElementById('link3')

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
var tagOneShape = document.getElementById('tagOneShape')
var tagTwoShape = document.getElementById('tagTwoShape')
var tagThreeShape = document.getElementById('tagThreeShape')
var foodTag = document.getElementById('foodTag')
var credit = document.getElementById('api')
var dataForSearchResults = []
var tagCounter = 0
var tagDict = {"tag1":"", "tag2":"", "tag3":""}
var modeTitle = document.getElementById('ModeSelectTitle')
var modeIngredient = document.getElementById('ModeSelectIngredient')
var searchError = document.getElementById('noSearchResults')
var recipeOneText = document.getElementById('recipeOne')
var recipeTwoText = document.getElementById('recipeTwo')
var recipeThreeText = document.getElementById('recipeThree')


//Testing Button Functions


function testDict() {
    console.log(tagDict);
}

// function api() {

//     /// Kyle Continue work here. We need the website to change into "nightmode" is the counter displays 0
//     var apiCounter = document.getElementById('api').innerText = Math.floor(Math.random() * 5);
//     var errorText = document.getElementById('error').innerText
//     if (apiCounter == 0) { 
//         errorText = "OUT OF CREDIT"
//     }
// }


// Connecting to Backend functions

function sendFormDataTitle() {

    var stringToFormat = document.getElementById('form').value

    var formattedString = stringToFormat.replaceAll(" ", "&")

    var input = {"data":formattedString};

    console.log(JSON.stringify(input));
    fetch("http://localhost:8080/recipe", {
    method: "POST",
    headers: {mode: 'cors', 'Content-Type': 'application/json'}, 
    body: JSON.stringify(input)
    }).then(res => res.json()
    .then(data =>{
    
    console.log("Request complete! response:", data);

    for (i = 0; i < data.length; i++ ){
       dataForSearchResults.push(data[i]);
    }

    console.log(dataForSearchResults);

    getCredit()
    setDataforResults()

    }));
}

function sendFormDataIngredient() {

    //var input = {"data":document.getElementById('form').value};

    console.log(JSON.stringify(tagDict));
    fetch("http://localhost:8080/ingredient", {
    method: "POST",
    headers: {mode: 'cors', 'Content-Type': 'application/json'}, 
    body: JSON.stringify(tagDict)
    }).then(res => res.json()
    .then(data =>{
    
    console.log("Request complete! response:", data);

    for (i = 0; i < data.length; i++ ){
       dataForSearchResults.push(data[i]);
    }

    console.log(dataForSearchResults);

    getCredit()
    setDataforResults()

    }));
}

function setDataforResults() {

    if (dataForSearchResults[0]["sourceUrl"] == "") {
        searchError.innerHTML = "No Results found. Please try a different search."
        dataForSearchResults.length = 0;
        hideLoader()
    } else {

        img1.src = dataForSearchResults[0]["image"];
        link1.setAttribute("href", dataForSearchResults[0]["sourceUrl"])
        recipeOneText.innerHTML = dataForSearchResults[0]["title"]

        img2.src = dataForSearchResults[1]["image"];
        link2.setAttribute("href", dataForSearchResults[1]["sourceUrl"])
        recipeTwoText.innerHTML = dataForSearchResults[1]["title"]

        img3.src = dataForSearchResults[2]["image"];
        link3.setAttribute("href", dataForSearchResults[2]["sourceUrl"])
        recipeThreeText.innerHTML = dataForSearchResults[2]["title"]

    hideLoader()
    search()
    }
    

}

function getCredit() {

    fetch("http://localhost:8080/credit", {
    method: "GET",
    headers: {mode: 'cors', 'Content-Type': 'application/json'}, 
    }).then(res => res.json()
    .then(data =>{
    
    console.log("Get Request complete! response:", data["credits"]);

    credit.innerHTML = data["credits"];

    }));
}


// Search related functions

function search() {

    document.getElementById('img_container').style.display='flex';
    counter++
}

function showLoader() {

    if (counter > 0) {
        
        document.getElementById('img_container').style.display='none';
        dataForSearchResults.length = 0;
        
    }

    searchError.innerHTML = "";

    document.getElementById('loader').style.display='flex';

    //console.log(modeIngredient.checked)

    //sendFormDataTitle()

    if (modeTitle.checked) {
        sendFormDataTitle()
    }else{
        sendFormDataIngredient()
    }

    
    
}

function hideLoader() {
    document.getElementById('loader').style.display='none';
    
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


function addTag() {
    
    if (tagCounter == 0) {
        tagOne.innerText = searchTerm.value;
        tagOneShape.style.display = "flex"
        tagDict["tag1"] = searchTerm.value;
        searchTerm.value = ""
        tagCounter += 1
    } else if (tagCounter == 1) {
        tagTwo.innerText = searchTerm.value;
        tagTwoShape.style.display = "flex"
        tagDict["tag2"] = searchTerm.value;
        searchTerm.value = ""
        tagCounter += 1 
    } else if (tagCounter == 2) {
        tagThree.innerText = searchTerm.value;
        tagThreeShape.style.display = "flex"
        tagDict["tag3"] = searchTerm.value;
        searchTerm.value = ""
        searchTerm.disabled = true;
        tagCounter += 1 
    }


}

function clearTags() {
    

    tagCounter = 0;
    tagOne.innerText = "";
    tagTwo.innerText = "";
    tagThree.innerText = "";
    tagDict["tag1"] = "";
    tagDict["tag2"] = "";
    tagDict["tag3"] = "";
    tagOneShape.style.display = "none"
    tagTwoShape.style.display = "none"
    tagThreeShape.style.display = "none"
    searchTerm.disabled = false;
    
    
}

function showIconTitle() {
    if (searchTerm.value == "") {
        searchBtn.style.display = "none";
    } else {
        searchBtn.style.display = "flex";
    }
}