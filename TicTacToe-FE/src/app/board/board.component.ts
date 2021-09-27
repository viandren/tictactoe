import { Component, OnInit } from '@angular/core';
import { ScoreTableDTO } from '../DTO/ScoreTableDTO';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.dataService.getLastGame().subscribe(
      response => {
        this.board = response.boardData;
        this.activePlayer = response.nextPlayer;
        this.gameEnded = this.checkWin();
      });
    this.dataService.saveScore({ player: 'Player1', opponent: 'Computer', result: '' }).subscribe(
      response => this.refreshScoreTable()
    );
  }

  scoreTable: ScoreTableDTO = {player1: 0, player2: 0, draw: 0 };


  width = Array.from({ length: 3 }, (_, i) => i + 1);

  activePlayer: string = 'o';
  isPlayerTheWinner: boolean = false;
  board = Array.from(Array(3), () => Array.from(['', '', '']));
  boardForWinMark = Array.from(Array(3), () => Array.from(['', '', '']));
  gameEnded = false;

  onClick(x: number, y: number) {
    let validStep = false;
    if (!this.gameEnded) {
      //console.log(x + '|' + y);
      if (this.board[x - 1][y - 1] == '') {
        validStep = true;
        if (this.activePlayer == 'o') {
          this.board[x - 1][y - 1] = 'o';
        } else {
          this.board[x - 1][y - 1] = 'x';
        }
      }
    }

    this.gameEnded = this.checkWin();
    if (!this.gameEnded && validStep) {
      this.dataService.getNextMove(this.board, this.activePlayer != 'x').subscribe(
        response => {
          let cx = response.x;
          let cy = response.y;
          if (this.board[cx][cy] == '') {
            if (this.activePlayer == 'o') {
              this.board[cx][cy] = 'x';
            } else {
              this.board[cx][cy] = 'o';
            }
          }
          this.gameEnded = this.checkWin();
        });
    }
  }

  getStyleClass(x: number, y: number) {
    let winMark = '';
    if (this.boardForWinMark[x - 1][y - 1] == 'w') {
      winMark = 'winner-cell ';
    }

    if (this.board[x - 1][y - 1] == 'o') {
      return winMark+'o-marked';
    } else if (this.board[x - 1][y - 1] == 'x'){
      return winMark +'x-marked';
    }
    if (!this.gameEnded) {
      return winMark + 'available';
    }

    return winMark;
  }

  checkWin() {
    if (this.checkBoardForWinner()) {

      this.dataService.saveScore({ player: 'Player1', opponent: 'Computer', result: this.isPlayerTheWinner ? 'win' : 'defeat' }).subscribe(
        response => this.refreshScoreTable()
      );
      return true;
    }
    if (this.checkBoardForDraw()) {

      this.dataService.saveScore({ player: 'Player1', opponent: 'Computer', result: 'draw' }).subscribe(
        response => this.refreshScoreTable()
      );
      return true;
    }

    return false;
  }

  refreshScoreTable() {
    this.dataService.getScoreTable({ player: 'Player1', opponent: 'Computer', result: '' }).subscribe(
      response => this.scoreTable = response
    )
  }

  checkBoardForDraw() {
    for (var i = 0; i < this.board.length; i++) {
      for (var j = 0; j < this.board.length; j++) {
        if (this.board[i][j] == '') {
          return false;
        }
      }
    }
    return true;
  }

  checkBoardForWinner() {

    for (var i = 0; i < this.board.length; i++) {
      console.log(i);
      if (this.board[i][0] != '' && this.board[i][0] == this.board[i][1] && this.board[i][0] == this.board[i][2]) {
        this.boardForWinMark[i][0] = 'w';
        this.boardForWinMark[i][1] = 'w';
        this.boardForWinMark[i][2] = 'w';
        this.isPlayerTheWinner = this.activePlayer == this.board[i][0];
        return true;
      }
    }

    for (var i = 0; i < this.board.length; i++) {
      if (this.board[0][i] != '' && this.board[0][i] == this.board[1][i] && this.board[1][i] == this.board[2][i]) {
        this.boardForWinMark[0][i] = 'w';
        this.boardForWinMark[1][i] = 'w';
        this.boardForWinMark[2][i] = 'w';
        this.isPlayerTheWinner = this.activePlayer == this.board[0][i];
        return true;
      }
    }

    if (this.board[0][0] != '' && this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2]) {

      this.boardForWinMark[0][0] = 'w';
      this.boardForWinMark[1][1] = 'w';
      this.boardForWinMark[2][2] = 'w';
      this.isPlayerTheWinner = this.activePlayer == this.board[0][0];
      return true;
    }


    if (this.board[2][0] != '' && this.board[2][0] == this.board[1][1] && this.board[0][2] == this.board[1][1]) {

      this.boardForWinMark[2][0] = 'w';
      this.boardForWinMark[1][1] = 'w';
      this.boardForWinMark[0][2] = 'w';
      this.isPlayerTheWinner = this.activePlayer == this.board[2][0];
      return true;
    }


    return false;
  }

  reload() {
    //console.log('reload');
    this.board = Array.from(Array(3), () => Array.from(['', '', '']));
    this.boardForWinMark = Array.from(Array(3), () => Array.from(['', '', '']));
    this.gameEnded = false;


    if (this.activePlayer == 'x') {
      this.activePlayer = 'o';

    } else {
      this.activePlayer = 'x';
      this.dataService.getNextMove(this.board, false).subscribe(
        response => {
          let cx = response.x;
          let cy = response.y;
          if (this.board[cx][cy] == '') {
            this.board[cx][cy] = 'o';
          }
          this.gameEnded = this.checkWin();
        });

    }
  }



}
