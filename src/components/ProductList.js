import React from 'react';
import ProductItem from './ProductItem';
import { Link } from 'react-router-dom';


class ProductList extends React.Component {
  constructor() {
    super()
    this.state = {
     dummy : []
    };
  }

  componentDidMount() {
    this.setState({dummy : QnADummy});
    //this._getProducts();
  }


// data를 불러오는 거니까 async를 쓴다. 
//   async는 항상 await와 함께 쓰인다.

  // _getProducts = async () =>{
  //   const products = await this._callApi()    
  //   this.setState({
  //     dummy : products                    
  //   })
    
  // }

  // _callApi= () => { //webtoon_list를 가지고 옵니다. 
  //     return fetch('http://localhost:8080/product/item/all', {
  //       method: 'GET',
  //       headers: [
  //         ['Accept', 'application/json'],
  //         ['Content-Type', 'application/json; charset: utf-8']
  //       ]
        
  //     })
  //     .then(response => response.json())
  //     //.then(json => console.log(json))
  //     // .then(json => json)             // json => json.data.movies
  //     .catch(error => console.log(error));
  // };

  _renderAllProducts = () => {
    const productItems = this.state.dummy.map (item => {
      return <Link to={`/${item.productCode}`}>
          <ProductItem key={item.productCode} item={item}/>
        </Link>
    })

    return productItems
  }

// {this.state.dummy ? this._renderAllProducts() : 'Loading'}
  render() {
    return(
      <div className="App">
          {this.state.dummy ? this._renderAllProducts() : 'Loading'}
      </div>
    );
  }
}


export default ProductList;

