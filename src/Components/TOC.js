import React, {Component} from 'react';

class TOC extends Component{
    // 부모의 state가 변경되지 않았는데도 자식의 render함수가 실행되어
    // 불필요한 함수 실행이 됨.
    // shouldComponentUpdate()라는 함수를 사용하여 불필요한 렌더링을 하지 않는다.
    // return false : 렌더 함수 호출 안함, true 호출 함. 
    shouldComponentUpdate(){
      return true;
    }
    render(){
      var lists=[];
      var data=this.props.data;
      var i=0;
      while(i<data.length){
        lists.push(
          <li key={data[i].id}>
            <a href={"/content/"+data[i].id}
            data-id={data[i].id}
                onClick={function(e){
                  e.preventDefault();
                  this.props.onChangePage(e.target.dataset.id);
                }.bind(this)}>
              {data[i].title}
            </a>
          </li>);
        i =i+1;
      }
      return(
        <nav>
          <ul>
            {lists}
          </ul>
        </nav>
      ); 
    }
  }
  
  export default TOC;   
  // TOC를 가져다 쓰는 쪽에서(외부에서) TOC를 가져다 쓸 수 있도록 해준다.