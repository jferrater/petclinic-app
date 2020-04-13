import { Component, OnInit } from '@angular/core';
import { PetProfiles } from '../../model/mock-petprofiles';

@Component({
  selector: 'app-petprofile',
  templateUrl: './petprofile.component.html',
  styleUrls: ['./petprofile.component.css']
})
export class PetprofileComponent implements OnInit {

  petProfiles = PetProfiles;
  
  constructor() { }

  ngOnInit(): void {
  }

}
