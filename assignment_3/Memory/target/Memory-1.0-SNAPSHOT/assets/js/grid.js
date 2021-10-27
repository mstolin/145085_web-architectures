class Grid {

  differentCards
  usedTypes;
  columns;
  rows;
  grid;

  constructor(grid) {
      this.differentCards = differentCards;
      this.columns = differentCards / 2;
      this.rows = differentCards / 2;
      this.grid = this.generateDevGrid();
  }

  generatedUsedTypes() {
      let usedTypes = new Map();
      for (var i = 1; i <= this.differentCards; i++) {
          usedTypes.set(i, 0)
      }
      return usedTypes;
  }

  generateDevGrid() {
      return [
          [1, 1, 2, 2],
          [3, 3, 4, 4],
          [5, 5, 6, 6],
          [7, 7, 8, 8]
      ];
  }

  generateGrid() {
      /*let grid = [];

      for (var col = 0; col < this.columns; col++) {
          let row = [];
          for (var rowIndex = 0; rowIndex < this.rows; rowIndex++) {
              row[rowIndex] = this.getRandomCardType();
          }
          grid[col] = row;
      }

      return grid;*/
      return [
          [5, 1, 3, 2],
          [4, 7, 1, 6],
          [6, 5, 4, 2],
          [7, 3, 8, 8]
      ];
  }

  getRandomCardType() {
      let availableTypes = Array.from({length: this.differentCards}, (_, i) => i + 1);
      let usedTypes = new Map();
      let _this = this;

      function _getRandomCardType(_this, availableTypes, usedTypes) {
          let cardType = _this.getRandomIntFrom(availableTypes);
          console.log("Got random type: ", cardType);
          let countOfType = usedTypes.has(cardType) ? usedTypes.get(cardType) : 0;
          console.log(`Count of type ${cardType}: ${countOfType}`)

          if (countOfType < 2) {
              console.log(`Type ${cardType} is still available with count ${countOfType}`);
              let newCount = countOfType + 1;
              usedTypes.set(cardType, newCount);
              return cardType;
          } else {
              console.log(`Type ${cardType} has already been used twice`);
              let indexOfType = availableTypes.indexOf(cardType);
              console.log(`Remove index ${indexOfType}:${availableTypes[indexOfType]} from available types`)
              availableTypes.splice(indexOfType, 1);
              console.log("Available types:", availableTypes);
              return _getRandomCardType(_this, availableTypes, usedTypes);
          }
      }



      return _getRandomCardType(this, availableTypes, usedTypes);
  }

  getRandomIntFrom(arrayOfInts) {
      let randomIndex = this.getRandomInt(0, arrayOfInts.length - 1);
      return arrayOfInts[randomIndex];
  }

  getRandomInt(min, max) {
      min = Math.ceil(min);
      max = Math.floor(max);
      return Math.floor(Math.random() * (max - min + 1) + min);
  }

  getGrid() {
      return this.grid;
  }

  getValue(colIndex, rowIndex) {
      return this.grid[colIndex][rowIndex];
  }

}
