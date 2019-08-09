import Dummy from './Containers/QnADummy.json'
import QnAtable from './Containers/QnATable';
import Head from './Containers/Head';
import AddQuestion from './Containers/AddQuestion';
import React from 'react';
    
class App extends React.Component{
    constructor(props){
        super(props)
        this.max_items_questionBoardNum=2;
        this.state = {
          mode:'read',
            items : Dummy.items
        };
        console.log('여기 실행 되나?', this.state);
      }

    submit = (text) => {
      const { items } = this.state;
      const qnaItem = {
        questionBoardNum: items.length + 1,
        questionBoardContent: text,
        questionBoardStatus: "false",
        customerId: "steve",
        questionBoardRegdate: "123124",
        questionAnswer: [],
        user: {
          username: 'steve',
          email: 'steve.p@bitcamp.co.kr'
        }
      };

      this.setState({
        ...this.state,
        items:[
          ...items,
          qnaItem
        ]
      });

      console.log("추가됨?", this.state);
    }

    render() {
      var _article=null;
      const { items } = this.state; 
      console.log("items >>>>>>>>>>>>>>>>>", items)

      if(this.state.mode==='write'){
        _article=<AddQuestion submit={this.submit}></AddQuestion>
      }
      return(
      <div className="container">
        <Head onChangePage={function(){
          this.setState({mode:'read'});
        }.bind(this)}></Head><br/>
        <button onClick={function(){
          this.setState({
            mode:'write'
          });
        }.bind(this)}>질문해보슈</button>
        {_article}
        <QnAtable items={items}></QnAtable>
      </div>
      );
    }
}
export default App;



// class App extends React.Component{
//   constructor(){
//     super()
//     this.items_num=2;
//     this.state={
//       items:Dummy.items
//     }
//   }

//   handleCreate=(data) => {
//     const {items}=this.state;
//     this.setState({
//       items:items.concat({questionBoardNum:this.items_num++,
//         questionBoardContent:data, ...data})
//     });
//   }

//   render(){
//     const {items} = this.state;
//     return(
//       <div className='App'>
//         <Head/>
//         <div className="col-7" data-toggle="collapse" data-target="#demo-">
//           <div id="demo-" class="collapse">
//             <AddQuestion handleCreate={ this.handleCreate}/>
//           </div>
//         </div>
//         <QnAtable data={items}></QnAtable>
//       </div>
//     )
//   }
// }
// export default App;
