class Game {

  differentCards;
  grid;
  maxTries;

  tries = 0;
  numberOfFailedTries = 0;
  numberOfSuccessTries = 0;
  points = 0;
  successIndexes = [];
  currentSelection = null;

  eventListener = {
    "onSelection": null,
    "onSuccess": null,
    "onFailure": null,
    "onGameEnded": null,
    "onTriesUpdated": null
  };

  constructor(differentCards, grid, maxTries) {
    this.differentCards = differentCards;
    this.grid = grid;
    this.maxTries = maxTries;
  }

  cardSelected(selectedIndex, elementId) {
    if (this.tries < this.maxTries) {
      let selectedValue = this.translateSelectedIndexToValue(selectedIndex); // put in grid
      this.increaseTries();
      console.log(`User has ${this.maxTries - this.tries} of ${this.maxTries} tries left`)

      this.performEventListener("onSelection", {
        selectedValue: selectedValue,
        selectedId: elementId,
        tries: this.tries
      });

      if (this.currentSelection != null) {
        // a selection has already been made
        this.checkSelection(selectedValue);
      } else {
        // set the selection
        this.currentSelection = selectedValue;
        console.log("Set current selection to", this.currentSelection);
      }

      if (this.tries == this.maxTries) {
        // Game has ended
        this.performEventListener("onGameEnded", {
          points: this.points
        });
      }
    } else {
      console.log(`Game has already ended with ${this.numberOfSuccessTries} successful tries and ${this.numberOfFailedTries} failed tries`);
    }
  }

  checkSelection(selectedValue) {
    console.log(`Already selected: ${this.currentSelection}, selected now: ${selectedValue}`);
    if (this.currentSelection == selectedValue) {
      this.onSuccess(selectedValue);
    } else {
      this.onFailure(selectedValue);
    }

    // reset selection
    this.currentSelection = null;
  }

  onSuccess(selectedValue) {
    this.numberOfSuccessTries = this.numberOfSuccessTries + 1;
    console.log("Congrats, number of successes:", this.numberOfSuccessTries);
    this.addPoints(selectedValue * 2);
    this.performEventListener("onSuccess", {
      selectedValue: selectedValue,
      currentSelection: this.currentSelection,
      points: this.points
    });
  }

  onFailure(selectedValue) {
    this.numberOfFailedTries = this.numberOfFailedTries + 1;
    console.log("Maybe later, number of fails:", this.numberOfFailedTries);
    this.decreasePoints(1);
    this.performEventListener("onFailure", {
      selectedValue: selectedValue,
      currentSelection: this.currentSelection,
      points: this.points
    });
  }

  addPoints(points) {
    this.points = this.points + points;
    console.log(`Added ${points} points - New score: ${this.points}`);
  }

  decreasePoints(points) {
    this.points = this.points - points;
    console.log(`Removed ${points} points - New score: ${this.points}`);
  }

  translateSelectedIndexToValue(selectedIndex) {
    let colIndex = Math.floor(selectedIndex / (this.differentCards / 2));
    let rowIndex = selectedIndex % (this.differentCards / 2);
    let selectedValue = this.grid.getValue(colIndex, rowIndex);
    return selectedValue;
}

  increaseTries() {
    this.tries = this.tries + 1;

    this.performEventListener("onTriesUpdated", {
      tries: this.tries
    });
  }

  performEventListener(name, params) {
    if (this.eventListener[name] != null) {
      this.eventListener[name](params);
    }
  }

  setEventListener(name, fn) {
    this.eventListener[name] = fn;
  }

  getCurrentSelection() {
    return this.currentSelection;
  }

}