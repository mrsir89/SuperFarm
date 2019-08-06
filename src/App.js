import React, {Component} from 'react';
import TOC from "./Components/TOC";
import Content from "./Contents/Content";
import Subject from "./Subjects/Subject";
import Control from "./Controllers/Control";
import './App.css';
import CreateContent from './Contents/CreateContent';

class App extends Component {
  // state값 초기화, props값 setting, Component가 실행될 때 render() 보다 먼저 실행되면서 Component 초기화
  constructor(props){
    super(props);
    this.max_content_id=3;
    this.state={
      mode:'welcome',
      selected_content_id:2,
      subject:{title:'Web', sub:'World wide Web!'},
      welcome:{title:'welcome', desc:'Hello, React!!'},
      contents:[
        {id:1, title:'HTML', desc:'HTML is for information'},
        {id:2, title:'CSS', desc:'CSS is for design'},
        {id:3, title:'JavaScript', desc:'JavaScript is for interactive'}
      ]
    }
  }

  // 상위 Component의 state값을 하위 Component의 props로 전달 가능하다.
  render(){ 
    var _title, _desc, _article=null;
    if(this.state.mode ==='welcome'){
      _title=this.state.welcome.title;
      _desc=this.state.welcome.desc;
      _article=<Content title={_title} desc={_desc}></Content>
    }else if(this.state.mode ==='read'){
      var i = 0;
      while(i<this.state.contents.length){
        var data=this.state.contents[i];

        if(data.id===this.state.selected_content_id){
        _title=data.title;
        _desc=data.desc;
        break;
        }
        i=i+1;
      }
      _article=<Content title={_title} desc={_desc}></Content>
    }else if( this.state.mode==='create'){
      _article=<CreateContent onSubmit={function(_title, _desc){
        // if submit, add content to this.state.contents
        this.max_content_id=this.max_content_id + 1;

        // 기존 데이터는 건들지 말고 새로운 데이터 생성.
        // 덮어쓰지 말기
        /*this.state.contents.push(
          {id:this.max_content_id, title:_title, desc:_desc}
        );*/

        this.state.contents.concat(
          {id:this.max_content_id, title:_title, edesc:_desc}
        )
        this.setState({
          contents:this.state.contents
        });

      }.bind(this)}></CreateContent>
    }
    return (
     <div className="App">
       <Subject title={this.state.subject.title} sub={this.state.subject.sub}
                onChangePage={function(){
                  this.setState({mode:'welcome'});
                }.bind(this)}
       ></Subject>
       
       {/*<header>
          <h1><a href="/" onClick={function(e){
            console.log(e);
            e.preventDefault();
            this.state.mode='welcome';
            1. 이벤트 호출되었을 때 실행되는 함수 안에서는 this는 Component를 가르키지 않고 아무값도 설정되지 않음.
            => this.setState({mode:'welcome'}); + 함수 끝나는 곳에 .bind(this) bind함수 사용
          }.bind(this)}>
          {this.state.subject.title}</a></h1>
          {this.state.subject.sub}
        </header>*/}

       <TOC onChangePage={function(id){
          this.setState({
            mode:'read',
            selected_content_id:Number(id)
          });
       }.bind(this)} data={this.state.contents}></TOC>
       <Control onChangeMode={function(_mode){
        this.setState({
          mode:_mode
        })
       }.bind(this)}>
       </Control>
       {_article}
     </div>
    );
  }
}

export default App; 
