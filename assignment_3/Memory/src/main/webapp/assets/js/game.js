class Game {

  maxTries;

  tries = 0;
  numberOfFailedTries = 0;
  numberOfSuccessTries = 0;
  points = 0;
  firstSelection = null;
  secondSelection = null;

  eventListener = {
    "onSelection": null,
    "onSuccess": null,
    "onFailure": null,
    "onGameEnded": null
  };

  constructor(maxTries) {
    this.maxTries = maxTries;
  }

  cardSelected(selectedIndex) {
    if (this.tries < this.maxTries) {
      this.increaseTries();
      console.log(`User has ${this.maxTries - this.tries} of ${this.maxTries} tries left`)

      this.performEventListener("onSelection", {
        selectedIndex: selectedIndex,
        tries: this.tries
      });
    } else {
      console.log(`Game has already ended with ${this.numberOfSuccessTries} successful tries and ${this.numberOfFailedTries} failed tries`);
    }
  }

  checkSelection() {
    if (this.firstSelection != null && this.secondSelection != null) {
      if (this.firstSelection == this.secondSelection) {
        this.onSuccess(this.secondSelection);
      } else {
        this.onFailure();
      }

      // reset selection
      this.firstSelection = null;
      this.secondSelection = null;

      // check if game ended
      if (this.tries == this.maxTries) {
        // Game has ended
        this.performEventListener("onGameEnded", {
          points: this.points
        });
      }
    }
  }

  onSuccess(selectedValue) {
    this.numberOfSuccessTries = this.numberOfSuccessTries + 1;
    console.log("Congrats, number of successes:", this.numberOfSuccessTries);
    this.addPoints(selectedValue * 2);
    this.performEventListener("onSuccess", {
      points: this.points
    });
  }

  onFailure() {
    this.numberOfFailedTries = this.numberOfFailedTries + 1;
    console.log("Maybe later, number of fails:", this.numberOfFailedTries);
    this.decreasePoints(1);
    this.performEventListener("onFailure", {
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

  increaseTries() {
    this.tries = this.tries + 1;
  }

  performEventListener(name, params) {
    if (this.eventListener[name] != null) {
      this.eventListener[name](params);
    }
  }

  setEventListener(name, fn) {
    this.eventListener[name] = fn;
  }

  setSelection(selectedValue) {
    if (this.firstSelection == null) {
      // first selection
      this.firstSelection = selectedValue;
    } else {
      // second selection
      this.secondSelection = selectedValue;
      // two have been selected
      this.checkSelection();
    }
  }

  getCurrentSelection() {
    return this.currentSelection;
  }

}