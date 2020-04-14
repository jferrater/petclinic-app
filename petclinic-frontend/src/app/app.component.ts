import { Component } from '@angular/core';
import { PetProfilesService } from './services/petprofiles.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ PetProfilesService ]
})
export class AppComponent {
  title = 'petclinic-frontend';
}
