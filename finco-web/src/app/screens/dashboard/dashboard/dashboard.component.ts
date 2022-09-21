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
  userSearch = new FormControl(null, Validators.required);
  userQuery: string ='';
  private allSubscriptions = new Subscription();
  messages: Message[] = [];
  messageBody = document.querySelector('#align');
  @ViewChild('align') align:any; 

  constructor(
    private searchController: SearchControllerService,
    private chatService: ChatService
  ) { }

  ngOnInit(): void {
    this.chatService.conversation.subscribe((val) => {
      this.messages = this.messages.concat(val);
      console.log(this.align)
      this.align.nativeElement.scrollTop = this.align.nativeElement.scrollHeight - this.align.nativeElement.clientHeight;
    });

    console.log(this.userSearch);
    console.log(this.messages);
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
      this.userQuery = event.target.value;
      this.userSearch.patchValue('');
      this.chatService.getBotAnswer(this.userQuery);
      
    }
  }

}
