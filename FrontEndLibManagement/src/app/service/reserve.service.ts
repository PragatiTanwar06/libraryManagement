import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ReserveService {

  constructor(private http: HttpClient) { }

  insertReserveService(readerId,docId,libId,availableCopies){
    return this.http.get('http://localhost:7676/library/api/reserve/'+readerId+'/'+docId+'/'+libId+'/'+availableCopies).pipe(
      map(resp => resp)
    );
  }

  getReservedDocService(readerId: string){
    return this.http.get('http://localhost:7676/library/api/reserve/documents/'+readerId).pipe(
      map(resp => resp)
    );
  }
}
