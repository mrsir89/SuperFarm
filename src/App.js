import Dummy from './Containers/QnADummy.json';
import QnAtable from './Containers/QnATable';
import Head from './Containers/Head';
import React from 'react';
    
class App extends React.Component{
    constructor(props){
        super(props)
        
        this.state = {
            items : Dummy.items
        };
        console.log('여기 실행 되나?')
    }
    
    render() {
        const { items } = this.state;
        return(
        <div className="container">
          <Head/>
          <div className="titleContenet">
            <QnAtable items={items}>   </QnAtable>
          </div>            
        </div>
        );
    }
}
export default App;