import { Injectable } from '@angular/core';
import { PetProfile } from '../model/petprofile';
import { ConfigService } from './config.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PetProfilesService {

  private petprofileEndpoint: string;

  constructor(private httpClient: HttpClient, private configService: ConfigService) { 
    this.petprofileEndpoint = this.configService.contextUrl+this.configService.api;
    console.log('petprofiles.service is intitialized')
  }

  getPetProfiles(): Observable<PetProfile[]> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.httpClient.get<PetProfile[]>(this.petprofileEndpoint, {headers: headers})
    .pipe(
      catchError(this.handleError<PetProfile[]>('getPetProfiles', []))
    );
  }

  searchPetProfile(petName: string) {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.httpClient.get<PetProfile>(this.petprofileEndpoint+"/"+petName, {headers: headers})
    .pipe(
      catchError(this.handleError<PetProfile>('getPetProfiles', null))
    );
  }
  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
