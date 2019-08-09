import React, {Component} from 'react';

class AddQuestion extends Component{
    onSubmit = (e) => {
        e.preventDefault();
        console.log('onsubmit >>>>>>>>>>>>>', e);
        console.log("log : " + e.target.questionBoardContent.value);
        const { value } = e.target.questionBoardContent;
        this.props.submit(value);
    }
    render(){
        return(
            <article>
                <h2>질문하셈</h2>
                {/* <form action="/" method="post"
                    onSubmit={(e)=>{
                    console.log("log : " + e.target.questionBoardContent.value)
                    this.props.onSubmit(
                        e.target.questionBoardContent.value
                    );
                    e.preventDefault();
                }}
                > */}
                <form onSubmit={(e) => this.onSubmit(e) }>
                {/* <div onSubmit={(e)=>{
                    console.log("log : " + e.target.questionBoardContent.value)
                    this.props.onSubmit(
                        e.target.questionBoardContent.value
                    );
                    e.preventDefault();
                }}>              */}
                <p><textarea name="questionBoardContent" placeholder="질문을 입력하세요"></textarea></p>
                <p><input type="submit"></input></p>
                </form>
                {/* </div> */}
            </article>
        );
    }
}

export default AddQuestion;