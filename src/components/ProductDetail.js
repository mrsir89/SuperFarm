import React from 'react';
import ProductItem from './ProductItem'
import productDummy from './QnADummy'
// import PropTypes from 'prop-types'
// import { connect } from 'react-redux';
// import { withRouter } from 'react-router-dom';
// import { matcherErrorMessage } from 'jest-matcher-utils';



class ProductDetail extends React.Component {
    constructor() {    // props 굳이 안써줘도 넘어 옴
      super()
      this.state = {
        dummy : productDummy
      };  
      // state에 이미 모든 Dummy Data가 들어와 있음을 전제로 함
      //  1) local storage에 있는 경우 (store) >> mapStateToProps로 전달 가능 (상품 데이터)
      //  2)              에 없는 경우  >> fetch Api를 통해 비동기적으로 전달 가능 (상품 데이터)      
    }
  
    componentDidMount() {
      // this.setState({dummy : productDummy});
      //console.log("state.dummy", this.state.dummy);   // state에 이미 모든 상품 데이터가 들어와 있음
      //this._getProducts();
  
    }
  
  
    // _renderProducts = () => {                         // state에 있는 모든 데이터 중에서 해당 id와 일치하는 거만 꺼냄 -> product 에 담는다 (배열)
    //     const product = this.state.dummy.filter (item => item.productCode === this.props.match.params.id)
    //     //console.log ("product >>", product)  // >> product는 배열임
    //     return <ProductItem  key={product.productCode} item={product[0]}/>  
        
    // }
  
  
    render() {
      // console.log("this.props",this.props)
      // console.log("match.param.id", this.props.match.params.id)
      return(
        <div >
          {this.state.dummy ? this._renderProducts() : 'Loading'}  
        </div>
      );
    }
  }
  
  // function ProductDetail({ match }){
     
  //     const product = () => {
  //       <ProductItem key={match.params.productCode} item={this.state.dummy}/>
  //     }

  //     return (
  //       <div>
  //         {product}
  //       </div>
  //     )
  // }


  // const mapStateToProps = (state) => ({
  //   products: state.products
  // })
  // export default withRouter(connect(mapStateToProps)(ProductDetail));
  
// Route Props(history, location, match)를 접근 할 수 있도록
// Component Decoration 해준다.  (x)
  //export default withRouter(ProductDetail);

  // 그냥 이렇게만 해줘도 Route props(history, location, match) 다 넘어감
  export default ProductDetail;