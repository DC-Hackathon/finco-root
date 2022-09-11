import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { SearchControllerService } from 'generated/api';
import { Subscription } from 'rxjs';
declare var webkitSpeechRecognition: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  /* Variables */
  value:any='';
  userSearch = new FormControl(null, Validators.required);
  private allSubscriptions = new Subscription();

  constructor(
    private searchController:SearchControllerService
  ) { }

  ngOnInit(): void {
    console.log("Dashboard works");
    console.log(this.userSearch);
  }
  
  textDetectionUsingVoice() {
    console.log("inside");
    if ('webkitSpeechRecognition' in window) {
      const search = new webkitSpeechRecognition();
      search.continuous = false;
      search.interimResults= false;
      search.lang='en-US';
      search.start();
      search.onresult = function(data:any) {
        this.value = data.results[0][0].transcript;
        console.log(this.value);
        search.stop();
      };
      search.onerror= function(error:any) {
        console.log(error)
        search.stop();
      };
    } else {
      console.log("Voice Recognition not supported by you Browser");
    }
  }

  onSubmit(event: any){
    if (event.key === "Enter") {
      console.log(event.target.value);
      const searchText = event.target.value;
      this.searchController.postFromFlask(searchText).subscribe(
        (data)=>
        console.log(data)
      );
    }
  }

}
