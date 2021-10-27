const GRID_URL = "http://localhost:8080/memory/grid";

var selectedFirstImage = null;
var selectedSecondImage = null;
let alreadyGuessedElements = [];

let game = new Game(8);
game.setEventListener("onSelection", onSelection);
game.setEventListener("onFailure", onFailure);
game.setEventListener("onSuccess", onSuccess);
game.setEventListener("onGameEnded", onGameEnded);

document.addEventListener("DOMContentLoaded", _ => {
    startGame();
})

function startGame() {
    toggleGameOverLabel();

    let allCardIds = getAllCardIds();
    console.log(`Got ${allCardIds.length} cards`);

    allCardIds.forEach((id, index) => {
        let element = document.getElementById(id);
        element.addEventListener("click", event => {
            let element = event.target;
            if (isElementSelectable(element)) {
                game.cardSelected(index);
            }
        });
    });
}

function isElementSelectable(element) {
    return element != selectedFirstImage &&
        element != selectedSecondImage &&
        !alreadyGuessedElements.includes(element);
}

function updateImage(element, selectedValue) {
    let imagePath = `../../assets/img/number-${selectedValue}.jpg`;
    console.log(`Update image to image ${imagePath}`);
    element.src = imagePath;
}

function getAllCardIds() {
    let numberOfCards = Array.from(document.getElementsByClassName("memoryCard")).length;
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

function updateTries(tries) {
    let element = document.getElementById("numberOfTries");
    element.innerText = tries;
}

function disableAllCards() {
    console.log("Disable all cards");
    alreadyGuessedElements = Array.from(document.getElementsByClassName("memoryCard"));
}

function toggleGameOverLabel() {
    let gameOverLabel = document.getElementById("game-over-label");
    if (gameOverLabel.style.display === "none") {
        console.log("TOGGLE ELEMENT " + gameOverLabel + " TO BLOCK");
        gameOverLabel.style.display = "block";
    } else {
        console.log("TOGGLE ELEMENT " + gameOverLabel + " TO NONE");
        gameOverLabel.style.display = "none";
    }
}

function onSelection(params) {
    let selectedIndex = params.selectedIndex;

    let urlParams = new URLSearchParams({
        "index": selectedIndex
    });
    let url = `http://localhost:8080/memory/grid?${urlParams}`;
    fetch(url, {
        method: "GET"
    })
    .then(response => response.text())
    .then(text => {
        let value = parseInt(text);

        // get image element
        let selectedId = `memory-card-${selectedIndex + 1}`;
        let imageElement = document.getElementById(selectedId);

        if (selectedFirstImage == null) {
            // first selection
            console.log("This is the first selection");
            selectedFirstImage = imageElement;
            updateImage(imageElement, value);
        } else {
            if (selectedSecondImage == null) {
                // second selection
                console.log("This is the second selection");
                selectedSecondImage = imageElement;
                updateImage(imageElement, value);
            }
        }

        updateTries(params.tries);
        game.setSelection(value);
    })
    .catch(error => console.error(error));
}

function onFailure(params) {
    updatePoints(params.points);
    // wait for 1sec
    setTimeout(_ => resetSelection(true), 1000);
}

function onSuccess(params) {
    // mark elements
    alreadyGuessedElements.push(selectedFirstImage);
    alreadyGuessedElements.push(selectedSecondImage);
    updatePoints(params.points);
    resetSelection(false);
}

function onGameEnded(params) {
    disableAllCards();

    let data = new URLSearchParams({
        "points": params.points
    });
    fetch("http://localhost:8080/ranking", {
        method: "POST",
        body: data,
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
    }).then(response => {
        console.log("Fetched status code:", response.status);

        if (response.status == 200) {
            toggleGameOverLabel();
            setTimeout(_ => {
                window.location.href = "http://localhost:8080/ranking";
            }, 5000);
        } else {
            console.log("Response from server was not correct");
        }
    });
}
