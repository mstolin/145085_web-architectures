let differentCards = 8;
var selectedFirstImage = null;
var selectedSecondImage = null;
let alreadyGuessed = [];

document.addEventListener("DOMContentLoaded", _ => {
    let grid = new Grid(differentCards); // put in game.js
    console.log("Grid:", grid.getGrid());

    let game = new Game(8, grid, 8);
    game.setEventListener("onSelection", onSelection);
    game.setEventListener("onFailure", onFailure);
    game.setEventListener("onSuccess", onSuccess);
    game.setEventListener("onGameEnded", onGameEnded);

    //let allCards = document.getElementsByClassName("memoryCard");
    let allCardIds = getAllCardIds(differentCards);
    console.log(`Got ${allCardIds.length} cards`);

    allCardIds.forEach((id, index) => {
        let element = document.getElementById(id);
        element.addEventListener("click", event => {
            if (event.target != selectedFirstImage && event.target != selectedSecondImage && !alreadyGuessed.includes(event.target)) {
                game.cardSelected(index, id);
            } else {
                console.log("THIS WAS ALREADY SELECTED");
            }
        });
    });
})

function updateImage(element, selectedValue) {
    let imagePath = `../../assets/img/number-${selectedValue}.jpg`;
    console.log(`Update image to image ${imagePath}`);
    element.src = imagePath;
}

function getAllCardIds(differentCards) {
    let numberOfCards = differentCards * 2;
    let ids = Array.from({length: numberOfCards}, (_, i) => {
        let index = i + 1;
        let id = `memory-card-${index}`;
        return id;
    });
    return ids;
}

function resetSelection(flipBack) {
    if (flipBack) {
        let imagePath = "../../assets/img/cardBack.jpg";
        selectedFirstImage.src = imagePath;
        selectedSecondImage.src = imagePath;
    }
    selectedFirstImage = null;
    selectedSecondImage = null;
}

function updatePoints(points) {
    let element = document.getElementById("points");
    element.innerText = points;
}

function disableAllCards() {
    console.log("Disable all cards");
    alreadyGuessed = Array.from(document.getElementsByClassName("memoryCard"));
}

function onSelection(params) {
    console.log("ON SELECTION", params);
    // get image element
    let imageElement = document.getElementById(params.selectedId);

    if (selectedFirstImage == null) {
        // first selection
        console.log("This is the first selection");
        selectedFirstImage = imageElement;
        updateImage(imageElement, params.selectedValue);
    } else {
        if (selectedSecondImage == null) {
            // second selection
            console.log("This is the second selection");
            selectedSecondImage = imageElement;
            updateImage(imageElement, params.selectedValue);
        }
    }

    let element = document.getElementById("numberOfTries");
    element.innerText = params.tries;
}

function onFailure(params) {
    updatePoints(params.points);
    resetSelection(true);
}

function onSuccess(params) {
    // mark elements
    alreadyGuessed.push(selectedFirstImage);
    alreadyGuessed.push(selectedSecondImage);
    updatePoints(params.points);
    resetSelection(false);
}

function onGameEnded(params) {
    disableAllCards();
}
