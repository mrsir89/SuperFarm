import React, {Component} from 'react';

class Content extends Component{
    render(){
      return(
        <article>
           {this.props.boardlist.questionBoardNum} {this.props.boardlist.customerId} {this.props.boardlist.questionBoardContent} {this.props.boardlist.questionBoardRegdate}
        </article>
      ); 
    }
  }

  export default Content;