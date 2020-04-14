import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private configJson;
  petProfilesConfig = {
    base_url: null,
    api: null
  };

  constructor(private http: HttpClient) { }

  loadAppConfig() {
    return this.http.get('assets/config/app_config.json')
      .toPromise()
      .then(data => {
        this.configJson = data;
        this.petProfilesConfig = this.configJson.petProfilesConfig;
        console.log('Successfully loaded application configuration file app_config.json');
      })
      .catch(error => {
        throwError('ERROR when loading configuration file app_config.json: ' + error);
      });
  }

  getProperty(key: string): string {
    const prop = this.petProfilesConfig[key];
    if (!prop) {
      throw new Error('Missing value for property: ' + key);
    }
    return prop;
  }

  get contextUrl(): string {
    return this.getProperty('context_url');
  }

  get api(): string {
    return this.getProperty('api');
  }
}
