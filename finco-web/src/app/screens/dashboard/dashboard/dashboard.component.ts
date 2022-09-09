import { Component, OnInit, SimpleChanges } from '@angular/core';
declare var webkitSpeechRecognition: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  /* Variables */
  value:any='';
  
  constructor() { }

  ngOnInit(): void {
    console.log("Dashboard works");
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

}
