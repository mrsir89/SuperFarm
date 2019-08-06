import React, {Component} from 'react';

class CreateContent extends Component{
    render(){
      // action : 입력한 정보를 "/..."로 보내겠다!
      // method : action에 get? post? delete? 등등 어떤 방식으로 보내겠다!
      // onSubmit : action_process로 페이지 이동없이 현재 창에서 업뎃하겠다!
      // this.props.onSubmit : 부모 스테이트를 변경할 인자를 보낸다.
      return(
        <article>
            <h2>Create</h2>
            <form action="/create_process" method="post"
              onSubmit={function(e){
                e.preventDefault();
                this.props.onSubmit(
                  e.target.title.value,
                  e.target.desc.value
                );
              }.bind(this)}
            >
              <p><input type="text" name="title" placeholder="input title..."></input></p>
              <p><textarea name="desc" placeholder="input content..."></textarea></p>
              <p>
                <input type="submit"></input>
              </p>
            </form>
        </article>
      ); 
    }
  }

  export default CreateContent;