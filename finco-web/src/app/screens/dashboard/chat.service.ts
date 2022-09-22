import { Injectable } from '@angular/core';
import { SearchControllerService } from 'generated/api';
import { Subject } from 'rxjs';

export class Message {
  constructor(public author: string, public content: string, public intent?: string, public id?: number) { }
}

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(
    private searchController : SearchControllerService
  ) { }

  conversation = new Subject<Message[]>();

  getBotAnswer(msg: string) {
    const userMessage = new Message('user', msg);
    this.conversation.next([userMessage]);
    this.getBotMessage(msg);
    // const botMessage = new Message('bot', this.getBotMessage(msg));
    // this.conversation.next([botMessage]);
  }

  getBotMessage(question: string) {
    this.searchController.postFromFlask(question).subscribe(response => {
      console.log(response);
      const botMessage = new Message('bot', response.queryResponse, response.nlpResponse?.intent, response.nlpResponse?.ID);
      console.log(botMessage);
      this.conversation.next([botMessage]);
    });
  }
}
