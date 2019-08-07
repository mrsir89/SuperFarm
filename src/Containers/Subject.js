import React, {Component} from 'react';

class Subject extends Component{
    render(){
       return(
        <header>
          <h1><a href="/" onClick={function(e){
            e.preventDefault();
            this.props.onChangePage();
          }.bind(this)}>{this.props.boardlist.questionBoardNum} {this.props.boardlist.questionBoardContent}</a></h1>
          {this.props.boardlist.customerId} {this.props.boardlist.questionBoardRegdate}
        </header>
       );
    }
  }

  export default Subject;