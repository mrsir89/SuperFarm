import React, {Component} from 'react';

class QnAtable extends Component{
  constructor(props){
    super(props)
      const {items}=props;
    this.state = {
      items : items
    }
    this.handleClick=this.handleClick.bind(this);
    console.log('여기 실행 되나?', this.state)
  }

    handleClick(e){
    const {Clickitem}=this.state;
    this.setState({
      Clickitem:{
        ...Clickitem,
        isExpanded : !this.setState.isExpanded
      }
    })
  }

    render(){
      const {items}=this.state;
      console.log(this.state, 'render');
        return(
            <div>
            <div className="row">
                <div className="col">답변상태</div>
                <div className="col-7">제목</div>
                <div className="col-1">작성자</div>
                <div className="col-2">작성일</div>
            </div>
            {items.map((item) =>(
              <div>
                <div className="row">
                  <div className="col">{item.questionBoardStatus}</div>
                  <div className="col-7" data-toggle="collapse" data-target="#demo">
                  {item.questionBoardContent}
                    {item.questionAnswer.map((answer) => (
                      <div id="demo" class="collapse">
                        <div className="col-10">{answer.answerContent}</div>
                        <div className="col">{answer.answerWriter}</div>
                      </div>
                      ))}
                      </div>
                  <div className="col-1">{item.user.username}</div>
                <div className="col-2">{item.questionBoardRegdate}</div>
                </div>      
              </div>
              ))}
               </div>

        );
    }
}

export default QnAtable;