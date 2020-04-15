import { Component, OnInit } from '@angular/core';
import { PetProfilesService } from '../../services/petprofiles.service';
import { PetProfile } from '../../model/petprofile';

@Component({
  selector: 'app-petprofile',
  templateUrl: './petprofile.component.html',
  styleUrls: ['./petprofile.component.css']
})
export class PetprofileComponent implements OnInit {

  petProfiles: PetProfile[];
  searchStr: string;
  searchResult: PetProfile;

  constructor(private petProfilesService: PetProfilesService) { }

  ngOnInit(): void {
    this.getPetProfiles();
  }

  getPetProfiles(): void {
    this.petProfilesService.getPetProfiles().subscribe(
      petProfiles => {
        this.petProfiles = petProfiles
        console.log(this.petProfiles)
    });
  }

  searchPetProfiles(): void {
    console.log("Searching pet profile: " + this.searchStr)
    this.petProfilesService.searchPetProfile(this.searchStr).subscribe(
      res => {
        this.searchResult = res;
        console.log(this.searchResult)
      } 
    )
  }
}
