class Game {

  differentCards;
  grid;
  maxTries;

  tries = 0;
  numberOfFailedTries = 0;
  numberOfSuccessTries = 0;
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

  cardSelected(selectedIndex) {
    if (this.tries <= this.maxTries) {
      this.increaseTries();
      console.log(`User has ${this.maxTries - this.tries} of ${this.maxTries} tries left`)

      let colIndex = Math.floor(selectedIndex / (differentCards / 2));
      let rowIndex = selectedIndex % (differentCards / 2);
      let selectedValue = this.grid.getValue(colIndex, rowIndex);

      this.performEventListener("onSelection", {
        elementIndex: selectedIndex,
        selectedValue: selectedValue,
        tries: this.tries
      });

      if (this.currentSelection != null) {
        // a selection has already been made
        console.log(`Already selected: ${this.currentSelection}, selected now: ${selectedValue}`);

        if (this.currentSelection == selectedValue) {
          this.numberOfSuccessTries = this.numberOfSuccessTries + 1;
          console.log("Congrats, number of successes:", this.numberOfSuccessTries);
          this.performEventListener("onSuccess", {
            elementIndex: selectedIndex,
            selectedValue: selectedValue,
            currentSelection: this.currentSelection
          });
        } else {
          this.numberOfFailedTries = this.numberOfFailedTries + 1;
          console.log("Maybe later, number of fails:", this.numberOfFailedTries);
          this.performEventListener("onFailure", {
            elementIndex: selectedIndex,
            selectedValue: selectedValue,
            currentSelection: this.currentSelection
          });
        }

        // reset selection
        this.currentSelection = null;
      } else {
        // set the selection
        this.currentSelection = selectedValue;
        console.log("Set current selection to", this.currentSelection);
      }
    } else {
      console.log(`Game has already ended with ${this.numberOfSuccessTries} successful tries and ${this.numberOfFailedTries} failed tries`);
    }
  }

  increaseTries() {
    this.tries = this.tries + 1;

    this.performEventListener("onTriesUpdated", {
      tries: this.tries
    });

    if (this.tries == this.maxTries) {
      this.performEventListener("onGameEnded", null);
    }
  }

  performEventListener(name, params) {
    if (this.eventListener[name] != null) {
      this.eventListener[name](params);
    }
  }

  setEventListener(name, fn) {
    this.eventListener[name] = fn;
  }

}