import { DatePipe } from '@angular/common';
import { Injectable } from '@angular/core';
import { SearchControllerService } from 'generated/api';
import { Subject } from 'rxjs';

export class Message {
  constructor(public author: string, public content: string, public intent?: any, public id?: number) { }
}

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(
    private searchController : SearchControllerService
  ) { }

  conversation = new Subject<Message[]>();
  fincoData: any;

  getBotAnswer(msg: string) {
    const userMessage = new Message('user', msg);
    this.conversation.next([userMessage]);
    this.getBotMessage(msg);
  }

  setIntent(intent: any){
    switch(intent){
      case 'line1': return 'address';
      case 'advisor.name': return 'advisor name';
      case 'patternTemplate.name': return 'investment profile';
      case 'account.statusCode': return 'status';
      default : return null;
    }
  }
  getBotMessage(question: string) {
    this.searchController.postFromFlask(question).subscribe(response => {
      this.fincoData = response.data;
      console.log(response);
      if (response.nlpResponse?.intent === 'commencementDate'){
        var datePipe = new DatePipe('en-US');
        response.queryResponse = datePipe.transform(response.queryResponse, 'dd/MM/yyyy');
      }
      var intent = this.setIntent(response.nlpResponse?.intent);
      if (intent === null){
        intent = response.nlpResponse?.intent.split(/(?=[A-Z])/).join(' ');
      }
      var queryResponse: any = response.queryResponse;
      if(response.data !== null){
        queryResponse = "<b>" + response.queryResponse + "</b>";
      }
      const botMessage = new Message('bot', queryResponse, intent, response.nlpResponse?.ID);
      this.conversation.next([botMessage]);
    });
  }
}
