import { Injectable } from '@angular/core';
import { API_URL } from '../app.constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { PointDTO } from '../DTO/PointDTO';
import { GameDTO } from '../DTO/GameDTO';
import { ScoreDTO } from '../DTO/ScoreDTO';
import { ScoreTableDTO } from '../DTO/ScoreTableDTO';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }






  getNextMove(board: string[][], crossTurn: boolean): Observable<PointDTO> {
    return this.http.post<any>(
      `${API_URL}/getNextMove/`+crossTurn,
      board
    ).pipe(
      map(
        data => {
          return data;
        }
      )
    );
  }


  getLastGame(): Observable<GameDTO> {
    return this.http.get<GameDTO>(`${API_URL}/getLastGame/`, { responseType: 'json' });
  }

  saveScore(scoreDTO: ScoreDTO) {
    return this.http.post<any>(`${API_URL}/saveScore/`, scoreDTO, { responseType: 'json' });
  }

  getScoreTable(scoreDTO: ScoreDTO): Observable<ScoreTableDTO> {
    return this.http.post<any>(`${API_URL}/getScoreTable/`, scoreDTO, { responseType: 'json' });
  }
}
