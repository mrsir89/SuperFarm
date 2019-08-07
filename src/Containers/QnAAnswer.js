import React, {Component} from 'react';

class QnAAnswer extends Component{
    render(){
        return(
            <table>
            <thead>
              <tr>
                <td>답변 내용</td>
                <td>작성자</td>
                <td>작성 날짜</td>
              </tr>
            </thead>
            <tbody>
            {this.props.questionAnswer.map(item=>(
              <tr>
                <td>{item.questionAnswer.answerContent}</td>
                <td>{item.questionAnswer.answerWriter}</td>
                <td>{item.questionAnswer.answerDate}</td>
              </tr>
            ))}
            </tbody>
            </table>
        );
    }
}

export default QnAAnswer;