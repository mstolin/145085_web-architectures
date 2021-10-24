let differentCards = 8;

document.addEventListener("DOMContentLoaded", _ => {
    let grid = new Grid(differentCards);
    console.log("Grid:", grid.getGrid());

    let game = new Game(8, grid, 8);
    game.setEventListener("onSelection", function(params) {
        console.log("ON SELECTION", params);
        updateImage(params.elementIndex, params.selectedValue);

        let element = document.getElementById("numberOfTries");
        element.innerText = params.tries;
    });
    game.setEventListener("onFailure", function(params) {
        console.log("ON FAILURE", params);
    });
    game.setEventListener("onSuccess", function(params) {
        console.log("ON SUCCESS", params);
    });
    game.setEventListener("onGameEnded", function(params) {
        console.log("ON GAME ENDED", params);
    });

    let allCards = document.getElementsByClassName("memoryCard");
    console.log(`Got ${allCards.length} cards`);

    Array.from(allCards).forEach((element, index) => {
        element.addEventListener("click", _ => {
            game.cardSelected(index);
        });
    });
})

function updateImage(idIndex, selectedValue) {
    let id = `memory-card-${idIndex + 1}`;
    let imagePath = `../../assets/img/number-${selectedValue}.jpg`;

    console.log(`Update image ${id} to image ${imagePath}`);

    let element = document.getElementById(id);
    element.src = imagePath;
}
