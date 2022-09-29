import { Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { SearchControllerService } from 'generated/api';
import { Observable, Subscription } from 'rxjs';
import { ChatService, Message } from '../chat.service';
declare var webkitSpeechRecognition: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  /* Variables */
  value:any='';
  userSearch = new FormControl(' ');
  userQuery: string ='';
  messages: Message[] = [];
  messageBody = document.querySelector('#align');
  showLoading: boolean = false;
  fincoData:any;

  constructor(
    private chatService: ChatService
  ) { }

  ngOnInit(): void {
    this.chatService.conversation.subscribe((val) => {
      if(val[0].author === 'bot'){
        this.showLoading = false;
      }
      this.fincoData = this.chatService.fincoData;
      this.messages = this.messages.concat(val);
    });
  }

  textDetectionUsingVoice() {
    console.log("inside");
    if ('webkitSpeechRecognition' in window) {
      const search = new webkitSpeechRecognition();
      search.continuous = false;
      search.interimResults= false;
      search.lang='en-US';
      search.start();
      let speechInput = this.userSearch;
      search.onresult = function(data:any) {
        this.value = data.results[0][0].transcript;
        speechInput.patchValue(this.value);
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
      this.userQuery = event.target.value;
      this.userSearch.patchValue('');
      this.showLoading= true;
      this.chatService.getBotAnswer(this.userQuery);
    }
  }

}
